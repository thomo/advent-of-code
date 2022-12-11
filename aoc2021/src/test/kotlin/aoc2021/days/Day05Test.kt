package aoc2021.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.awt.Point

internal class Day05Test {
    private lateinit var cut: Day05

    val input = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2"
    )

    @BeforeEach
    fun setup() {
        cut = Day05()
    }

    @Test
    fun convertLine() {
        assertEquals(Pair(Point(0, 9), Point(5, 9)), cut.convertLine(input[0]))
    }

    @Test
    fun getPointsOfHorizontal() {
        assertEquals(
            setOf(Point(0, 9), Point(1, 9), Point(2, 9), Point(3, 9), Point(4, 9), Point(5, 9)),
            cut.getPoints(Point(0, 9), Point(5, 9))
        )
    }

    @Test
    fun getPointsOfVertical() {
        assertEquals(
            setOf(Point(3, 2), Point(3, 3), Point(3, 4), Point(3, 5)),
            cut.getPoints(Point(3, 2), Point(3, 5))
        )
    }

    @Test
    fun getPointsOfDiagonal() {
        assertEquals(
            setOf(Point(3, 2), Point(4, 3), Point(5, 4), Point(6, 5)),
            cut.getPoints(Point(3, 2), Point(6, 5))
        )
    }

    @Test
    fun analyse() {
        assertEquals(5, cut.analyse(input))
    }

    @Test
    fun analyse2() {
        assertEquals(12, cut.analyse2(input))
    }
}
