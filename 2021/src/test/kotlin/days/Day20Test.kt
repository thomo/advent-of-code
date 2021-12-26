package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import utils.FieldPos

internal class Day20Test {
    private lateinit var field: List<FieldPos>
    private lateinit var cut: Day20

    val input = listOf(
        "..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##" +
            "#..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###" +
            ".######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#." +
            ".#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#....." +
            ".#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.." +
            "...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#....." +
            "..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#",
        "",
        "#..#.",
        "#....",
        "##..#",
        "..#..",
        "..###"
    )

    @BeforeEach
    fun setup() {
        cut = Day20()
        field = cut.parseImage(input.drop(2))
    }

    @Test
    internal fun shouldGenerateNeighbors() {
        val fp = FieldPos(5, 10)
        assertEquals(
            listOf(
                FieldPos(4, 9),
                FieldPos(5, 9),
                FieldPos(6, 9),
                FieldPos(4, 10),
                FieldPos(5, 10),
                FieldPos(6, 10),
                FieldPos(4, 11),
                FieldPos(5, 11),
                FieldPos(6, 11)
            ), cut.getNeighbors(fp)
        )
    }

    @Test
    internal fun shouldGetAlgoIndex() {
        assertEquals(34, cut.algoIndexCalc(FieldPos(2, 2), field.toSet(), false))
    }

    @Test
    fun analyse() {
        assertEquals(35, cut.analyse(input))
    }

    @Test
    fun analyse2() {
        assertEquals(3351, cut.analyse2(input))
    }
}
