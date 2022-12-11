package aoc2020

import aoc.days.DayXX

class Day02 : DayXX {
	override fun analyse(lines: List<String>): Any {
		TODO("Not yet implemented")
		//import java.io.File
//import kotlin.test.assertTrue
//
//fun aoc2020.main(args: Array<String>) {
//    val lines = readFileAsLinesUsingUseLines("input.txt")
//    val filtered = lines.map{line -> analyeLine(line)}
//    println(filtered)
//    println(filtered.filter{it}.size)
//}
//
//fun analyeLine(line:String): Boolean {
//    val parts = line.split(" ")
//    val char = parts[1][0]
//    val dims = parts[0].split("-")
//    val reg = Regex("^[^"+char+"]*("+char+"[^"+char+"]*){"+dims[0].toInt()+","+dims[1].toInt()+"}[^"+char+"]*$")
//    return reg.matches(parts[2])
//}
//
//fun readFileAsLinesUsingUseLines(fileName: String): List<String>
//    = File(fileName).useLines { it.toList() }

	}

	override fun analyse2(lines: List<String>): Any {
		TODO("Not yet implemented")
		//import java.io.File
//import kotlin.test.assertTrue
//
//fun aoc2020.main(args: Array<String>) {
//    val lines = readFileAsLinesUsingUseLines("input.txt")
//    val filtered = lines.map{line -> analyeLine2(line)}
//    println(filtered)
//    println(filtered.filter{it}.size)
//}
//
//fun analyeLine2(line:String): Boolean {
//    val parts = line.split(" ")
//    val char = parts[1][0]
//    val pos = parts[0].split("-").map{it.toInt()}
//    val pw = parts[2]
//
//    return (pw[pos[0]-1] == char).xor(pw[pos[1]-1] == char)
//}
//

	}
}
