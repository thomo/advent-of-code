package aoc2022.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day11Test {
	private var cut = Day11()

	private val input = listOf(
		""
	)

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	fun analyse() {
		assertEquals("", cut.analyse(input))
	}

	@Test
	fun analyse2() {
		assertEquals("", cut.analyse2(input))
	}
}
