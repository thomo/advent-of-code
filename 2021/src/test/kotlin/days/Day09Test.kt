package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import utils.Field
import utils.FieldPos

internal class Day09Test {
    private lateinit var field: Field<Int>
    private lateinit var cut: Day09

    val input = listOf(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678"
    )

    @BeforeEach
    fun setup() {
        cut = Day09()
        field = cut.parseInput(input)
    }

    @ParameterizedTest(name = "isLowest should return true for {0}")
    @ValueSource(ints = [1, 9, 22, 46])
    fun foundLowest_True(idx: Int) {
        assertTrue(cut.isLowest(idx, field))
    }

    @ParameterizedTest(name = "isLowest should return false for {0}")
    @ValueSource(ints = [0, 12])
    fun foundLowest_False(idx: Int) {
        assertFalse(cut.isLowest(idx, field))
    }

    @Test
    fun analyse() {
        assertEquals(15, cut.analyse(input))
    }

    @Test
    fun getBasin_0() {
        assertEquals(setOf(FieldPos(0, 0), FieldPos(0, 1), FieldPos(1, 0)), cut.getBasin(0, field))
    }

    @Test
    fun analyse2() {
        assertEquals(1134, cut.analyse2(input))
    }
}
