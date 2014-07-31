package EstructurasDeControl

object iterar {
    def mientras(cond: => Boolean): TMientras = new TMientras(cond)

    def desde(valInit: Int): T1 = new T1(valInit)

}

object mientras {
    def sea(cond: => Boolean): TMientras = new TMientras(cond)
}

class T1(valInit: Int) {
    def hasta(valFinal: Int) = new T2(valInit, valFinal)
}

class T2(valInit: Int, valFinal: Int) extends T1(valInit) {
    def haciendo(act: (Int) => Unit) {

        if (valInit > valFinal)
        List.range(valInit, valFinal - 1, -1).foreach(act)
        else
        valInit to valFinal foreach (act)

    }

    def con(p: T3): T4 = new T4(valInit, valFinal)
}

class T3

class T4(valInit: Int, valFinal: Int) {
    def de(paso: Int): T5 = new T5(valInit, valFinal, paso)
}

class T5(valInit: Int, valFinal: Int, paso: Int) {
    def haciendo(act: (Int) => Unit) {

        var delta = if (paso > 0) 1 else -1

        List.range(valInit, valFinal + delta, paso).foreach(act)

    }

}

class TMientras(cond: => Boolean) {
    def haciendo(inst: => Unit) {
        while (cond) inst
    }

    def hacer(inst: => Unit) {
        while (cond) inst
    }

}




