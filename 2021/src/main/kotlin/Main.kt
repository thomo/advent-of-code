import days.Day13
import days.DayX
import java.io.File

fun main(args: Array<String>) {
    val app: DayX = Day13()
    val lines = readFileAsLinesUsingUseLines("inputd13.txt")

    val result = app.analyse(lines)
    println("1: $result")

    val result2 = app.analyse2(lines)
    println("2: $result2")
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
