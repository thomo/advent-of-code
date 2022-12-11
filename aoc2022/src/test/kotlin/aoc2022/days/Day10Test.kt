package aoc2022.days

import aoc.util.dd.Coord
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day10Test {
	private var cut = Day10()

	private val input = listOf(
		"addx 15",
		"addx -11",
		"addx 6",
		"addx -3",
		"addx 5",
		"addx -1",
		"addx -8",
		"addx 13",
		"addx 4",
		"noop",
		"addx -1",
		"addx 5",
		"addx -1",
		"addx 5",
		"addx -1",
		"addx 5",
		"addx -1",
		"addx 5",
		"addx -1",
		"addx -35",
		"addx 1",
		"addx 24",
		"addx -19",
		"addx 1",
		"addx 16",
		"addx -11",
		"noop",
		"noop",
		"addx 21",
		"addx -15",
		"noop",
		"noop",
		"addx -3",
		"addx 9",
		"addx 1",
		"addx -3",
		"addx 8",
		"addx 1",
		"addx 5",
		"noop",
		"noop",
		"noop",
		"noop",
		"noop",
		"addx -36",
		"noop",
		"addx 1",
		"addx 7",
		"noop",
		"noop",
		"noop",
		"addx 2",
		"addx 6",
		"noop",
		"noop",
		"noop",
		"noop",
		"noop",
		"addx 1",
		"noop",
		"noop",
		"addx 7",
		"addx 1",
		"noop",
		"addx -13",
		"addx 13",
		"addx 7",
		"noop",
		"addx 1",
		"addx -33",
		"noop",
		"noop",
		"noop",
		"addx 2",
		"noop",
		"noop",
		"noop",
		"addx 8",
		"noop",
		"addx -1",
		"addx 2",
		"addx 1",
		"noop",
		"addx 17",
		"addx -9",
		"addx 1",
		"addx 1",
		"addx -3",
		"addx 11",
		"noop",
		"noop",
		"addx 1",
		"noop",
		"addx 1",
		"noop",
		"noop",
		"addx -13",
		"addx -19",
		"addx 1",
		"addx 3",
		"addx 26",
		"addx -30",
		"addx 12",
		"addx -1",
		"addx 3",
		"addx 1",
		"noop",
		"noop",
		"noop",
		"addx -9",
		"addx 18",
		"addx 1",
		"addx 2",
		"noop",
		"noop",
		"addx 9",
		"noop",
		"noop",
		"noop",
		"addx -1",
		"addx 2",
		"addx -37",
		"addx 1",
		"addx 3",
		"noop",
		"addx 15",
		"addx -21",
		"addx 22",
		"addx -6",
		"addx 1",
		"noop",
		"addx 2",
		"addx 1",
		"noop",
		"addx -10",
		"noop",
		"noop",
		"addx 20",
		"addx 1",
		"addx 2",
		"addx 2",
		"addx -6",
		"addx -11",
		"noop",
		"noop",
		"noop"
	)

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	fun analyse() {
		assertEquals(13140L, cut.analyse(input))
	}

	@Test
	internal fun toCrt() {
		assertEquals(Coord(0, 0), cut.toCrtPos(1))
		assertEquals(Coord(39, 0), cut.toCrtPos(40))
		assertEquals(Coord(39, 3), cut.toCrtPos(160))
	}

	@Test
	fun analyse2() {
		val expected = listOf(
			"##..##..##..##..##..##..##..##..##..##..",
			"###...###...###...###...###...###...###.",
			"####....####....####....####....####....",
			"#####.....#####.....#####.....#####.....",
			"######......######......######......####",
			"#######.......#######.......#######....."
		)
		assertEquals(expected, cut.analyse2(input))
	}

}
