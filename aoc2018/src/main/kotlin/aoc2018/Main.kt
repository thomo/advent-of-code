package aoc2018

import aoc.util.readFileAsLinesUsingUseLines

fun main() {
	val app = Day03()
	val lines = readFileAsLinesUsingUseLines("aoc2018", "inputd03.txt")

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
