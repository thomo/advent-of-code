package aoc

import aoc.days.Day01
import java.io.File


fun main() {
    val app = Day01()
    val lines = readFileAsLinesUsingUseLines("inputd01.txt")

    val result = app.analyse(lines)
    println("1: $result")

    val result2 = app.analyse2(lines)
    println("2: $result2")
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }