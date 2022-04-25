package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day25Test {
    private lateinit var cut: Day25

    val input = listOf(
        "v...>>.vv>",
        ".vv>>.vv..",
        ">>.>v>...v",
        ">>v>>.>.v.",
        "v>v.vv.v..",
        ">.>>..v...",
        ".vv..>.>v.",
        "v.v..>>v.v",
        "....v..v.>"
    )

    @BeforeEach
    fun setup() {
        cut = Day25()
    }

    @Test
    fun analyse() {
        assertEquals(58, cut.analyse(input))
    }

    @Test
    fun analyse2() {
        assertEquals(0, cut.analyse2(input))
    }
}
