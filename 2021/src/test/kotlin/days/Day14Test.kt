package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day14Test {
    private lateinit var cut: Day14

    val input = listOf(
            "NNCB",
            "",
            "CH -> B",
            "HH -> N",
            "CB -> H",
            "NH -> C",
            "HB -> C",
            "HC -> B",
            "HN -> C",
            "NN -> C",
            "BH -> H",
            "NC -> B",
            "NB -> B",
            "BN -> B",
            "BB -> N",
            "BC -> B",
            "CC -> N",
            "CN -> C"
    )

    @BeforeEach
    fun setup() {
        cut = Day14()
    }

    @Test
    fun toChunk() {
        assertEquals(listOf("NN", "NC", "CB"), cut.toChunks("NNCB"))
    }

    @Test
    fun a1_step1() {
        val (template, rules) = cut.parseInput(input)
        assertEquals("NCNBCHB", cut.replace(template, rules, 1))
    }

    @Test
    fun a2_step1() {
        val (template, rules) = cut.parseInput(input)
        assertEquals(mapOf('N' to 2L, 'C' to 2L, 'B' to 2L, 'H' to 1L), cut.calc(template, rules, 1))
    }

    @Test
    fun a2_step2() {
        val (template, rules) = cut.parseInput(input)
        assertEquals(mapOf('N' to 2L, 'C' to 4L, 'B' to 6L, 'H' to 1L), cut.calc(template, rules, 2))
    }

    //                                                 NBCCNBBBCBHCB
    // NNCB - NN NC CB - NCNBCHB - NC CN NB BC CH HB - NBCCNBBBCBHCB
    // NN - C / NC CN - B / NB BC  C / CC CN
    // NC - B / NB BC - B / NB BB  B / BB CB
    // CB - H / CH HB - B / CB BH  C / HC CB
    //      B
    @Test
    fun a2_countChars() {
        assertEquals(mapOf('N' to 2L, 'C' to 2L, 'B' to 2L, 'H' to 1L), cut.countChars(mapOf("NC" to 1, "CN" to 1, "NB" to 1, "BC" to 1, "CH" to 1, "HB" to 1), 'N', 'B'))
    }

    @Test
    fun a1_step2() {
        val (template, rules) = cut.parseInput(input)
        assertEquals("NBCCNBBBCBHCB", cut.replace(template, rules, 2))
    }

    @Test
    fun a1_step4() {
        val (template, rules) = cut.parseInput(input)
        assertEquals("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB", cut.replace(template, rules, 4))
    }

    @Test
    fun analyse() {
        assertEquals(1588, cut.analyse(input))
    }

    @Test
    fun analyse2() {
        assertEquals(2188189693529L, cut.analyse2(input))
    }
}
