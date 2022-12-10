package aoc2021.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day13Test {
    private lateinit var cut: Day13

    val input = listOf(
            "6,10",
            "0,14",
            "9,10",
            "0,3",
            "10,4",
            "4,11",
            "6,0",
            "6,12",
            "4,1",
            "0,13",
            "10,12",
            "3,4",
            "3,0",
            "8,4",
            "1,10",
            "2,14",
            "8,10",
            "9,0",
            "",
            "fold along y=7",
            "fold along x=5"
    )

    @BeforeEach
    fun setup() {
        cut = Day13()
    }

    // 01234567890
    // xx...|x.x..  -> xxx.x
    @Test
    fun foldField() {
        assertEquals(
                setOf(Pair(0, 0), Pair(0, 1), Pair(0, 2), Pair(0, 4)),
                cut.foldHorizontal(setOf(Pair(0, 0), Pair(0, 1), Pair(0, 6), Pair(0, 8)), 5)
        )
    }

    @Test
    fun analyse() {
        assertEquals(17, cut.analyse(input))
    }

    @Test
    fun analyse2() {
        assertEquals(0, cut.analyse2(input))
    }
}
