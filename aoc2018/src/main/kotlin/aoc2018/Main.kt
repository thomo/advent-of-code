package aoc2018

import aoc.util.readFileAsLinesUsingUseLines

fun main() {
	val app = Day11()
	val lines = readFileAsLinesUsingUseLines("aoc2018", "inputd11.txt")

	val startTime = System.currentTimeMillis()
	val result = app.analyse(lines)
	println("result 1: $result")
	println("analysis1 duration: " + ((System.currentTimeMillis() - startTime) / 1000.0) + " sec")
	println("---------------------------------------------")

	val startTime2 = System.currentTimeMillis()
	val result2 = app.analyse2(lines)
	println("result 2: $result2")
	println("analysis2 duration: " + ((System.currentTimeMillis() - startTime2) / 1000.0) + " sec")
}
