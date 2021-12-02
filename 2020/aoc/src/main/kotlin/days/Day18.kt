package days

class Day18 {
    fun run(lines: List<String>) {
        // println(lines.map { l -> calcLine(tokenize(l)) }.sum())
        println(lines.map { l -> calcLine2(l) }.sum())
    }

    fun calcLine2(l: String): Long {
        val innerRx = Regex("\\(([^()]+)\\)")
        val oooRx = Regex("(\\d+)\\s*\\+\\s*(\\d+)")

        if (innerRx.containsMatchIn(l)) {
            var inner = innerRx.find(l)!!.groupValues[1]
            var innerValue = calcLine2(inner.trim())
            return calcLine2(l.replaceFirst("($inner)", innerValue.toString()))
        }

        if (oooRx.containsMatchIn(l)) {
            var gv = oooRx.find(l)!!.groupValues
            var oooValue = gv[1].toLong() + gv[2].toLong()
            // println("$l")
            // println("(x + y) -> ${gv[0]} = $oooValue")
            return calcLine2(l.replaceFirst(gv[0],oooValue.toString()))
        }
        var it = l.split(" ").listIterator()
        return calc2(it)
    }

    fun tokenize(l: String): List<String> =
        l.replace("(", " ( ").replace(")", " ) ").split(" ").filter { it.isNotEmpty() }

    fun calcLine(tokens: List<String>): Long {
        val it = tokens.listIterator()

        return calc(it)
    }

    private fun calc2(it: ListIterator<String>): Long {
        var akku = 1L
        while (it.hasNext()) {
            var token = it.next().trim()
            when (token) {
                "*" -> { // nothing
                }
                else -> {
                    akku *= token.toLong()
                }
            }

        }

        return akku
    }

    private fun eval2(akku: Long, op: String, operant: Long): Long {
        when (op) {
            "+" -> return akku + operant
            "*" -> return akku * operant
        }
        return akku
    }

    private fun calc(it: ListIterator<String>): Long {
        var akku = 0L
        var op = "+"
        while (it.hasNext()) {
            var token = it.next().trim()
            when (token) {
                "*" -> op = "*"
                "+" -> op = "+"
                "(" -> akku = eval(akku, op, calc(it))
                ")" -> return akku
                else -> akku = eval(akku, op, token.toLong())
            }
        }

        return akku
    }

    private fun eval(akku: Long, op: String, operant: Long): Long {
        // println("$akku $op $operant")
        when (op) {
            "+" -> return akku + operant
            "*" -> return akku * operant
        }
        return akku
    }

}
