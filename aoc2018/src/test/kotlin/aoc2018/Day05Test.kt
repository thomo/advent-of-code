package aoc2018

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day05Test {

	private val cut = Day05()
	private val line: String = "dabAcCaCBAcCcaDA"
	private val testInput = listOf(line)

	@Test
	internal fun reduceLine() {
		assertEquals("c", cut.reduceLine("aAbBc"))
		assertEquals("cc", cut.reduceLine("aAccbB"))
	}

	@Test
	internal fun reducePairs() {
		assertEquals("c", cut.reducePairs("aAcbB"))
		assertEquals("cc", cut.reducePairs("aAcbBc"))
	}


	@Test
	fun analyse() {
		assertEquals(10, cut.analyse(testInput))
	}

	@Test
	fun analyse2() {
		assertEquals(4, cut.analyse2(testInput))
	}

}