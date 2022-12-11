package aoc2021.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day04Test {
    private lateinit var cut: Day04

    val input = listOf(
        "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
        "",
        "22 13 17 11  0",
        " 8  2 23  4 24",
        "21  9 14 16  7",
        " 6 10  3 18  5",
        " 1 12 20 15 19",
        "",
        " 3 15  0  2 22",
        " 9 18 13 17  5",
        "19  8  7 25 23",
        "20 11 10 24  4",
        "14 21 16 12  6",
        "",
        "14 21 17 24  4",
        "10 16 15  9 19",
        "18  8 23 26 20",
        "22 11 13  6  5",
        " 2  0 12  3  7"
    )

    @BeforeEach
    fun setup() {
        cut = Day04()
    }

    @Test
    fun markWhePlay() {
        val b = cut.createBoard(
            listOf(
                "14 21 17 24  4",
                "10 16 15  9 19",
                "18  8 23 26 20",
                "22 11 13  6  5",
                " 2  0 12  3  7"
            )
        )

        val expected = cut.createBoard(
            listOf(
                "* 21 17 24  4",
                "10 16 15  9 19",
                "18  8 23 26 20",
                "22 11 13  6  5",
                " 2  0 12  3  7"
            )
        )
        assertEquals(expected, b.play("14"))
    }

    @Test
    fun getWinnerHorizontal() {
        val b = cut.createBoard(
            listOf(
                "14 21 17 24  4",
                "10 16 15  9 19",
                "18  8 23 26 20",
                "22 11 13  6  5",
                " 2  0 12  3  7"
            )
        )
        val result = b.play("18").play("20").play("23").play("8").play("26")

        assertTrue(result.isWinner())
    }

    @Test
    fun getWinnerVertical() {
        val b = cut.createBoard(
            listOf(
                "14 21 17 24  4",
                "10 16 15  9 19",
                "18  8 23 26 20",
                "22 11 13  6  5",
                " 2  0 12  3  7"
            )
        )
        val result = b.play("21").play("16").play("8").play("11").play("0")

        assertTrue(result.isWinner())
    }

    @Test
    fun sumOfUnMarked() {
        val b = cut.createBoard(
            listOf(
                "* * * * *",
                "10 16 15  * 19",
                "18  8 * 26 20",
                "22 * 13  6  *",
                "*  * 12  3  *"
            )
        )
        assertEquals(188, b.sumOfUnmarked())
    }

    @Test
    fun numberOfBoards() {
        assertEquals(3, cut.extractBoards(input.drop(1)).size)
    }

    @Test
    fun analyse() {
        assertEquals(4512, cut.analyse(input))
    }

    @Test
    fun analyse2() {
        assertEquals(1924, cut.analyse2(input))
    }
}
