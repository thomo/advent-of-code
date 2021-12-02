import java.io.File
import kotlin.test.assertTrue

fun main(args: Array<String>) {
    val lines = readFileAsLinesUsingUseLines("input.txt")
    val filtered = lines.map{line -> analyeLine2(line)}
    println(filtered)
    println(filtered.filter{it}.size)
}

fun analyeLine2(line:String): Boolean {
    val parts = line.split(" ")
    val char = parts[1][0]
    val pos = parts[0].split("-").map{it.toInt()}
    val pw = parts[2]

    return (pw[pos[0]-1] == char).xor(pw[pos[1]-1] == char)
}

