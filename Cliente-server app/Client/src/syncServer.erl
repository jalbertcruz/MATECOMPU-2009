-module(syncServer).

-export([start/1,
         loop/1,
         init/1]).

start(Socket) ->
    register(syncServer, spawn(syncServer, init, [Socket])).

init(Socket) ->
    loop(Socket).

loop(Socket) ->
    receive
	
        {daPaso, _} ->
            %io:write(daPaso),
            Msg = mkMessage("broadcast darPaso"),
            gen_tcp:send(Socket, Msg),
            loop(Socket);
			
        {daNPasos, N} ->
            %io:write(N),
            Conversion = convertValue(N),
            Msg1 = mkMessage("sensor-update \"cantPasos\" " ++ Conversion ++ " "),
            Msg2 = mkMessage("broadcast darNPasos"),
            Msg = Msg1 ++ Msg2,
            gen_tcp:send(Socket, Msg),
            loop(Socket);
			
        {decirPalabras, Palabras} ->
            Conversion = convertValue(Palabras),
            Msg1 = mkMessage("sensor-update \"palabras\" " ++ Conversion ++ " "),
            Msg2 = mkMessage("broadcast decirPalabras"),
            Msg = Msg1 ++ Msg2,
            gen_tcp:send(Socket, Msg),
            loop(Socket);
			
        stop -> ok;
		
        Other -> io:write(Other)
		
    end.

mkMessage(Msg) -> L = length(Msg),
    [0, 0, 0, L] ++ Msg.

convertValue(Value) ->
    if 
        is_list(Value) -> [$"] ++ Value ++ [$"];
        is_integer(Value) ->  integer_to_list(Value);
        is_atom(Value) -> atom_to_list(Value)
    end.

