package aoc2022.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day08Test {
	private var cut = Day08()

	private val input = listOf(
			"30373",
					"25512",
					"65332",
					"33549",
					"35390"
	)

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	fun analyse() {
		assertEquals(21, cut.analyse(input))
	}

	@Test
	fun analyse2() {
		assertEquals(8, cut.analyse2(input))
	}
}
