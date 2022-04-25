package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day19Test {
    private lateinit var cut: Day19

    val input = listOf(
        ""
    )

    val scanner0 = listOf(
        Day19.Beacon(-1, -1, 1),
        Day19.Beacon(-2, -2, 2),
        Day19.Beacon(-3, -3, 3),
        Day19.Beacon(-2, -3, 1),
        Day19.Beacon(5, 6, -4),
        Day19.Beacon(8, 0, 7)
    )

    @Test
    internal fun shouldGenerateOrientationsOfBeacon() {
        val b = Day19.Beacon(2, 3, 4)
        val result = b.orientations()

        assertTrue(result.contains(Day19.Beacon(2, 3, 4)))
        assertTrue(result.contains(Day19.Beacon(-2, 3, 4)))
        assertTrue(result.contains(Day19.Beacon(2, -3, 4)))
        assertTrue(result.contains(Day19.Beacon(-2, -3, 4)))
        assertTrue(result.contains(Day19.Beacon(2, 3, -4)))
        assertTrue(result.contains(Day19.Beacon(-2, 3, -4)))
        assertTrue(result.contains(Day19.Beacon(2, -3, -4)))
        assertTrue(result.contains(Day19.Beacon(-2, -3, -4)))

        assertTrue(result.contains(Day19.Beacon(2, 4, 3)))
        assertTrue(result.contains(Day19.Beacon(-2, 4, 3)))
        assertTrue(result.contains(Day19.Beacon(2, -4, 3)))
        assertTrue(result.contains(Day19.Beacon(-2, -4, 3)))
        assertTrue(result.contains(Day19.Beacon(2, 4, -3)))
        assertTrue(result.contains(Day19.Beacon(-2, 4, -3)))
        assertTrue(result.contains(Day19.Beacon(2, -4, -3)))
        assertTrue(result.contains(Day19.Beacon(-2, -4, -3)))

        assertTrue(result.contains(Day19.Beacon(3, 4, 2)))
        assertTrue(result.contains(Day19.Beacon(-3, 4, 2)))
        assertTrue(result.contains(Day19.Beacon(3, -4, 2)))
        assertTrue(result.contains(Day19.Beacon(-3, -4, 2)))
        assertTrue(result.contains(Day19.Beacon(3, 4, -2)))
        assertTrue(result.contains(Day19.Beacon(-3, 4, -2)))
        assertTrue(result.contains(Day19.Beacon(3, -4, -2)))
        assertTrue(result.contains(Day19.Beacon(-3, -4, -2)))

        assertTrue(result.contains(Day19.Beacon(3, 2, 4)))
        assertTrue(result.contains(Day19.Beacon(-3, 2, 4)))
        assertTrue(result.contains(Day19.Beacon(3, -2, 4)))
        assertTrue(result.contains(Day19.Beacon(-3, -2, 4)))
        assertTrue(result.contains(Day19.Beacon(3, 2, -4)))
        assertTrue(result.contains(Day19.Beacon(-3, 2, -4)))
        assertTrue(result.contains(Day19.Beacon(3, -2, -4)))
        assertTrue(result.contains(Day19.Beacon(-3, -2, -4)))

        assertTrue(result.contains(Day19.Beacon(4, 2, 3)))
        assertTrue(result.contains(Day19.Beacon(-4, 2, 3)))
        assertTrue(result.contains(Day19.Beacon(4, -2, 3)))
        assertTrue(result.contains(Day19.Beacon(-4, -2, 3)))
        assertTrue(result.contains(Day19.Beacon(4, 2, -3)))
        assertTrue(result.contains(Day19.Beacon(-4, 2, -3)))
        assertTrue(result.contains(Day19.Beacon(4, -2, -3)))
        assertTrue(result.contains(Day19.Beacon(-4, -2, -3)))

        assertTrue(result.contains(Day19.Beacon(4, 3, 2)))
        assertTrue(result.contains(Day19.Beacon(-4, 3, 2)))
        assertTrue(result.contains(Day19.Beacon(4, -3, 2)))
        assertTrue(result.contains(Day19.Beacon(-4, -3, 2)))
        assertTrue(result.contains(Day19.Beacon(4, 3, -2)))
        assertTrue(result.contains(Day19.Beacon(-4, 3, -2)))
        assertTrue(result.contains(Day19.Beacon(4, -3, -2)))
        assertTrue(result.contains(Day19.Beacon(-4, -3, -2)))
    }

    @Test
    internal fun shouldGenerateOrientationsOfScanner() {
        val result = cut.orientations(scanner0)
        assertTrue(
            result.contains(
                listOf(
                    Day19.Beacon(1, 1, 1), Day19.Beacon(2, 2, 2), Day19.Beacon(3, 3, 3),
                    Day19.Beacon(3, 1, 2), Day19.Beacon(-6, -4, -5), Day19.Beacon(0, 7, -8)
                )
            )
        )
        assertTrue(
            result.contains(
                listOf(
                    Day19.Beacon(-1, -1, -1), Day19.Beacon(-2, -2, -2), Day19.Beacon(-3, -3, -3),
                    Day19.Beacon(-1, -3, -2), Day19.Beacon(4, 6, 5), Day19.Beacon(-7, 0, 8)
                )
            )
        )
    }

    @Test
    internal fun findMatch() {
        val s1 = listOf(Day19.Beacon(0, 2, 0), Day19.Beacon(4, 1, 0), Day19.Beacon(3, 3, 0))
        val s2 = listOf(Day19.Beacon(-1, -1, 0), Day19.Beacon(-5, 0, 0), Day19.Beacon(-2, 1, 0))
        val result = cut.match(s1, s2)
//        ...B..
//            B....S
//        ....B.
//        S.....
//        assertEquals(
//            Day19.Scanner(
//                listOf(Day19.Beacon(-1, -1, 0), Day19.Beacon(-5, 0, 0), Day19.Beacon(-2, 1, 0)),
//                result
//            )
    }

    @BeforeEach
    fun setup() {
        cut = Day19()
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
