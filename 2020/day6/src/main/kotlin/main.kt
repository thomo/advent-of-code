import java.io.File

fun main(args: Array<String>) {
    var lines = readFileAsLinesUsingUseLines("input.txt")
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String>
    = File(fileName).useLines { it.toList() }