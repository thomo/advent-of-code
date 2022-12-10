package aoc2021.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day08Test {
    private lateinit var cut: Day08

    val input = listOf(
        "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
        "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
        "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
        "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
        "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
        "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
        "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
        "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
        "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
        "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"
    )

    @BeforeEach
    fun setup() {
        cut = Day08()
    }

    @Test
    fun checkAcontainsB() {
        val d = Day08.DigitLine(input[0].split('|'))
        assertTrue(d.checkAcontainsB(charArrayOf('a', 'b', 'c', 'd'), charArrayOf('b', 'c')))
    }

    @Test
    fun checkAcontainsB_neg() {
        val d = Day08.DigitLine(input[0].split('|'))
        assertFalse(d.checkAcontainsB(charArrayOf('a', 'c', 'd'), charArrayOf('b', 'c')))
    }

    @Test
    fun digilineAnalyse() {
        val d = Day08.DigitLine(input[0].split('|'))
        assertEquals(listOf(8, 3, 9, 4), d.analyse())
    }

    @Test
    fun decryptLine() {
        val d = Day08.DigitLine(input[0].split('|'))
        assertEquals(8394, d.solve())
    }

    @Test
    fun analyse() {
        assertEquals(26, cut.analyse(input))
    }

    @Test
    fun analyse2() {
        assertEquals(61229, cut.analyse2(input))
    }
}
