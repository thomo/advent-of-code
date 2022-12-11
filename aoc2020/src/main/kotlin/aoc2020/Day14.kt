package aoc2020

class Day14 {
	val memory2 = mutableMapOf<Long, Long>()

	fun run(lines: List<String>) {
		var mask = Triple(0L, 0L, "")
		var mem = Pair(0L, 0L)
		val memory = mutableMapOf<Long, Long>()
		lines.forEach { line ->
			when (line.substring(0, 2)) {
				"ma" ->
					mask = convertMask(parseMaskLine(line))

				"me" -> {
					mem = parseMemLine(line)
					val value = calc(mem.second, mask)
					memory[mem.first] = value
					getAddresses(mem.first, mask.third).forEach { memory2[it] = mem.second }
				}
			}
		}
		println(memory.values.sum())
		println(memory2.values.sum())
	}

	fun getAddresses(baseAddr: Long, mask: String): List<Long> {
		val minAddr = baseAddr and mask.replace('0', '1').replace('X', '0').toLong(2)
		return buildAddresses(minAddr, mask)
	}

	private fun buildAddresses(baseAddr: Long, mask: String): List<Long> {
		val idx = mask.indexOfFirst { it == 'X' }
		if (idx == -1) {
			return listOf(baseAddr or mask.toLong(2))
		}
		val result = buildAddresses(baseAddr, mask.replaceFirst('X', '0')).toMutableList()
		result.addAll(buildAddresses(baseAddr, mask.replaceFirst('X', '1')))
		return result
	}

	fun convertMask(s: String): Triple<Long, Long, String> {
		val maskOr = s.replace('X', '0').toLong(2)
		val maskAnd = s.replace('X', '1').toLong(2)
		return Triple(maskOr, maskAnd, s)
	}

	fun calc(value: Long, m: Triple<Long, Long, String>): Long = (value or m.first) and m.second

	fun parseMaskLine(line: String): String = line.split(" = ")[1]

	// mem[35453] = 378
	fun parseMemLine(line: String): Pair<Long, Long> {
		val regex = Regex("""mem.(\d+). = (\d+)""")
		val groups = regex.find(line)!!.groupValues
		return Pair(groups[1].toLong(), groups[2].toLong())
	}

}
