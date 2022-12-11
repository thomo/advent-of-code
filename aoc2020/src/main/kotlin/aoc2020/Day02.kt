package aoc2020

import aoc.days.DayXX

class Day02 : DayXX {

	// 4-11 z: lwnzgzlmpvz
	fun analyseLine(line: String): Boolean {
		val parts = line.split(" ")
		val dims = parts[0].split("-").map { it.toInt() }
		val char = parts[1][0]
		val reg = Regex("^[^" + char + "]*(" + char + "[^" + char + "]*){" + dims[0] + "," + dims[1] + "}[^" + char + "]*$")
		return reg.matches(parts[2])
	}

	// 506
	override fun analyse(lines: List<String>): Any {
		val filtered = lines.map { line -> analyseLine(line) }
		println(filtered)
		return filtered.filter { it }.size
	}


	fun analyseLine2(line: String): Boolean {
		val parts = line.split(" ")
		val pos = parts[0].split("-").map { it.toInt() }
		val char = parts[1][0]
		val pw = parts[2]

		return (pw[pos[0] - 1] == char).xor(pw[pos[1] - 1] == char)
	}

	// 443
	override fun analyse2(lines: List<String>): Any {
		val filtered = lines.map { line -> analyseLine2(line) }
		println(filtered)
		return filtered.filter { it }.size
	}
}
