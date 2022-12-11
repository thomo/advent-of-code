package aoc2018

import aoc.days.DayXX

class Day02 : DayXX {
	private fun replaceOneChar(line: String): List<String> {
		val result = mutableListOf<String>()
		line.forEachIndexed { idx, _ -> result.add(line.replaceRange(idx, idx + 1, "@")) }
		return result
	}

	// 4920
	override fun analyse(lines: List<String>): Any {
		val counts = lines.map { l ->
			l
					.asSequence()
					.groupBy { c -> c } // group by character
					.map { mapEntry -> mapEntry.value.size } // just keep how often a char occurs
		}
		val c2 = counts.filter { cnt -> cnt.contains(2) }.size
		val c3 = counts.filter { cnt -> cnt.contains(3) }.size
		return c2 * c3
	}

	// fonbwmjquwtapeyzikghtvdxl
	override fun analyse2(lines: List<String>) = lines
			.flatMap { line -> replaceOneChar(line) } // get list by removing one char from line
			.groupBy { reducedLine -> reducedLine } // group by itself
			.filter { entry -> entry.value.size == 2 } // keep the double ones
			.map { entry -> entry.key } // we need the key only
			.first()
			.replace("@", "")
}
