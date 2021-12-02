package days

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day18Test {
    private lateinit var cut: Day18

    @BeforeEach
    fun setup() {
        cut = Day18()
    }

    @Test
    fun example1() {
        assertEquals(71, cut.calcLine(cut.tokenize("1 + 2 * 3 + 4 * 5 + 6")))
    }

    @Test
    fun example2() {
        assertEquals(51, cut.calcLine(cut.tokenize("1 + (2 * 3) + (4 * (5 + 6))")))
    }

    @Test
    fun example12() {
        assertEquals(231, cut.calcLine2("1 + 2 * 3 + 4 * 5 + 6"))
    }

    @Test
    fun example22() {
        assertEquals(51, cut.calcLine2(("1 + (2 * 3) + (4 * (5 + 6))")))
    }

    @Test
    fun example32() {
        assertEquals(23340, cut.calcLine2(("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")))
    }

    @Test
    fun example321() {
        assertEquals(6*9*(15*14+6)+2, cut.calcLine2(("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2")))
    }

}