package aoc2018

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day02Test {
	private val cut = Day02()

	private val input = listOf(
			"abcdef",
			"bababc",
			"abbcde",
			"abcccd",
			"aabcdd",
			"abcdee",
			"ababab",
	)

	private val input2 = listOf(
			"abcde",
			"fghij",
			"klmno",
			"pqrst",
			"fguij",
			"axcye",
			"wvxyz"
	)

	@Test
	fun analyse() {
		assertEquals(12, cut.analyse(input))
	}

	@Test
	fun analyse2() {
		assertEquals("fgij", cut.analyse2(input2))
	}
}