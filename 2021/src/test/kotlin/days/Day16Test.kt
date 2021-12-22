package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day16Test {
    private lateinit var cut: Day16

    val input = listOf(
        ""
    )

    @BeforeEach
    fun setup() {
        cut = Day16()
    }

    @Test
    internal fun convertHexInput() {
        assertEquals("110100101111111000101000", cut.parseHexInput("D2FE28"))
    }

    @ParameterizedTest(name = "getVersion {0}")
    @CsvSource(
        "110100101111111000101000,6",
        "00111000000000000110111101000101001010010001001000000000, 1",
        "11101110000000001101010000001100100000100011000001100000, 7"
    )
    internal fun getVersion(binString: String, expected: Int) {
        assertEquals(expected, cut.getVersion(binString))
    }

    @ParameterizedTest(name = "getType {0}")
    @CsvSource(
        "110100101111111000101000,4",
        "00111000000000000110111101000101001010010001001000000000, 6",
        "11101110000000001101010000001100100000100011000001100000, 3"
    )
    internal fun getType(binString: String, expected: Int) {
        assertEquals(expected, cut.getType(binString))
    }

    @ParameterizedTest(name = "analyse {0}")
    @CsvSource(
        "D2FE28,6",
        "8A004A801A8002F478,16",
        "620080001611562C8802118E34,12",
        "C0015000016115A2E0802F182340,23",
        "A0016C880162017C3686B18A3D4780,31"
    )
    fun analyse(input: String, expectedResult: Int) {
        assertEquals(expectedResult, cut.analyse(listOf(input)))
    }

    @Test
    fun analyse2() {
        assertEquals(0, cut.analyse2(input))
    }
}
