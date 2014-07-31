
package scala.erlang.conversions

import com.ericsson.otp.erlang._

trait Record // Marca para indicar que estamos en presencia de un Record Erlang

object Converter {

    def otp2scl(v: OtpErlangObject): Any = {

        v match {

            case v: Record           => "TODO:!!!" // Cuando sean clases mapeadoras de Records Erlang

            case v: OtpErlangString  => v.stringValue

            case v: OtpErlangBoolean => v.booleanValue

            case v: OtpErlangAtom    => Symbol(v.toString)

            case v: OtpErlangInt     => v.intValue

            case v: OtpErlangUInt    => v.intValue

            case v: OtpErlangByte    => v.intValue

            case v: OtpErlangChar    => v.charValue

            case v: OtpErlangShort   => v.intValue

            case v: OtpErlangUShort  => v.intValue

            case v: OtpErlangLong    => v.longValue

            case v: OtpErlangFloat   => v.doubleValue

            case v: OtpErlangDouble  => v.doubleValue

            case v: OtpErlangList    =>  v.elements map( e => otp2scl(e) ) toList

            case v: OtpErlangTuple   =>
                val ecs = v.elements map( e => otp2scl(e) )
                v.elements.length match {

                    /* Para el caso de las tuplas

                     Ej: case 3 => ( ecs(0), ecs(1), ecs(2) )

                     val res = (2 to 22) map( i => {

                     val l = 0 to i - 1

                     " " * 29 + "case " + i + " => ( ecs(" + l.mkString( "), ecs(" ) + ") )"

                     })

                     val cases = res mkString "\r\n"

                     */
                    case 2 => ( ecs(0), ecs(1) )
                    case 3 => ( ecs(0), ecs(1), ecs(2) )
                    case 4 => ( ecs(0), ecs(1), ecs(2), ecs(3) )
                    case 5 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4))
                    case 6 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5) )
                    case 7 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6) )
                    case 8 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7) )
                    case 9 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8) )
                    case 10 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8), ecs(9) )
                    case 11 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8), ecs(9), ecs(10) )
                    case 12 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8), ecs(9), ecs(10), ecs(11) )
                    case 13 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8), ecs(9), ecs(10), ecs(11), ecs(12) )
                    case 14 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8), ecs(9), ecs(10), ecs(11), ecs(12), ecs(13) )
                    case 15 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8), ecs(9), ecs(10), ecs(11), ecs(12), ecs(13), ecs(14) )
                    case 16 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8), ecs(9), ecs(10), ecs(11), ecs(12), ecs(13), ecs(14), ecs(15) )
                    case 17 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8), ecs(9), ecs(10), ecs(11), ecs(12), ecs(13), ecs(14), ecs(15), ecs(16) )
                    case 18 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8), ecs(9), ecs(10), ecs(11), ecs(12), ecs(13), ecs(14), ecs(15), ecs(16), ecs(17) )
                    case 19 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8), ecs(9), ecs(10), ecs(11), ecs(12), ecs(13), ecs(14), ecs(15), ecs(16), ecs(17), ecs(18) )
                    case 20 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8), ecs(9), ecs(10), ecs(11), ecs(12), ecs(13), ecs(14), ecs(15), ecs(16), ecs(17), ecs(18), ecs(19) )
                    case 21 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8), ecs(9), ecs(10), ecs(11), ecs(12), ecs(13), ecs(14), ecs(15), ecs(16), ecs(17), ecs(18), ecs(19), ecs(20) )
                    case 22 => ( ecs(0), ecs(1), ecs(2), ecs(3), ecs(4), ecs(5), ecs(6), ecs(7), ecs(8), ecs(9), ecs(10), ecs(11), ecs(12), ecs(13), ecs(14), ecs(15), ecs(16), ecs(17), ecs(18), ecs(19), ecs(20), ecs(21) )
                }

                //case _					=> None
            case v: OtpErlangObject => v
        }

    }

    def scl2otp(v: Any): OtpErlangObject = {

        v match {

            case v: Byte     => new OtpErlangInt(v)

            case v: Short    => new OtpErlangInt(v)

            case v: Int      => new OtpErlangInt(v)

            case v: Long     => new OtpErlangLong(v)

            case v: Float    => new OtpErlangDouble(v)

            case v: Double   => new OtpErlangDouble(v)

            case v: Char     => new OtpErlangChar(v)

            case v: Boolean   => new OtpErlangBoolean(v)

            case v: String  => new OtpErlangString(v)

            case v: Symbol  => new OtpErlangAtom(v.name)

            case v: List[_] => new OtpErlangList( v.map( e => scl2otp(e) ).toArray )

                /* Para el caso de las tuplas
                 Ej. case ( a1, a2, a3 )   => new OtpErlangTuple(Array(  scl2otp(a1), scl2otp(a2), scl2otp(a3)                ))

                 val res = (2 to 22) map( i => {
                 val l = 1 to i
                 " " * 13 + "case ( a" + l.mkString(", a") + ")   => new OtpErlangTuple(Array( scl2otp(a" +
                 l.mkString( "), scl2otp(a" ) + ")))"
                 })
                 val cases = res mkString "\r\n"
                 */

            case ( a1, a2)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2)))
            case ( a1, a2, a3)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3)))
            case ( a1, a2, a3, a4)   => new OtpErlangTuple(Array( scl2otp(a1),scl2otp(a2), scl2otp(a3), scl2otp(a4)))
            case ( a1, a2, a3, a4, a5)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5)))
            case ( a1, a2, a3, a4, a5, a6)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6)))
            case ( a1, a2, a3, a4, a5, a6, a7)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9, a10)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9), scl2otp(a10)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9), scl2otp(a10), scl2otp(a11)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9), scl2otp(a10), scl2otp(a11), scl2otp(a12)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9), scl2otp(a10), scl2otp(a11), scl2otp(a12), scl2otp(a13)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9), scl2otp(a10), scl2otp(a11), scl2otp(a12), scl2otp(a13), scl2otp(a14)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9), scl2otp(a10), scl2otp(a11), scl2otp(a12), scl2otp(a13), scl2otp(a14), scl2otp(a15)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9), scl2otp(a10), scl2otp(a11), scl2otp(a12), scl2otp(a13), scl2otp(a14), scl2otp(a15
                        ), scl2otp(a16)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9), scl2otp(a10), scl2otp(a11), scl2otp(a12), scl2otp(a13), scl2otp(a14), scl2otp(a15), scl2otp(a16), scl2otp(a17)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9), scl2otp(a10), scl2otp(a11), scl2otp(a12), scl2otp(a13), scl2otp(a14), scl2otp(a15), scl2otp(a16), scl2otp(a17), scl2otp(a18)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9), scl2otp(a10), scl2otp(a11), scl2otp(a12), scl2otp(a13), scl2otp(a14), scl2otp(a15), scl2otp(a16), scl2otp(a17), scl2otp(a18), scl2otp(a19)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9), scl2otp(a10), scl2otp(a11), scl2otp(a12), scl2otp(a13), scl2otp(a14), scl2otp(a15), scl2otp(a16), scl2otp(a17), scl2otp(a18), scl2otp(a19), scl2otp(a20)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21)   => new OtpErlangTuple(Array( scl2otp(a1),scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9), scl2otp(a10), scl2otp(a11), scl2otp(a12), scl2otp(a13),
                                                                                                                                                 scl2otp(a14), scl2otp(a15), scl2otp(a16), scl2otp(a17), scl2otp(a18), scl2otp(a19), scl2otp(a20), scl2otp(a21)))
            case ( a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22)   => new OtpErlangTuple(Array( scl2otp(a1), scl2otp(a2), scl2otp(a3), scl2otp(a4), scl2otp(a5), scl2otp(a6), scl2otp(a7), scl2otp(a8), scl2otp(a9), scl2otp(a10), scl2otp(a11), scl2otp(a12), scl2otp(a13), scl2otp(a14), scl2otp(a15), scl2otp(a16), scl2otp(a17), scl2otp(a18), scl2otp(a19), scl2otp(a20), scl2otp(a21), scl2otp(a22)))


            case v: OtpErlangObject => v
        }

    }



}
