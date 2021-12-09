package days

class Day03 : Day00 {
    override fun analyse(lines: List<String>): Any {
        val bg = bitGravity(lines)
        // 1: 1071734
        return gammaRate(bg) * epsilonRate(bg)
    }

    override fun analyse2(lines: List<String>) = oxygenGeneratorRate(lines) * co2scrubberRate(lines)

    fun bitGravity(lines: List<String>): String {
        return countBits(lines).joinToString("") { i -> if (i > 0) "1" else "0" }
    }

    private fun countBits(lines: List<String>): List<Int> {
        val counter = Array<Int>(lines[0].length) { _ -> 0 }
        lines.forEach { l: String ->
            val ca = l.toCharArray()
            ca.forEachIndexed { idx, ch -> if (ch == '1') counter[idx] += 1 else counter[idx] -= 1 }
        }
        return counter.toList()
    }

    fun gammaRate(bits: String) = bits.toInt(2)

    fun epsilonRate(bits: String) = invertBitString(bits).toInt(2)

    private fun invertBitString(bits: String) = bits.replace('0', 'x')
        .replace('1', '0')
        .replace('x', '1')

    fun co2scrubberRate(lines: List<String>) = co2scrubberRate(lines, 0)

    private fun co2scrubberRate(lines: List<String>, pos: Int): Int {
        if (lines.size == 1) return lines[0].toInt(2)
        val cb = countBits(lines)
        val cmpChar = if (cb[pos] >= 0) '0' else '1'
        val fl = filterLines(lines, pos, cmpChar)
        return co2scrubberRate(fl, pos + 1)
    }

    fun oxygenGeneratorRate(lines: List<String>) = oxygenGeneratorRate(lines, 0)

    private fun oxygenGeneratorRate(lines: List<String>, pos: Int): Int {
        if (lines.size == 1) return lines[0].toInt(2)
        val cb = countBits(lines)
        val cmpChar = if (cb[pos] >= 0) '1' else '0'
        val fl = filterLines(lines, pos, cmpChar)
        return oxygenGeneratorRate(fl, pos + 1)
    }

    fun filterLines(lines: List<String>, pos: Int, cmpChar: Char) = lines.filter { l -> l[pos] == cmpChar }

}
