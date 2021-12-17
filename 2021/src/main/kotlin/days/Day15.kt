package days

class Day15 : Day00 {

    private lateinit var END_POINT: MyPoint
    private var dimX: Int = 0
    private var dimY: Int = 0
    private lateinit var maze: Array<Array<Day15.MazePoint>>
    private var totolRiskMin: Int = -1

    private val NO_POINT: Day15.MyPoint = MyPoint(-10, -10)
    private val START_POINT: Day15.MyPoint = MyPoint(-1, -1)

    data class MyPoint(val x: Int, val y: Int)

    data class MazePoint(val risk: Int, val pathValue: Int, val min: MyPoint) {
        fun minValue(newMin: Int, newPoint: MyPoint) = MazePoint(risk, newMin, newPoint)
    }

    private fun parseInput(lines: List<String>): Array<Array<MazePoint>> {
        val dY = lines.size
        val dX = lines[0].length
        return Array(dX) { i -> Array(dY) { j -> MazePoint(lines[j][i].toString().toInt(), -1, NO_POINT) } }
    }

    private fun calcProperties() {
        dimY = maze.size
        dimX = maze[0].size
        maze[0][0] = maze[0][0].minValue(0, START_POINT)
        END_POINT = MyPoint(dimX - 1, dimY - 1)
        totolRiskMin = maze[0].sumOf { it.risk } + maze.sumOf { it[dimX - 1].risk }
    }

    private fun searchPath(p: Day15.MyPoint) {
        val totalRisk = maze[p.y][p.x].pathValue
        getHorOrVar(p).filter {
            val fp = maze[it.y][it.x]
            fp.pathValue < 0 || fp.pathValue > fp.risk + totalRisk
        }.forEach {
            val fp = maze[it.y][it.x]
            maze[it.y][it.x] = fp.minValue(fp.risk + totalRisk, p)
            if (it != END_POINT && maze[it.y][it.x].pathValue < totolRiskMin) searchPath(it)
        }
    }

    private fun getHorOrVar(p: Day15.MyPoint) =
        listOf(
            Day15.MyPoint(p.x - 1, p.y),
            Day15.MyPoint(p.x + 1, p.y),
            Day15.MyPoint(p.x, p.y - 1),
            MyPoint(p.x, p.y + 1)
        )
            .filter { it.x >= 0 && it.y >= 0 }
            .filter { it.x < dimX && it.y < dimY }

    override fun analyse(lines: List<String>): Int {
        maze = parseInput(lines)
        calcProperties()
        searchPath(MyPoint(0, 0))
        return maze[END_POINT.x][END_POINT.y].pathValue
    }

    override fun analyse2(lines: List<String>): Any {
        maze = parseInput(parseInputToBigMaze(lines))
        calcProperties()
        searchPath(MyPoint(0, 0))
        return maze[END_POINT.x][END_POINT.y].pathValue
    }

    fun linePlusOne(line: String) =
        line.toCharArray().toList().map { c -> c.toString().toInt() % 9 + 1 }.joinToString("")

    private fun parseInputToBigMaze(lines: List<String>): List<String> {
        val extendedLines0 = lines.map {
            val l1 = linePlusOne(it)
            val l2 = linePlusOne(l1)
            val l3 = linePlusOne(l2)
            val l4 = linePlusOne(l3)
            it + l1 + l2 + l3 + l4
        }
        val extendedLines1 = extendedLines0.map { linePlusOne(it) }
        val extendedLines2 = extendedLines1.map { linePlusOne(it) }
        val extendedLines3 = extendedLines2.map { linePlusOne(it) }
        val extendedLines4 = extendedLines3.map { linePlusOne(it) }
        return extendedLines0 + extendedLines1 + extendedLines2 + extendedLines3 + extendedLines4
    }

}
