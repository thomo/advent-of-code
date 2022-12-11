package aoc2018

import aoc.util.dd.Cell
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day06Test {
	private lateinit var cells: Set<Cell<Int>>

	private val cut = Day06()
	private val testInput = listOf(
			"1, 1",
			"1, 6",
			"8, 3",
			"3, 4",
			"5, 5",
			"8, 9")

	@BeforeEach
	internal fun setUp() {
		cells = cut.getStartCells(testInput)
	}

	@Test
	fun identifyInnerCells() {
		val ic = cut.getInnerCells(cells)
		assertEquals(2, ic.size)
	}

	@Test
	fun analyse() {
		assertEquals(17, cut.analyse(testInput))
	}

}