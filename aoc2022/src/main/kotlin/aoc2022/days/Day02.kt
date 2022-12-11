package aoc2022.days

import aoc.days.DayXX

class Day02 : DayXX {
	private fun replace(x: Char) = when (x) {
		'A', 'X' -> 'R'
		'B', 'Y' -> 'P'
		'C', 'Z' -> 'S'
		else -> throw IllegalArgumentException("$x unknown")
	}

	private fun replace2(x: Char) = when (x) {
		'A' -> 'R'
		'B' -> 'P'
		'C' -> 'S'
		'X' -> 'L'
		'Y' -> 'D'
		'Z' -> 'W'
		else -> throw IllegalArgumentException("$x unknown")
	}

	private fun play(g: Pair<Char, Char>) = when (g) {
		Pair('R', 'R') -> 1 + 3
		Pair('R', 'P') -> 2 + 6
		Pair('R', 'S') -> 3 + 0
		Pair('P', 'R') -> 1 + 0
		Pair('P', 'P') -> 2 + 3
		Pair('P', 'S') -> 3 + 6
		Pair('S', 'R') -> 1 + 6
		Pair('S', 'P') -> 2 + 0
		Pair('S', 'S') -> 3 + 3
		else -> throw IllegalArgumentException("illegal pair $g")
	}

	private fun play2(g: Pair<Char, Char>) = when (g) {
		Pair('R', 'L') -> play(Pair('R', 'S'))
		Pair('R', 'D') -> play(Pair('R', 'R'))
		Pair('R', 'W') -> play(Pair('R', 'P'))
		Pair('P', 'L') -> play(Pair('P', 'R'))
		Pair('P', 'D') -> play(Pair('P', 'P'))
		Pair('P', 'W') -> play(Pair('P', 'S'))
		Pair('S', 'L') -> play(Pair('S', 'P'))
		Pair('S', 'D') -> play(Pair('S', 'S'))
		Pair('S', 'W') -> play(Pair('S', 'R'))
		else -> throw IllegalArgumentException("illegal pair $g")
	}

	private fun convertToPair(line: String) = Pair(line[0], line[2])

	override fun analyse(lines: List<String>) = lines
		.map { convertToPair(it) }
		.map { Pair(replace(it.first), replace(it.second)) }
		.map { play(it) }
		.sum()

	override fun analyse2(lines: List<String>) = lines
		.map { convertToPair(it) }
		.map { Pair(replace2(it.first), replace2(it.second)) }
		.map { play2(it) }
		.sum()
}
