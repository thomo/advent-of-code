package aoc2018

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day14Test {

	private val cut = Day14()

	@Test
	fun getScores2() {
		assertEquals(Triple(listOf(3, 7), 0, 1), cut.receipes(2))
	}

	@Test
	fun getScores4() {
		assertEquals(Triple(listOf(3, 7, 1, 0), 0, 1), cut.receipes(4))
	}

	@Test
	fun analyse_5() {
		assertEquals("0124515891", cut.analyse(listOf("5")))
	}

	@Test
	fun analyse_9() {
		assertEquals("5158916779", cut.analyse(listOf("9")))
	}

	@Test
	fun analyse2_51589() {
		assertEquals(9, cut.analyse2(listOf("51589")))
	}

	@Test
	fun analyse2_59414() {
		assertEquals(2018, cut.analyse2(listOf("59414")))
	}

}