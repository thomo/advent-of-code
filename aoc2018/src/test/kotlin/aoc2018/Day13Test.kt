package aoc2018

import aoc.util.dd.Coord
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day13Test {

	private val cut = Day13()

	private val testInput = listOf(
			"""/->-\        """,
			"""|   |  /----\""",
			"""| /-+--+-\  |""",
			"""| | |  | v  |""",
			"""\-+-/  \-+--/""",
			"""  \------/   """
	)


	private val testInput2 = listOf(
			"""/>-<\""",
			"""|   |""",
			"""| /<+-\""",
			"""| | | v""",
			"""\>+</ |""",
			"""  |   ^""",
			"""  \<->/""",
	)

	@BeforeEach
	internal fun setUp() {

	}

	@Test
	fun analyse() {
		assertEquals(Coord(7, 3), cut.analyse(testInput))
	}

	@Test
	fun analyse2() {
		assertEquals(Coord(6, 4), cut.analyse2(testInput2))
	}
}