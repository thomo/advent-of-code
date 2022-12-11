import aoc.util.readFileAsLinesUsingUseLines
import aoc2021.days.Day19

fun main() {
	val app = Day19()
	val lines = readFileAsLinesUsingUseLines("aoc2021", "inputd19.txt")

	val startTime = System.currentTimeMillis()
	val result = app.analyse(lines)
	println("result 1: $result")
	println("analysis1 duration: " + (System.currentTimeMillis() - startTime))
	println("---------------------------------------------")

	val startTime2 = System.currentTimeMillis()
	val result2 = app.analyse2(lines)
	println("result 2: $result2")
	println("analysis2 duration: " + (System.currentTimeMillis() - startTime2))
}
