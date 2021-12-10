package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class Day10Test {
    private lateinit var cut: Day10

    val input = listOf(
            "[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]"
    )

    @BeforeEach
    fun setup() {
        cut = Day10()
    }

    @ParameterizedTest
    @ValueSource(strings = ["(", "([]", "({})[]["])
    fun markAsIncomplete(input: String) {
        val result = cut.parseLine(input).first
        assertEquals(Day10.LINE_STATE.INCOMPLETE, result)
    }

    @ParameterizedTest
    @CsvSource(value = ["(],]", "([]>,>", "({})[}[],}", "<{([([[(<>()){}]>(<<{{,>"])
    fun markAsCorrupted(input: String, c: Char) {
        val result = cut.parseLine(input)
        assertEquals(Day10.LINE_STATE.CORRUPTED, result.first)
        assertEquals(c, result.second)
    }

    @ParameterizedTest
    @CsvSource(value = [
        "[,]",
        "{[(),]}",
        "[({(<(())[]>[[{[]{<()<>>,}}]])})]",
        "[(()[<>])]({[<{<<[]>>(,)}>]})",
        "(((({<>}<{<{<>}{[]{[]{},}}>}>))))",
        "{<[[]]>}<{[{[{[]{()[[[],]]}}]}]}>",
        "<{([{{}}[<[[[<>{}]]]>[]],])}>"
    ])
    fun getCompletion(input: String, completion: String) {
        assertEquals(completion.toCharArray().toList(), cut.parseLine(input).third)
    }

    @ParameterizedTest
    @CsvSource(value = [
        "}}]])})],288957",
        ")}>]}),5566",
        "}}>}>)))),1480781",
        "]]}}]}]}>,995444",
        "])}>,294"
    ])
    fun scoreCompletion(input: String, value: Long) {
        assertEquals(value, cut.scoreCompletion(input.toCharArray().toList()))
    }

    @Test
    fun analyse() {
        assertEquals(26397, cut.analyse(input))
    }

    @ParameterizedTest
    @CsvSource(value = ["5,2", "7,3", "1, 0", "3,1"])
    fun calcMiddle(size: Int, middle: Int) {
        assertEquals(middle, cut.middleIdx(size))
    }

    @Test
    fun analyse2() {
        assertEquals(288957L, cut.analyse2(input))
    }
}
