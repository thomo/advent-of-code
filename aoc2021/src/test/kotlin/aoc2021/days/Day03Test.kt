package aoc2021.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day03Test {
    private lateinit var cut: Day03

    val input = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )

    @BeforeEach
    fun setup() {
        cut = Day03()
    }

    @Test
    fun bitGravity() {
        assertEquals("10110", cut.bitGravity(input))
    }

    @Test
    fun gammaRate() {
        assertEquals(22, cut.gammaRate("10110"))
    }

    @Test
    fun epsilonRate() {
        assertEquals(9, cut.epsilonRate("10110"))
    }

    @Test
    fun analyse() {
        assertEquals(198, cut.analyse(input))
    }

    @Test
    fun co2scrubberRate() {
        assertEquals(10, cut.co2scrubberRate(input))
    }

    @Test
    fun oxygenGeneratorRate() {
        assertEquals(23, cut.oxygenGeneratorRate(input))
    }

    @Test
    fun filterLinesBy0() {
        val result = cut.filterLines(input, 2, '0')
        assertEquals(listOf("10000", "11001", "00010", "01010"), result)
    }

    @Test
    fun filterLinesBy1() {
        val result = cut.filterLines(input, 0, '1')
        assertEquals(listOf("11110", "10110", "10111", "10101", "11100", "10000", "11001"), result)
    }

    @Test
    fun analyse2() {
        assertEquals(230, cut.analyse2(input))
    }
}
