package aoc2021.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day07Test {
    private lateinit var cut: Day07

    val line = "16,1,2,0,4,2,7,1,2,14"
    val input = listOf(line)
    val positions = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)

    @BeforeEach
    fun setup() {
        cut = Day07()
    }

    @Test
    fun readHorizontalPositions() {
        assertEquals(positions, cut.readHorizontalPositions(line))
    }

    @Test
    fun calcTargetPositions3() {
        assertEquals(2, cut.calcTargetPositions(listOf(0, 2, 2, 2)))
    }

    @Test
    fun calcTargetPositions() {
        assertEquals(2, cut.calcTargetPositions(positions))
    }

    @Test
    fun calcFuel() {
        assertEquals(37, cut.calcFuel(positions, 2))
    }

    @Test
    fun calcFuel1() {
        assertEquals(41, cut.calcFuel(positions, 1))
    }

    @Test
    fun calcFuel10() {
        assertEquals(71, cut.calcFuel(positions, 10))
    }

    @Test
    fun analyse() {
        assertEquals(37, cut.analyse(input))
    }

    @Test
    fun calcFuel2_5() {
        assertEquals(168, cut.calcFuel2(positions, 5))
    }

    @Test
    fun calcFuel2_2() {
        assertEquals(206, cut.calcFuel2(positions, 2))
    }

    @Test
    fun analyse2() {
        assertEquals(168, cut.analyse2(input))
    }
}
