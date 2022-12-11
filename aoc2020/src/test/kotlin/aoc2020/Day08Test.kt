package aoc2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day08Test {
	private var cut = Day08()

	private val input = listOf(
			"nop +0",
			"acc +1",
			"jmp +4",
			"acc +3",
			"jmp -3",
			"acc -99",
			"acc +1",
			"jmp -4",
			"acc +6"
	)

	@Test
	fun analyse() {
		assertEquals(5, cut.analyse(input))
	}

	@Test
	fun analyse2() {
		assertEquals(8, cut.analyse2(input))
	}
}