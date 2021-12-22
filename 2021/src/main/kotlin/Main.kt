import days.Day17
import java.io.File

fun main(args: Array<String>) {
    val app = Day17()
    val lines = readFileAsLinesUsingUseLines("inputd17.txt")

    val result = app.analyse(lines)
    println("1: $result")

    val result2 = app.analyse2(lines)
    println("2: $result2")
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
