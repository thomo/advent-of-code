package aoc2018

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Day01Test {

	private val cut = Day01()

	private val testInput = listOf(
			"+1",
			"-2",
			"+3",
			"+1"
	)

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	fun analyse() {
		assertEquals(3, cut.analyse(testInput))
	}

	@Test
	fun analyse2() {
		assertEquals(2, cut.analyse2(testInput))
	}
}