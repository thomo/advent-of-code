package aoc2022.days

import aoc.util.dd.Coord
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Day09Test {
	private var cut = Day09()

	private val input = listOf(
		"R 4",
		"U 4",
		"L 3",
		"D 1",
		"R 4",
		"D 1",
		"L 5",
		"R 2"
	)

	private val inputLong = listOf(
		"R 5",
		"U 8",
		"L 8",
		"D 3",
		"R 17",
		"D 10",
		"L 25",
		"U 20"
	)

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	internal fun moveDiagonal1() {
		assertEquals(Coord(3, 3), cut.moveTail(Coord(3, 4), Coord(2, 2)))
	}

	@Test
	internal fun moveDiagonal2() {
		assertEquals(Coord(3, 3), cut.moveTail(Coord(4, 3), Coord(2, 2)))
	}

	@Test
	fun analyse() {
		assertEquals(13, cut.analyse(input))
	}

	@Test
	fun analyse2() {
		assertEquals(1, cut.analyse2(input))
	}

	@Test
	fun analyse2_long() {
		assertEquals(36, cut.analyse2(inputLong))
	}

}
