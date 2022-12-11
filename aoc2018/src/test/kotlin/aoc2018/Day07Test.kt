package aoc2018

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day07Test {

	private val cut = Day07()

	private val testInput = listOf(
			"Step C must be finished before step A can begin.",
			"Step C must be finished before step F can begin.",
			"Step A must be finished before step B can begin.",
			"Step A must be finished before step D can begin.",
			"Step B must be finished before step E can begin.",
			"Step D must be finished before step E can begin.",
			"Step F must be finished before step E can begin."
	)

	@BeforeEach
	internal fun setUp() {
	}

	@Test
	fun analyse() {
		assertEquals("CABDFE", cut.analyse(testInput))
	}

	@Test
	fun analyse2() {
		assertEquals(15, cut.a2(testInput, 2, 0))
	}
}