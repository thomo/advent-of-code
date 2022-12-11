package aoc2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day03Test {

	private var cut = Day03()

	val input = listOf(
			"..##.......",
			"#...#...#..",
			".#....#..#.",
			"..#.#...#.#",
			".#...##..#.",
			"..#.##.....",
			".#.#.#....#",
			".#........#",
			"#.##...#...",
			"#...##....#",
			".#..#...#.#"
	)

	@Test
	fun analyse() {
		assertEquals("7", "" + cut.analyse(input))
	}

	@Test
	fun analyse2() {
		assertEquals("336", "" + cut.analyse2(input))
	}
}