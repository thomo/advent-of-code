package aoc2020

class Day6 {
	fun run(lines: List<String>) {
		val answers = mutableListOf<Pair<Int, String>>()
		var merged: String = ""
		var size: Int = 0
		for (line in lines) {
			if (line.isBlank()) {
				answers.add(Pair(size, merged))
				merged = ""
				size = 0
			} else {
				merged = "$merged$line"
				++size
			}
		}
		answers.add(Pair(size, merged))

		// 6.1 println(answers.map { s -> countDistinctChars(s)}.fold(0){acc, e -> acc + e})

		println(d62(answers))
		//result.forEach { println(it.toString()) }

	}

	private fun d62(answers: List<Pair<Int, String>>): Int =
		answers.map { p -> groupsOfSize(p) }.fold(0) { acc, e -> acc + e }

	private fun groupsOfSize(p: Pair<Int, String>): Int {
		var cnt = p.first
		var a = p.second.toCharArray()
		return a.groupBy { ch -> ch }.map { e -> e.value.size }.filter { it == cnt }.size
	}

	private fun countDistinctChars(s: String): Int = s.toCharArray().distinct().size

}
