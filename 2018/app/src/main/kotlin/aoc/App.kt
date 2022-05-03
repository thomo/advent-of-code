package aoc

import aoc.days.Day02
import java.io.File


fun main() {
  val app = Day02()
  val lines = readFileAsLinesUsingUseLines("inputd02.txt")

  val result = app.analyse(lines)
  println("1: $result")

  val result2 = app.analyse2(lines)
  println("2: $result2")
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }