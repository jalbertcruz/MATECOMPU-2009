-module(actions).
-export([decode/1]).

%createSensorUpdatePair(Acum, Cad)
createSensorUpdatePair(Acum, "") ->
    Acum;
createSensorUpdatePair(Acum, Cad) ->
    [_ | V] = Cad,
    Name = lists:takewhile(fun(C) -> C =/= $" end, V),
    {_, V1} = lists:split(length(Name) + 2, V),
    [H|V2] = V1,
    Value = if
                H =:= $" ->
            Delta = 2,
            lists:takewhile(fun(C) -> C =/= $" end, V2) ; % Es un valor string
                H =:= $- -> 
            Delta = 0,
            lists:takewhile(fun(C) -> C =/= 32 end, V1);

                true     ->
            Delta = 0,
            lists:takewhile(fun(C) -> C =/= 32 end, V1)   % es numerico
            end,
    RealDelta = if
                    length(Value) + Delta =< length(V2) -> length(Value) + Delta;
                    true -> length(V2)
                end,
    {_, CadNew} = lists:split(RealDelta, V2),
    createSensorUpdatePair([{update, Name, Value} | Acum], CadNew).

createSensorUpdate(L) ->
    P = "sensor-update ",
    {_, V} = lists:split(length(P), L),
    createSensorUpdatePair([], V).

createBroadcast(L) ->
    P = "broadcast ",
    {broadcast, message, lists:sublist(L, length(P) + 2, length(L) - length(P) - 2)}.
 
splitMsg(Acum, []) ->
    Acum;
splitMsg(Acum, L) ->
    [_, _, _, Cant | Rest] = L,
    {L1, L2} = lists:split(Cant, Rest),
    splitMsg([L1 | Acum], L2).


decode(Packet) ->
    Msgs = splitMsg([], Packet),
    Rest = lists:flatten(
        lists:map(fun(Val) ->
                    P = lists:prefix("sensor-update ", Val),
                    if
                        P    -> createSensorUpdate(Val); % Actualizacion de variables
                        true -> createBroadcast(Val)     % envio de mensaje
                    end
                  end, Msgs
                 )
                        ),
    lists:foreach(fun(E) ->
                case E of
                    {broadcast, message, "darPasoDone"} ->
                        {server, 'nl@jfx'} ! darPasoDone;

                    {broadcast, message, "darNPasosDone"} ->
                        {server, 'nl@jfx'} ! darNPasosDone;

                    {update, "catx", NewValue} ->
                        {server, 'nl@jfx'} ! {catx, NewValue};

                    {update, "caty", Value} ->
                        {server, 'nl@jfx'} ! {caty, Value};

                    Other -> {error, Other} %io:write(Other)
                end
                  end,
                  lists:reverse(Rest)).
    %{server, 'nl@jfx'} ! Rest.



