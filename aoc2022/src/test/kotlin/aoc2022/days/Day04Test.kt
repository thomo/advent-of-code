package aoc2022.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day04Test {
	private var cut = Day04()

	private val input = listOf(
		"2-4,6-8",
		"2-3,4-5",
		"5-7,7-9",
		"2-8,3-7",
		"6-6,4-6",
		"2-6,4-8"
	)

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	fun analyse() {
		assertEquals(2, cut.analyse(input))
	}

	@Test
	fun analyse2() {
		assertEquals(4, cut.analyse2(input))
	}
}
