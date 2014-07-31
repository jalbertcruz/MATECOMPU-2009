
package server

import scala.erlang.conversions.Converter._
import com.ericsson.otp.erlang._
import scala.erlang.server._

object gato {

    var x: Double = 0
    var y: Double = 0

    def camina(n: Int): T1 = new T1(n)

    def dice(msg: String){
        sendNoWait('decirPalabras, msg)
    }

    def daPaso(){
        sendAndWait('daPaso, Nil)
    }

    class T1(n: Int){
        def pasos{
            sendAndWait('daNPasos, n)
        }
    }


    val nodo: Node = new Node("nl", "coo")
    val llaves = scala.collection.mutable.Map[Symbol, Boolean]()

    def sendNoWait(label: Symbol, arg: Any){
        server.send("syncServer", "nl1@jfx", scl2otp(label, arg))
    }

    def sendAndWait(label: Symbol, arg: Any){
        server.send("syncServer", "nl1@jfx", scl2otp(label, arg))
        llaves += (label -> false)
        while(! llaves(label))
        Thread.sleep(100);
    }
 
    val server = new scala.erlang.server.OtpActor {

        override def node = nodo

        override def acts: PartialFunction[Any, Unit] = {

            case ('catx, value: String) =>
                gato.x = value.toDouble

            case ('caty, value: String) =>
                gato.y = value.toDouble

            case 'darPasoDone =>
                llaves += ('daPaso -> true)

            case 'darNPasosDone =>
                llaves += ('daNPasos -> true)

            case msg => println(msg)
        }

    }
    server registerName "server"

}