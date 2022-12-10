package aoc2021.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day06Test {
    private lateinit var cut: Day06

    val input = listOf(
        "3,4,3,1,2"
    )

    @BeforeEach
    fun setup() {
        cut = Day06()
    }

    @Test
    fun createSwarm() {
        assertEquals(
            listOf(Day06.Group(3, 2), Day06.Group(4, 1), Day06.Group(1, 1), Day06.Group(2, 1)),
            cut.createSwarm(input)
        )
    }

    @Test
    fun numberOfNew() {
        assertEquals(3, cut.numberOfNew(listOf(Day06.Group(0, 2), Day06.Group(4, 1), Day06.Group(0, 1))))
    }

    @Test
    fun createNew3() {
        assertEquals(Day06.Group(8, 3), cut.createNew(3))
    }

    @Test
    fun createNew0() {
        assertEquals(Day06.Group(8, 0), cut.createNew(0))
    }

    @Test
    fun passOneDay() {
        val input = listOf(
            Day06.Group(0, 2),
            Day06.Group(4, 1),
            Day06.Group(0, 1),
            Day06.Group(8, 6)
        )

        val expected = listOf(
            Day06.Group(6, 2),
            Day06.Group(3, 1),
            Day06.Group(6, 1),
            Day06.Group(7, 6)
        )
        assertEquals(expected, cut.passOneDay(input))
    }

    @Test
    fun analyse() {
        assertEquals(5934L, cut.analyse(input))
    }

    @Test
    fun analyse2() {
        assertEquals(26984457539L, cut.analyse2(input))
    }
}
