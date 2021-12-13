package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day11Test {
    private lateinit var inputField: Day11.MyField
    private lateinit var inputFieldAfterStep1: Day11.MyField
    private lateinit var inputFieldAfterStep2: Day11.MyField

    private lateinit var cut: Day11

    val input = listOf(
            "5483143223",
            "2745854711",
            "5264556173",
            "6141336146",
            "6357385478",
            "4167524645",
            "2176841721",
            "6882881134",
            "4846848554",
            "5283751526"
    )

    val inputAfterStep1 = listOf(
            "6594254334",
            "3856965822",
            "6375667284",
            "7252447257",
            "7468496589",
            "5278635756",
            "3287952832",
            "7993992245",
            "5957959665",
            "6394862637"
    )

    val inputAfterStep2 = listOf(
            "8807476555",
            "5089087054",
            "8597889608",
            "8485769600",
            "8700908800",
            "6600088989",
            "6800005943",
            "0000007456",
            "9000000876",
            "8700006848"
    )

    @BeforeEach
    fun setup() {
        cut = Day11()
        inputField = cut.buildField(input)
        inputFieldAfterStep1 = cut.buildField(inputAfterStep1)
        inputFieldAfterStep2 = cut.buildField(inputAfterStep2)
    }

    @Test
    fun play_After1_simple() {
        val simpleField = cut.buildField(listOf("11111", "19991", "19191", "19991", "11111"))
        val expected = cut.buildField(listOf("34543", "40004", "50005", "40004", "34543"))
        val (result, sum) = cut.play(simpleField, 0)
        assertEquals(expected, result)
    }

    @Test
    fun play_After2_simple() {
        val simpleField = cut.buildField(listOf("11111", "19991", "19191", "19991", "11111"))
        val expected = cut.buildField(listOf("45654", "51115", "61116", "51115", "45654"))
        val (result, sum) = cut.play(simpleField, 0)
        val (result2, sum2) = cut.play(result, sum)
        assertEquals(expected, result2)
    }

    @Test
    fun play_After1() {
        val (result, sum) = cut.play(inputField, 0)
        assertEquals(inputFieldAfterStep1, result)
    }

    @Test
    fun analyse() {
        assertEquals(1656, cut.analyse(input))
    }

    @Test
    fun analyse2() {
        assertEquals(195, cut.analyse2(input))
    }
}
