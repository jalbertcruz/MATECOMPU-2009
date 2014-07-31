
-module(client).

-export([loop/1,
         connect/0,
         start/0,
         init/0,
         stop/0]).
 
loop(Socket) ->
    receive
        {tcp, Socket, Packet} ->
            actions:decode(Packet),
            loop(Socket);

        stop -> syncServer ! stop,
            ok = gen_tcp:close(Socket)
    end.

connect() ->
    {ok, Socket} = gen_tcp:connect("localhost", 42001, [{packet, 0}]),
    Socket.

start() ->
    register(client, spawn(client, init, [])).

stop()->
    client ! stop.

init() ->
    S = connect(),
    syncServer:start(S),
    loop(S).
