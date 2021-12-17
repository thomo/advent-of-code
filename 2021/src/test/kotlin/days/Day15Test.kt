package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day15Test {
    private lateinit var cut: Day15

    val input = listOf(
            "1163751742",
            "1381373672",
            "2136511328",
            "3694931569",
            "7463417111",
            "1319128137",
            "1359912421",
            "3125421639",
            "1293138521",
            "2311944581"
    )

    @BeforeEach
    fun setup() {
        cut = Day15()
    }

    @Test
    fun analyse() {
        assertEquals(40, cut.analyse(input))
    }

    @Test
    fun linePlusOne() {
        assertEquals("234567891", cut.linePlusOne("123456789"))
        assertEquals("345678912", cut.linePlusOne("234567891"))
    }

    @Test
    fun analyse2() {
        assertEquals(315, cut.analyse2(input))
    }
}
