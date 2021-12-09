package days

class Day08 : Day00 {
    override fun analyse(lines: List<String>) = lines
        .map { it.split('|')[1].trim() }
        .map { right ->
            right
                .split(" ").count {
                    it.length == 2      // 1
                        || it.length == 3   // 7
                        || it.length == 4   // 4
                        || it.length == 7   // 8
                }
        }.sum()

    override fun analyse2(lines: List<String>) = lines
        .map { DigitLine(it.split('|').map { p -> p.trim() }) }
        .map { it.solve() }
        .sum()

    class DigitLine(parts: List<String>) {

        private var digits: List<CharArray>
        private var patterns: List<CharArray>

        init {
            patterns = parts[0].trim().split(' ').map { toSortedChars(it) }
            digits = parts[1].trim().split(' ').map { toSortedChars(it) }
        }

        fun analyse(): List<Int?> {

            val dm = mutableMapOf<Int, Int>()
            val one = patterns.first { it.size == 2 }
            dm.put(one.contentHashCode(), 1)
            val four = patterns.first { it.size == 4 }
            dm.put(four.contentHashCode(), 4)
            val seven = patterns.first { it.size == 3 }
            dm.put(seven.contentHashCode(), 7)
            val eight = patterns.first { it.size == 7 }
            dm.put(eight.contentHashCode(), 8)

            // 6 segs - zero, six, nine
            // 5 segs - two, three, five

            val three = patterns.filter { it.size == 5 }.first { checkAcontainsB(it, seven) }
            dm.put(three.contentHashCode(), 3)
            val nine = patterns.filter { it.size == 6 }.first { checkAcontainsB(it, three) }
            dm.put(nine.contentHashCode(), 9)
            val zero = patterns.filter { it.size == 6 } // zero, six, nine
                .filterNot { it.equals(nine) } // zero, six
                .filter { checkAcontainsB(it, one) } // zero
                .first()
            dm.put(zero.contentHashCode(), 0)
            val six = patterns.filter { it.size == 6 } // zero, six, nine
                .filterNot { it.equals(nine) } // zero, six
                .filterNot { it.equals(zero) } // siz
                .first()
            dm.put(six.contentHashCode(), 6)
            val five = patterns.filter { it.size == 5 } // two, three, five
                .filterNot { it.equals(three) } // two, five
                .filter { checkAcontainsB(six, it) } // five
                .first()
            dm.put(five.contentHashCode(), 5)
            val two = patterns.filter { it.size == 5 } // two, three, five
                .filterNot { it.equals(three) } // two, five
                .filterNot { it.equals(five) } // two
                .first()
            dm.put(two.contentHashCode(), 2)
            return digits.map { dm[it.contentHashCode()] }
        }

        fun checkAcontainsB(a: CharArray, b: CharArray) = b.all { c -> a.contains(c) }

        private fun toSortedChars(it: String): CharArray {
            val x = it.trim().toCharArray(); x.sort(); return x
        }

        fun solve() = toNumber(analyse())

        fun toNumber(solution: List<Int?>) = solution.fold(0) { acc, i -> acc * 10 + (i ?: 0) }
    }

}
