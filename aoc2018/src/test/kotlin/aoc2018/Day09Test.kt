package aoc2018

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day09Test {

	private val cut = Day09()

	private val testInput = listOf("9 players; last marble is worth 25 points")

	@BeforeEach
	internal fun setUp() {
	}

	@ParameterizedTest
	@CsvSource(
			"9 players; last marble is worth 25 points, 32",
			"10 players; last marble is worth 1618 points, 8317",
			"13 players; last marble is worth 7999 points, 146373",
			"17 players; last marble is worth 1104 points, 2764",
			"21 players; last marble is worth 6111 points, 54718",
			"30 players; last marble is worth 5807 points, 37305",
			"462 players; last marble is worth 71938 points, 398371"
	)
	fun analyse(input: String, expected: Long) {
		assertEquals(expected, cut.analyse(listOf(input)))
	}

	@Test
	fun analyse2() {
		assertEquals(22563L, cut.analyse2(testInput))
	}
}