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
	fun analyse() {
		assertEquals(Coord(33, 45), cut.a1(18, 300, 3))
	}

	@Test
	fun analyse2() {
		TODO()
		//assertEquals(listOf(232, 251, 12), cut.a2(42, 300))
	}
}