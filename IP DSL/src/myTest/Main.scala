package myTest

import ipdsl.Prelude._

object Main {

    def usarSi() {

    //  si-sino:
    //  si es cierto que 51 < 6 hacer {
        si es cierto que 51 < 6 entonces {

            Consola imprimir "si"
            Consola nl

        }
        
    }

    def usarSi_Sino() {
        var valor = 6
        si es cierto que 51 < valor hacer {
            Consola imprimir "El valor es mayor."
            Consola nl
        } si no lo es hacer {
            Consola imprimir "No lo es."
            Consola nl
        }

    }

    def usarMientras1() {

        var c = 1

        mientras sea c < 6  hacer {

            Consola imprimir c
            Consola imprimirNuevaLinea
            
            c += 1

        }

    }

    def usarMientras2() {

        var c = 1

        iterar mientras c < 6 haciendo {

            Consola.imprimir(c)
            Consola.imprimirNuevaLinea
            c += 1
        }

    }

    def usarDesde() {

        iterar desde 13 hasta 0 con paso de -3 haciendo {
            i => {
                Consola imprimir i
                Consola nl
            }
        }

        iterar desde 6 hasta 2 haciendo {
            i => {
                Consola imprimir i
                Consola nl
            }
        }

    }


    def buscarMayor(): Int = {

        val valores = Lista(5, 8, 2, 9, 45, 12):::Lista(1, 666)
        var m = valores(0)
        iterar desde 1 hasta valores.length - 1 haciendo {
            i => {
                si es cierto que valores(i) > m entonces {
                    m = valores(i)
                }
            }
        }

        return m
    }

    def main(args: Array[String]) = {

        Consola imprimir buscarMayor
        Consola nl

    }

}