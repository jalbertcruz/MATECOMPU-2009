package EstructurasDeControl


object si {
    def es(a: T9): T10 = new T10
}

class T9

class T10 {
    def que(cond: => Boolean): T11 = new T11(cond)
}

class T11(cond: => Boolean) {
    def entonces(inst: => Unit) {
        if (cond) inst
    }

    def hacer(inst: => Unit): T12 = new T12(cond, inst)

}


class T12(cond: => Boolean, inst: => Unit) {
    def si(t: T13): T14 = new T14(cond, inst)
}

class T13

class T14(cond: => Boolean, inst: => Unit) {
    def lo(t: T15): T16 = new T16(cond, inst)
}

class T15

class T16(cond: => Boolean, instIf: => Unit) {
    def hacer(instElse: => Unit) {
        if (cond)
            instIf
        else
            instElse
    }

}

