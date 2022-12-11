package aoc2020

import aoc.days.DayXX
import aoc.util.chunkedByEmptyLines

class Day06 : DayXX {
	// 7128
	override fun analyse(lines: List<String>) = chunkedByEmptyLines(lines)
			.map { it.joinToString("").toCharArray().toSet().size }
			.sum()

	// 3640
	override fun analyse2(lines: List<String>) = chunkedByEmptyLines(lines)
			.map { it.fold(it[0].toCharArray().toSet()) { inter, a -> inter.intersect(a.toCharArray().toSet()) } }
			.map { it.size }
			.sum()

}
