package days

class Day02 {
    data class Tuple (val forward: Int, val depth: Int, val aim: Int)

    fun run(lines: List<String>) {
        val result = analyse(lines)
        println("1: $result")

        val result2 = analyse2(lines)
        println("2: $result2")

    }

    fun analyse2(lines: List<String>): Int {
        val t = lines
            .fold(Tuple(0,0, 0)) { t: Tuple, line: String ->
                drive(t, convertToPair(line))
            }
        return t.forward * t.depth
    }

    private fun drive(t: Tuple, p: Pair<Int, Int>) =
        if (p.first == 0)
            Tuple( t.forward,t.depth,t.aim+p.second)
        else Tuple(t.forward+p.first,t.depth+p.first*t.aim,t.aim)

    fun analyse(lines: List<String>): Int {
        val p = lines
            .fold(Pair(0,0)) { p: Pair<Int, Int>, line: String ->
                sumPair(p, convertToPair(line))
            }
        return p.first * p.second
    }

    private fun sumPair(p1: Pair<Int, Int>, p2: Pair<Int, Int>) = Pair(p1.first+p2.first, p1.second+p2.second)

    private fun convertToPair(line: String): Pair<Int, Int> {
       val (dir, sSteps) = line.split(" ")
       val steps = sSteps.toInt()
       return when(dir) {
           "forward" -> Pair(steps, 0)
           "up" -> Pair(0, -steps)
           "down" -> Pair(0, steps)
           else -> throw Exception("unknown")
       }
    }

}
