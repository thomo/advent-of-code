package days

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day19Test {
    private lateinit var cut: Day19
    private lateinit var inputs: List<String>

    @BeforeEach
    fun setup() {
        cut = Day19()

        inputs = listOf(
            "0: 4 1 5",
            "1: 2 3 | 3 2",
            "2: 4 4 | 5 5",
            "3: 4 5 | 5 4",
            "4: \"a\"",
            "5: \"b\"",
            "",
            "ababbb",
            "bababa",
            "abbbab",
            "aaabbb",
            "aaaabbb"
        )

    }

    @Test
    fun runMatch() {
//        val it = inputs.listIterator()
//        var leaves = cut.extractRules(it)
//        var inputs = cut.readPatterns(it)
//
//        assertEquals(true, cut.check(listOf("a","b", "a", "b", "b", "b"), 0))
    }

    @Test
    fun run() {
        assertEquals(2, cut.run(inputs))
    }
}