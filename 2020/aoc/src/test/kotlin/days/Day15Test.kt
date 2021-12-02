package days

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day15Test {
    private lateinit var cut: Day15

    @BeforeEach
    fun setup() {
        cut = Day15()
        cut.start("0,3,6")
    }

    @Test
    fun turn1() {
        assertEquals(0, cut.turn(1))
    }
    @Test
    fun turn2() {
        assertEquals(3, cut.turn(2))
    }
    @Test
    fun turn3() {
        assertEquals(6, cut.turn(3), cut.toString())
    }

    @Test
    fun turn4() {
        println(cut)
        assertEquals(0, cut.turn(4), cut.toString())
    }

    @Test
    fun turn5() {
        assertEquals(3, cut.turn(5), cut.toString())
    }

    @Test
    fun turn6() {
        assertEquals(3, cut.turn(6), cut.toString())
    }

    @Test
    fun turn7() {
        assertEquals(1, cut.turn(7), cut.toString())
    }

    @Test
    fun turn8() {
        assertEquals(0, cut.turn(8), cut.toString())
    }

    @Test
    fun turn9() {
        assertEquals(4, cut.turn(9), cut.toString())
    }

    @Test
    fun turn2020() {
        assertEquals(436, cut.turn(2020))
    }

}