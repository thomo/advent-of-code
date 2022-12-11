package aoc2018

import aoc.days.DayXX
import java.util.stream.IntStream

class Day05 : DayXX {
	private fun reduceIfMatch(c1: Char, c2: Char) = if (sameCharButDifferentCase(c1, c2)) "" else "$c1$c2"

	private fun sameCharButDifferentCase(c1: Char, c2: Char) = c1.lowercase() == c2.lowercase() && c1 != c2

	fun reducePairs(line: String): String {
		val even = reduceLine(line) // remove all aA pairs which start at even indices 0,2,4,...
		return even[0] + reduceLine(even.drop(1)) // remove all aA pairs which start at odd indices 1,3,5 ...
	}

	fun reduceLine(line: String): String {
		val result = IntStream.range(0, line.length / 2)
				.mapToObj { idx -> reduceIfMatch(line[idx * 2], line[idx * 2 + 1]) }
				.toList()
				.joinToString("")
		return if (line.length % 2 == 1) result + line.last() else result
	}

	private fun reduceFull(start: String): String {
		var line: String
		var newLine = start

		do {
			line = newLine
			newLine = reducePairs(line)
		} while (newLine != line)

		return newLine
	}

	private fun removeUnit(line: String, c: String) = line.replace(c, "").replace(c.uppercase(), "")

	// 10978
	override fun analyse(lines: List<String>) = reduceFull(lines[0]).length

	// 4840
	override fun analyse2(lines: List<String>): Any {
		val line = lines[0]
		val units = line.asSequence().map { c -> c.lowercase() }.toSet()

		return units.map { c -> removeUnit(line, c) }
				.map { lineWoPair -> reduceFull(lineWoPair) }
				.map { reduced -> reduced.length }
				.minOf { x -> x }
	}

}
