package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day18Test {
    private lateinit var cut: Day18

    val input = listOf(
        "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
        "[[[5,[2,8]],4],[5,[[9,9],0]]]",
        "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
        "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
        "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
        "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
        "[[[[5,4],[7,7]],8],[[8,3],8]]",
        "[[9,3],[[9,9],[6,[4,9]]]]",
        "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
        "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]"
    )

    @BeforeEach
    fun setup() {
        cut = Day18()
    }

    @ParameterizedTest(name = "tokenize snailfish number {0}")
    @CsvSource(
        delimiter = ':',
        value = [
            "[[[[[9,8],1],2],3],4]",
            "[7,[6,[5,[4,[3,2]]]]]",
            "[[6,[5,[4,[3,2]]]],1]",
            "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]",
            "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"
        ]
    )
    internal fun shouldTokenize(line: String) {
        assertEquals(line, cut.tokenize(line).map { it.c }.joinToString(""))
    }

    @ParameterizedTest(name = "reduce snailfish number {0}")
    @CsvSource(
        delimiter = ':',
        value = [
            "[[[[[9,8],1],2],3],4]:[[[[0,9],2],3],4]",
            "[7,[6,[5,[4,[3,2]]]]]:[7,[6,[5,[7,0]]]]",
            "[[6,[5,[4,[3,2]]]],1]:[[6,[5,[7,0]]],3]",
            "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]:[[3,[2,[8,0]]],[9,[5,[7,0]]]]",
            "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]:[[3,[2,[8,0]]],[9,[5,[7,0]]]]"
        ]
    )
    internal fun shouldReduce(input: String, expected: String) {
        assertEquals(expected, cut.reduce(cut.tokenize(input)).map { it.c }.joinToString(""))
    }

    @ParameterizedTest(name = "magnitude {0}")
    @CsvSource(
        delimiter = ':',
        value = [
            "[[1,2],[[3,4],5]]:143",
            "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]:1384",
            "[[[[1,1],[2,2]],[3,3]],[4,4]]:445",
            "[[[[3,0],[5,3]],[4,4]],[5,5]]:791",
            "[[[[5,0],[7,4]],[5,5]],[6,6]]:1137",
            "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]:3488"
        ]
    )
    internal fun calcMagnitude(input: String, expected: Long) {
        assertEquals(expected, cut.magnitude(input))
    }

    @Test
    fun calc() {
        assertEquals("[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]", cut.calc(input))
    }

    @Test
    fun analyse() {
        assertEquals(4140L, cut.analyse(input))
    }

    @Test
    fun analyse2() {
        assertEquals(3993L, cut.analyse2(input))
    }
}
