package aoc2022.days

import aoc.days.DayXX

class Day04 : DayXX {
	private fun parse(line: String) = line
		.split(",", "-")
		.map { it.toInt() }
		.run { listOf(IntRange(this[0], this[1]), IntRange(this[2], this[3])) }

	private fun overlap(ranges: List<IntRange>) =
		doesAcontainsB(ranges[0], ranges[1]) || doesAcontainsB(ranges[1], ranges[0])

	private fun doesAcontainsB(a: IntRange, b: IntRange) = a.contains(b.first) && a.contains(b.last)

	private fun overlapPartial(ranges: List<IntRange>) =
		ranges[0].contains(ranges[1].first) ||
			ranges[0].contains(ranges[1].last) ||
			ranges[1].contains(ranges[0].first) ||
			ranges[1].contains(ranges[0].last)

	override fun analyse(lines: List<String>) = lines
		.map { parse(it) }
		.filter { overlap(it) }
		.count()

	override fun analyse2(lines: List<String>) = lines
		.map { parse(it) }
		.filter { overlapPartial(it) }
		.count()

}
