package aoc2021.days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day12Test {
    private lateinit var cut: Day12

    val input = listOf(
            "fs-end",
            "he-DX",
            "fs-he",
            "start-DX",
            "pj-DX",
            "end-zg",
            "zg-sl",
            "zg-pj",
            "pj-he",
            "RW-he",
            "fs-DX",
            "pj-RW",
            "zg-RW",
            "start-pj",
            "he-WI",
            "zg-he",
            "pj-fs",
            "start-RW")

    val input1 = listOf(
            "start-A",
            "start-b",
            "A-c",
            "A-b",
            "b-d",
            "A-end",
            "b-end"
    )

    val input2 = listOf(
            "dc-end",
            "HN-start",
            "start-kj",
            "dc-start",
            "dc-HN",
            "LN-dc",
            "HN-end",
            "kj-sa",
            "kj-HN",
            "kj-dc"
    )

    @BeforeEach
    fun setup() {
        cut = Day12()
    }

    @Test
    fun analyse1_simple() {
        assertEquals(1, cut.analyse(listOf("end-start")))
    }

    @Test
    fun analyse1_1() {
        assertEquals(10, cut.analyse(input1))
    }

    @Test
    fun analyse1_2() {
        assertEquals(19, cut.analyse(input2))
    }

    @Test
    fun analyse() {
        assertEquals(226, cut.analyse(input))
    }

    @Test
    fun analyse2_1() {
        assertEquals(36, cut.analyse2(input1))
    }

    @Test
    fun analyse2_2() {
        assertEquals(103, cut.analyse2(input2))
    }

    @Test
    fun analyse2() {
        assertEquals(3509, cut.analyse2(input))
    }
}
