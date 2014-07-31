
package scala.erlang.server

import scala.erlang.conversions.Converter._
import com.ericsson.otp.erlang._
import scala.actors._

abstract class OtpActor {

    def acts: PartialFunction[Any, Unit]

    def node: Node

    private[this] val mbox: OtpMbox = node.createMbox

    def registerName (name: String)                 = mbox registerName name

    def send (name: String, msg: Any)               = mbox send (name, scl2otp(msg))

    def send (name: String, node: String, msg: Any) = mbox send (name, node, scl2otp(msg))

    def send (to: OtpErlangPid, msg: Any)           = mbox send (to, scl2otp(msg))

    private[this] var me: Actor =  scala.actors.Actor.actor {
        scala.actors.Actor.loop {
            scala.actors.Actor.react(acts)
        }
    }

    // actor que recibe de Erlang y manda para el mio
    private[this] val _t1 = new Runnable{
        def run() {
            while(true){
                me ! otp2scl( mbox.receive )
            }
        }
    }

    private[this] val hilo = new Thread( _t1 )
    hilo.start()

    def stop = hilo.interrupt()

}
