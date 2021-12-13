package days

class Day13 : DayX {
    private fun parseInput(lines: List<String>): Pair<Set<Pair<Int, Int>>, List<Pair<String, Int>>> {

        // lines : 783,866
        val points = lines.filter { it.matches(Regex("\\d+,\\d+")) }
                .map { it.split(",") }
                .map { Pair(it[0].toInt(), it[1].toInt()) }
                .toSet()


        // lines : fold along x=655
        val instr = lines.filter { it.startsWith("fold along") }
                .map { it.split(" ")[2].split("=") }
                .map { Pair(it[0], it[1].toInt()) }

        return Pair(points, instr)
    }

    private fun foldVertical(points: Set<Pair<Int, Int>>, pos: Int) = swapCoords(foldHorizontal(swapCoords(points), pos))

    private fun swapCoords(points: Set<Pair<Int, Int>>) = points.map { Pair(it.second, it.first) }.toSet()

    private fun foldField(points: Set<Pair<Int, Int>>, instr: Pair<String, Int>) = if (instr.first == "x") foldVertical(points, instr.second) else foldHorizontal(points, instr.second)

    fun foldHorizontal(points: Set<Pair<Int, Int>>, pos: Int) =
            points.sortedBy { it.second }
                    .map { if (it.second < pos) it else Pair(it.first, 2 * pos - it.second) }
                    .toSet()


    override fun analyse(lines: List<String>): Any {
        val (points, instructions) = parseInput(lines)
        val result = foldField(points, instructions[0])
        return result.size
    }

    override fun analyse2(lines: List<String>): Any {
        var (points, instructions) = parseInput(lines)
        instructions.forEach { points = foldField(points, it) }
        points.groupBy { it.second }.map {
            var line = StringBuilder(" ".repeat(40))
            it.value.forEach { p -> line.set(p.first, 'X') }
            (1..8).onEach { line.set(it * 5 - 1, ' ') }
            line.toString()
        }.forEach { println(it) }
        return 0
    }

}
