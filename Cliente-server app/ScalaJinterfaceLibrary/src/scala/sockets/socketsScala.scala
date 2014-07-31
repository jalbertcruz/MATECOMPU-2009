
package scala.sockets

import java.io._
import scala.actors._
import java.net._

class ClientActorSlave(handler: Actor, name: String, channel: Socket) extends Runnable{

    private[this] val output = new ObjectOutputStream( channel.getOutputStream() )
	
    output.flush()
	
    private[this] val input = new ObjectInputStream( channel.getInputStream() )
	
    def ! (v: Any){
        output writeObject v
        output.flush()
    }
	
    def run(){
        var res = input.readObject()
        while(res != null){
            handler ! (res, this)
            res = input.readObject()
        }
    }

    (new Thread(this)).start()
	
}

abstract class ServerScala(port: Int) extends Actor with Runnable {

    private[this] val sc = new ServerSocket(port)
	
    protected val hijos = new scala.collection.mutable.ListBuffer[ClientActorSlave]()
	
    def run(){
        while(true){
            val nsc = sc.accept()
            val h = new ClientActorSlave(this, "", nsc)
            hijos += h
        }
    }
	
}

class ClientScala(host: String, port: Int, logic: PartialFunction[Any, Unit]) extends Runnable{

    val channel = new Socket( InetAddress.getByName(host), port )

    val responder = scala.actors.Actor.actor {
        scala.actors.Actor.loop {
            scala.actors.Actor.react ( logic )
        }
    }

    private[this] val output = new ObjectOutputStream( channel.getOutputStream() )
	
    output.flush()
	
    private[this] val input = new ObjectInputStream( channel.getInputStream() )
	
    def ! (v: Any){
        output writeObject v
        output.flush()
    }
	
    def run(){
	
        var res = input.readObject()
		
        while(res != null){
            responder ! res
            res = input.readObject()
        }
    }
	
    (new Thread(this)).start()
	
}

object server extends ServerScala(5900) {

    (new Thread(this)).start()

    def act(){
	
        scala.actors.Actor.loop {
            scala.actors.Actor.react {
			
                case ("todos", _) => hijos.foreach(h => h ! "oyeee!!!!")
				
                case (msg, sender: ClientActorSlave) => sender ! 'recibido
				
                    //case v => println(v)
				
            }
        }
    }

}
