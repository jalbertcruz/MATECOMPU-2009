
package scala.erlang.server

import scala.erlang.conversions.Converter._
import com.ericsson.otp.erlang._

class Node private (otpNode: OtpNode) {

    def createMbox = otpNode.createMbox

    def createMbox(name: String) = otpNode.createMbox(name)

    def this(name: String, cookie: String) = this(new OtpNode(name, cookie))

    def this(name: String, cookie: String, port: Int) = this(new OtpNode(name, cookie, port))

    def this(name: String) = this(new OtpNode(name))

    def ping(node: String, timeout: Long): Boolean = otpNode.ping(node, timeout)

    //def createMbox(acts: PartialFunction[Any, Unit]) = new OtpActor(otpNode.createMbox, acts)

}