package aoc2021.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day17Test {
    private lateinit var cut: Day17

    val input = listOf(
        "target area: x=20..30, y=-10..-5"
    )

    @BeforeEach
    fun setup() {
        cut = Day17()
    }

    @Test
    internal fun shouldExtractRangesFromInput() {
        val result = cut.parseLine(input[0])
        assertEquals(20..30, result.x)
        assertEquals(-10..-5, result.y)
    }

    @Test
    internal fun shouldCalcXCandidates() {
        val result = cut.collectX(20..30)
        assertEquals(listOf(6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30), result)
    }

    @Test
    internal fun shouldCalcYCandidates() {
        val result = cut.collectY(-10..-5)
        assertEquals(listOf(-10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9), result)
    }

    @Test
    fun analyse() {
        assertEquals(45, cut.analyse(input))
    }

    @Test
    fun analyse2() {
        assertEquals(112, cut.analyse2(input))
    }
}
