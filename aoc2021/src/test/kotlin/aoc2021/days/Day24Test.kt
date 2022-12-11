package aoc2021.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day24Test {
    private lateinit var cut: Day24

    val input = listOf(
        ""
    )

    @BeforeEach
    fun setup() {
        cut = Day24()
    }

    @Test
    fun progNeg() {
        val prog = cut.parse(listOf("inp x", "mul x -1"))[0]
        val reg = cut.eval(prog, Day24.Register(0, 0, 0, 0), 10)
        assertEquals(-10, reg.x)
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
