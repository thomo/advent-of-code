package aoc2021.days

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class Day02Test {
    private lateinit var cut: Day02

    val input = listOf(
"forward 5",
"down 5",
"forward 8",
"up 3",
"down 8",
"forward 2",
    )

    @BeforeEach
    fun setup() {
        cut = Day02()
    }

    @Test
    fun analyse() {
        assertEquals(cut.analyse(input), 150)
    }
    @Test
    fun analyse2() {
        assertEquals(cut.analyse2(input), 900)
    }
}
