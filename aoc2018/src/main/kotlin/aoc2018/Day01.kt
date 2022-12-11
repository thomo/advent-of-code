package aoc2018

import aoc.days.DayXX

class Day01 : DayXX {
	// 508
	override fun analyse(lines: List<String>): Any {
		return lines.map { l -> l.toInt() }.fold(0) { akku, x -> akku + x }
	}

	// 549
	override fun analyse2(lines: List<String>): Any {
		val freqs = mutableSetOf<Int>()
		var start = 0
		do {
			start = lines.map { l -> l.toInt() }.fold(start) { akku, x ->
				val sum = akku + x
				val isNew = freqs.add(sum)
				if (!isNew) return sum
				sum
			}
		} while (true)
	}
}

