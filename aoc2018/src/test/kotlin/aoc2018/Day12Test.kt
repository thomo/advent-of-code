package aoc2018

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day12Test {

	private val cut = Day12()

	private val testInput = listOf(
			"initial state: #..#.#..##......###...###",
			"",
			"...## => #",
			"..#.. => #",
			".#... => #",
			".#.#. => #",
			".#.## => #",
			".##.. => #",
			".#### => #",
			"#.#.# => #",
			"#.### => #",
			"##.#. => #",
			"##.## => #",
			"###.. => #",
			"###.# => #",
			"####. => #"
	)

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	fun analyse() {
		assertEquals(325L, cut.analyse(testInput))
	}

	@Test
	fun analyse2_run88() {
		assertEquals(1134L, cut.run2(testInput, 88))
	}

	@Test
	fun analyse2_run89() {
		assertEquals(1154L, cut.run2(testInput, 89))
	}

	@Test
	fun analyse2_run90() {
		assertEquals(1174L, cut.run2(testInput, 90))
	}

	@Test
	fun analyse2_run200() {
		assertEquals(3374L, cut.run2(testInput, 200))
	}

	@Test
	fun analyse2_run500() {
		assertEquals(9374L, cut.run2(testInput, 500))
	}

	@Test
	fun analyse2() {
		assertEquals(999999999374L, cut.analyse2(testInput))
	}
}