package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day23Test {
    private lateinit var cut: Day23

    val input = listOf(
        ""
    )

    @BeforeEach
    fun setup() {
        cut = Day23()
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
