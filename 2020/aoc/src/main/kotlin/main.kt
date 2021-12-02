import days.*
import java.io.File

fun main(args: Array<String>) {
    val app = Day22()

    app.run2(readFileAsLinesUsingUseLines("inputd22.txt"))

    //d15 app.run("10,16,6,0,1,17")

}

fun readFileAsLinesUsingUseLines(fileName: String): List<String>
    = File(fileName).useLines { it.toList() }
