package aoc2022.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day03Test {
	private lateinit var cut: Day03

	@BeforeEach
	internal fun setUp() {
		cut = Day03()
	}

	@Test
	fun prioritize() {
		assertEquals(1, cut.prioritize('a'))
		assertEquals(27, cut.prioritize('A'))
	}
}
