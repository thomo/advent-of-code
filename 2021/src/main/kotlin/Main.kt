import days.Day12
import days.DayX
import java.io.File

fun main(args: Array<String>) {
    val app: DayX = Day12()
    val lines = readFileAsLinesUsingUseLines("inputd12.txt")

    val result = app.analyse(lines)
    println("1: $result")

    val result2 = app.analyse2(lines)
    println("2: $result2")
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
