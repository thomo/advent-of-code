package aoc2022.days

import aoc.days.DayXX

class Day01 : DayXX {
	override fun analyse(lines: List<String>): Any {
		var max = 0
		var sum = 0
		lines.forEach { line ->
			if (line.isBlank()) {
				max = if (sum > max) sum else max
				sum = 0
			} else sum += line.toInt()
		}
		return max
	}

	override fun analyse2(lines: List<String>): Any {
		var sums = mutableListOf<Int>()
		var sum = 0
		lines.forEach { line ->
			if (line.isBlank()) {
				sums.add(sum)
				sum = 0
			} else sum += line.toInt()
		}
		sums.sort()
		return sums.takeLast(3).sum()
	}
}
