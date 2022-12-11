package aoc2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day06Test {
	private var cut = Day06()

	private val input = listOf(
			"abc",
			"",
			"a",
			"b",
			"c",
			"",
			"ab",
			"ac",
			"",
			"a",
			"a",
			"a",
			"a",
			"",
			"b"
	)

	@Test
	fun analyse() {
		assertEquals(11, cut.analyse(input))
	}

	@Test
	fun analyse2() {
		assertEquals(6, cut.analyse2(input))
	}
}

