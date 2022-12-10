package aoc2022.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day05Test {
	private var cut = Day05()

	private val input = listOf(
		"    [D]    ",
		"[N] [C]    ",
		"[Z] [M] [P]",
		" 1   2   3 ",
		"",
		"move 1 from 2 to 1",
		"move 3 from 1 to 3",
		"move 2 from 2 to 1",
		"move 1 from 1 to 2"
	)

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	fun analyse() {
		assertEquals("CMZ", cut.analyse(input))
	}

	@Test
	fun analyse2() {
		assertEquals("MCD", cut.analyse2(input))
	}
}
