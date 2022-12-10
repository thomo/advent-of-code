import java.io.File
import kotlin.test.assertTrue

fun main(args: Array<String>) {
    val lines = readFileAsLinesUsingUseLines("input.txt")
    val filtered = lines.map{line -> analyeLine(line)}
    println(filtered)
    println(filtered.filter{it}.size)
}

fun analyeLine(line:String): Boolean {
    val parts = line.split(" ")
    val char = parts[1][0]
    val dims = parts[0].split("-")
    val reg = Regex("^[^"+char+"]*("+char+"[^"+char+"]*){"+dims[0].toInt()+","+dims[1].toInt()+"}[^"+char+"]*$")
    return reg.matches(parts[2])
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String>
    = File(fileName).useLines { it.toList() }