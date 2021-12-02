package days

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day01Test {
    private lateinit var cut: Day01

    val input = listOf(
        199,
        200,
        208,
        210,
        200,
        207,
        240,
        269,
        260,
        263
    )

    @BeforeEach
    fun setup() {
        cut = Day01()
    }

    @Test
    fun example1() {
        assertEquals(7, cut.analyze(input))
    }

    @Test
    fun example2() {
        assertEquals(5, cut.analyze2(input))
    }

}
