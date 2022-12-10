package days

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day14Test {
    private lateinit var cut: Day14

    @BeforeEach
    fun setup() {
        cut = Day14()
    }

    @Test
    fun convertMask() {
        val m = cut.convertMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        assertEquals(0b000000000000000000000000000001000000, m.first, "OR")
        assertEquals(0b111111111111111111111111111111111101, m.second, "AND")
    }

    @Test
    fun calc_1() {
        val m = cut.convertMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        val result:Long = cut.calc(11L, m)
        assertEquals(73L, result)
    }

    @Test
    fun calc_2() {
        val m = cut.convertMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        val result:Long = cut.calc(101L, m)
        assertEquals(101L, result)
    }

    @Test
    fun calc_3() {
        val m = cut.convertMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        val result:Long = cut.calc(0L, m)
        assertEquals(64L, result)
    }

    @Test
    fun parseMaskLine() {
        val mask = cut.parseMaskLine("mask = 1101000X0110100X1X1X00001010XX00X0X0")
        assertEquals("1101000X0110100X1X1X00001010XX00X0X0", mask)
    }

    @Test
    fun parseMemLine() {
        val mem = cut.parseMemLine("mem[48482] = 6450058")
        assertEquals(48482, mem.first)
        assertEquals(6450058, mem.second)
    }

    @Test
    fun generateAddrList_8() {
        val mask = cut.parseMaskLine("mask = 0X1X")
        val result = cut.getAddresses(0b1000, mask)
        assertEquals(4, result.size)
        assertTrue(result.contains(0b1010))
        assertTrue(result.contains(0b1011))
        assertTrue(result.contains(0b1110))
        assertTrue(result.contains(0b1111))
    }

    @Test
    fun generateAddrList_long() {
        val mask = cut.parseMaskLine("mask = 00000000000000000000000000000000X0XX")
        val result = cut.getAddresses(26, mask)
        assertEquals(8, result.size)
        assertTrue(result.contains(16))
        assertTrue(result.contains(17))
        assertTrue(result.contains(18))
        assertTrue(result.contains(19))
        assertTrue(result.contains(24))
        assertTrue(result.contains(25))
        assertTrue(result.contains(26))
        assertTrue(result.contains(27))
    }

}