package aoc2018

import aoc.util.dd.Coord
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day11Test {

	private val cut = Day11()

	@ParameterizedTest
	@CsvSource(
			"122,79,57,-5",
			"217,196,39,0",
			"101,153,71,4"
	)
	internal fun calcPowerLevel(x: Int, y: Int, serial: Int, expected: Int) {
		assertEquals(expected, cut.powerLevel(x, y, serial))
	}

	@Test
	fun t1() {
		val field = (
				listOf(-3, 4, 2, 2, 2).mapIndexed { i, v -> Pair(Coord(i + 1, 1), v) } +
						listOf(-4, 4, 3, 3, 4).mapIndexed { i, v -> Pair(Coord(i + 1, 2), v) } +
						listOf(-5, 3, 3, 4, -4).mapIndexed { i, v -> Pair(Coord(i + 1, 3), v) } +
						listOf(4, 3, 3, 4, -3).mapIndexed { i, v -> Pair(Coord(i + 1, 4), v) } +
						listOf(3, 3, 3, -5, -1).mapIndexed { i, v -> Pair(Coord(i + 1, 5), v) }
				).toMap()
		assertEquals(3 + 4 + 4 + 3 + 3, cut.edgePower(field, 2, 2, 3))
	}

	@Test
	fun analyse() {
		assertEquals(Coord(33, 45), cut.solve1(18, 300, 3))
	}

	@Test
	fun analyse2() {
		assertEquals(listOf(232, 251, 12), cut.solve2(42, 300))
	}
}