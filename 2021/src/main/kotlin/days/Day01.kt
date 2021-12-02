package days

class Day01 {
    fun run(lines: List<String>) {
        val result = analyze(convertLines(lines))
        println("1: $result")

        val result2 = analyze2(convertLines(lines))
        println("2: $result2")
    }

    fun convertLines(lines: List<String>) = lines.map { l -> l.toInt() }

    fun analyze(lines: List<Int>): Int {
        var prev = lines[0];
        return lines.drop(1)
            .map { i -> val cmp = i > prev; prev = i; cmp }
            .filter { b -> b }
            .size
    }

    fun analyze2(lines: List<Int>): Int {
        var prev = lines.subList(0, 2);
        val slidedList = lines.drop(2)
            .map { l ->
                val ml = prev.toMutableList()
                ml.add(l)
                val sum = ml.sum()
                prev = ml.drop(1)
                sum
            }
        return analyze(slidedList)
    }

}
