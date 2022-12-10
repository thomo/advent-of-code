package aoc2022.days

import aoc.days.DayXX

class Day03 : DayXX {
	fun prioritize(c: Char) =
		if (c in 'a'..'z')
			c.code - 'a'.code + 1
		else
			c.code - 'A'.code + 27

	private fun findSharedType(parts: Pair<String, String>) =
		getChars(parts.first).intersect(
			getChars(parts.second)
		).first()

	private fun getChars(txt: String) = txt.toCharArray().toSet()

	private fun splitInTwo(line: String) = Pair(line.take(line.length / 2), line.drop(line.length / 2))

	private fun getCommon(chunk: List<String>) =
		getChars(chunk[0]).intersect(getChars(chunk[1])).intersect(getChars(chunk[2])).first()

	override fun analyse(lines: List<String>) = lines
		.map { splitInTwo(it) }
		.map { findSharedType(it) }
		.map { prioritize(it) }
		.sum()

	override fun analyse2(lines: List<String>) =
		lines
			.chunked(3)
			.map { getCommon(it) }
			.map { prioritize(it) }
			.sum()

}
