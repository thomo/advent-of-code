package aoc2022.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class DayXXTest {
	private var cut = Day01()

	private val input = listOf(
		""
	)

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	fun analyse() {
		assertEquals(0, cut.analyse(input))
	}

	@Test
	fun analyse2() {
		assertEquals(0, cut.analyse2(input))
	}
}
