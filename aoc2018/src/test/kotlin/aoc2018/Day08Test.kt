package aoc2018

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day08Test {

	private val cut = Day08()

	private val testInput = listOf("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2")

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	fun analyse() {
		assertEquals(138, cut.analyse(testInput))
	}

	@Test
	fun analyse2() {
		assertEquals(66, cut.analyse2(testInput))
	}
}