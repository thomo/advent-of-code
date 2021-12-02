import days.Day01
import days.Day02
import java.io.File

fun main(args: Array<String>) {
    val app = Day02()

    app.run(readFileAsLinesUsingUseLines("inputd02.txt"))
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String>
    = File(fileName).useLines { it.toList() }
