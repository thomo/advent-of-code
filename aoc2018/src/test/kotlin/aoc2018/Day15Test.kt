package aoc2018

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day15Test {

	private val cut = Day15()

	private val testInput = listOf(
			"#######",
			"#.G...#",
			"#...EG#",
			"#.#.#G#",
			"#..G#E#",
			"#.....#",
			"#######"
	)

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	fun analyse_1() {
		assertEquals(27730, cut.analyse(listOf(
				"#######",
				"#.G...#",
				"#...EG#",
				"#.#.#G#",
				"#..G#E#",
				"#.....#",
				"#######"
		)))
	}

	@Test
	fun analyse_2() {
		assertEquals(18740, cut.analyse(listOf(
				"#########",
				"#G......#",
				"#.E.#...#",
				"#..##..G#",
				"#...##..#",
				"#...#...#",
				"#.G...G.#",
				"#.....G.#",
				"#########"
		)))
	}

	@Test
	fun analyse2() {
		assertEquals(4988, cut.analyse2(testInput))
	}
}