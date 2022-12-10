import days.*
import java.io.File

fun main() {
	val app = Day22()
	val lines = readFileAsLinesUsingUseLines("inputd11.txt")

	val startTime = System.currentTimeMillis()
	val result = app.analyse(lines)
	println("result 1: $result")
	println("analysis1 duration: " + (System.currentTimeMillis() - startTime))
	println("---------------------------------------------")

	val startTime2 = System.currentTimeMillis()
	val result2 = app.analyse2(lines)
	println("result 2: $result2")
	println("analysis2 duration: " + (System.currentTimeMillis() - startTime2))
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
