package aoc2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Day01Test {
	private var cut = Day01()

	private val input = listOf(
		"1721",
		"979",
		"366",
		"299",
		"675",
		"1456"
	)

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	fun analyse() {
		assertEquals(514579, cut.analyse(input))
	}

	@Test
	fun analyse2() {
		assertEquals(241861950, cut.analyse2(input))
	}
}
