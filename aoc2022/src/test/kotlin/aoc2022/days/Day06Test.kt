package aoc2022.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day06Test {
	private var cut = Day06()

	private val input = listOf(
		"mjqjpqmgbljsphdztnvjfqwrcgsmlb"
	)

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	fun analyse() {
		assertEquals(7, cut.analyse(input))
	}

	@Test
	fun analyse_2() {
		assertEquals(10, cut.analyse(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")))

	}

	@Test
	fun analyse2() {
		assertEquals(19, cut.analyse2(input))
	}
}
