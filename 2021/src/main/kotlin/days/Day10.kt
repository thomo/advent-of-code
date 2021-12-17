package days

class Day10 : Day00 {
    override fun analyse(lines: List<String>) = lines
        .map { parseLine(it) }
        .filter { it.first == LINE_STATE.CORRUPTED }
        .map { char2value(it.second) }
        .sum()

    override fun analyse2(lines: List<String>): Any {
        val numbers = lines
            .map { parseLine(it) }
            .filter { it.first == LINE_STATE.INCOMPLETE }
            .map { scoreCompletion(it.third) }
            .sorted()
        return numbers[middleIdx(numbers.size)]
    }

    fun middleIdx(size: Int) = (size) / 2

    fun parseLine(input: String) = parseLine(input, java.util.ArrayDeque<Char>())

    fun parseLine(input: String, stack: java.util.ArrayDeque<Char>): Triple<LINE_STATE, Char, List<Char>> {
        if (input.isEmpty()) {
            return Triple(LINE_STATE.INCOMPLETE, 'x', toClosing(stack))
        }
        val cur = input[0]
        if (isOpening(cur)) {
            stack.push(cur)
            return parseLine(input.drop(1), stack)
        } else {
            val prev = stack.peek()
            return if (isMatch(prev, cur)) {
                stack.pop()
                return parseLine(input.drop(1), stack)
            } else Triple(LINE_STATE.CORRUPTED, cur, emptyList())
        }
    }

    private fun toClosing(stack: java.util.ArrayDeque<Char>) =
        stack.map {
            when (it) {
                '(' -> ')'
                '{' -> '}'
                '<' -> '>'
                '[' -> ']'
                else -> ' '
            }
        }

    private fun isMatch(opening: Char, closing: Char): Boolean {
        return opening == '{' && closing == '}' || opening == '(' && closing == ')' || opening == '<' && closing == '>' || opening == '[' && closing == ']'
    }

    private fun isOpening(c: Char) = c == '{' || c == '[' || c == '(' || c == '<'

    private fun isClosing(c: Char) = c == '}' || c == ']' || c == ')' || c == '>'

    fun char2value(c: Char) = when (c) {
        ')' -> 3L
        ']' -> 57L
        '}' -> 1197L
        '>' -> 25137L
        else -> 0L
    }

    /*
        Start with a total score of 0.
        Then, for each character, multiply the total score by 5
        and then increase the total score by the point value given
        for the character in the following table:

        ): 1 point.
        ]: 2 points.
        }: 3 points.
        >: 4 points.

     */
    fun scoreCompletion(input: List<Char>) = input.map {
        when (it) {
            ')' -> 1L
            ']' -> 2L
            '}' -> 3L
            '>' -> 4L
            else -> 0L
        }
    }.fold(0L) { acc, i -> acc * 5L + i }

    enum class LINE_STATE {
        INCOMPLETE, CORRUPTED
    }
}
