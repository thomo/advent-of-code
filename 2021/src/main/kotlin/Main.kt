import days.Day20
import java.io.File

fun main(args: Array<String>) {
    val app = Day20()
    val lines = readFileAsLinesUsingUseLines("inputd20.txt")

    val result = app.analyse(lines)
    println("1: $result")

    val result2 = app.analyse2(lines)
    println("2: $result2")
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
