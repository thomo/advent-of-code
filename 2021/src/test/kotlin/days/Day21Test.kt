package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day21Test {
    private lateinit var cut: Day21

    val input = listOf(
        "Player 1 starting position: 4",
        "Player 2 starting position: 8"
    )

    @BeforeEach
    fun setup() {
        cut = Day21()
    }

    @Test
    internal fun parse() {
        val (p1, p2) = cut.parse(input)
        assertEquals(4, p1)
        assertEquals(8, p2)
    }

    @ParameterizedTest(name = "start at {0}, dice is {1}, stops at {2}")
    @CsvSource(
        "4, 0, 0",
        "10,6,4",
        "3, 9, 6",
        "4, 90, 0"
    )
    fun roll(start: Int, dice: Int, end: Int) {
        val result = cut.roll(start, dice)
        assertEquals(end, result)
    }

    @Test
    fun analyse() {
        assertEquals(739785, cut.analyse(input))
    }

    @Test
    fun analyse2() {
        assertEquals(444356092776315L, cut.analyse2(input))
    }
}
