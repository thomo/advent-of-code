package aoc2020

class Day22 {
	private val rounds: MutableList<String> = mutableListOf()

	fun run(lines: List<String>) {
		var (p1, p2) = parseInput(lines)

		while (p1.isNotEmpty() && p2.isNotEmpty()) {
			var p = playRound(p1, p2)
			p1 = p.first
			p2 = p.second
		}

		println(winnerScore(p1, p2))

	}

	private fun winnerScore(p1: List<Int>, p2: List<Int>) =
		(if (p1.isNotEmpty()) p1 else p2).reversed().mapIndexed { index, i -> (index + 1) * i }.sum()

	private fun playRound(p1: List<Int>, p2: List<Int>): Pair<List<Int>, List<Int>> {
		val c1 = p1[0]
		val c2 = p2[0]
		if (c1 > c2) {
			return Pair(p1.drop(1) + c1 + c2, p2.drop(1))
		} else {
			return Pair(p1.drop(1), p2.drop(1) + c2 + c1)
		}
	}

	fun run2(lines: List<String>) {
		var (p1, p2) = parseInput(lines)

		while (!rounds.contains(toRound(p1, p2))) {

			rounds.add(toRound(p1, p2))
		}

		println(winnerScore(p1, p2))
	}

	private fun toRound(p1: List<Int>, p2: List<Int>) =
		p1.joinToString(" ") + "|" + p2.joinToString(" ")

	private fun parseInput(lines: List<String>) =
		lines.drop(1).joinToString(" ").split(Regex("  Player 2: ")).map { it.split(" ").map { i -> i.toInt() } }

}
