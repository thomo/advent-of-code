package utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class FieldTest {
    private lateinit var cut: Field<Int>

    val input = listOf(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678"
    )

    @BeforeEach
    fun setup() {
        cut = Field<Int>(input.map { it.toCharArray().map { it.toString().toInt() } })
    }

    @Test
    fun getPos() {
        Assertions.assertEquals(FieldPos(0, 0), cut.getPos(0))
        Assertions.assertEquals(FieldPos(1, 0), cut.getPos(1))
        Assertions.assertEquals(FieldPos(8, 0), cut.getPos(8))
        Assertions.assertEquals(FieldPos(9, 0), cut.getPos(9))
        Assertions.assertEquals(FieldPos(0, 1), cut.getPos(10))
        Assertions.assertEquals(FieldPos(9, 4), cut.getPos(49))
    }

    @Test
    fun getStraightNeighborPos() {
        Assertions.assertEquals(listOf(FieldPos(1, 0), FieldPos(0, 1)), cut.getStraightNeighborPos(0))
    }

    @Test
    fun getStraightNeighborValues() {
        Assertions.assertEquals(listOf(1, 3), cut.getValues(cut.getStraightNeighborPos(0)))
        Assertions.assertEquals(listOf(9, 7, 9, 5), cut.getValues(cut.getStraightNeighborPos(12)))
        Assertions.assertEquals(listOf(3, 8, 1, 8), cut.getValues(cut.getStraightNeighborPos(1, 1)))
    }

}
