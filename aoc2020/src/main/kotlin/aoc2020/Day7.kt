package aoc2020

class Day7 {
	fun run(lines: List<String>) {
		var bagMap = lines
			.map { line -> line.split(" bags contain ") }
			.associate { Pair(it[0], it[1].split(Regex(", ")).map { b -> innerBag(b) }) }

		var cnt = 0
		for (b in bagMap.keys) {
			if (canContainGoldenBag(bagMap[b], bagMap)) {
				cnt += 1
			}
		}

		// println(cnt)

		// println(countContainingBags("dark green", bagMap))
		println(countContainingBags("shiny gold", bagMap))
	}

	private fun countContainingBags(b: String, bm: Map<String, List<Pair<Int, String>>>): Int {
		val innerBags = bm[b] ?: return 1
		return innerBags.map { ib -> ib.first + ib.first * countContainingBags(ib.second, bm) }.sum()
	}

	private fun canContainGoldenBag(
		innerBags: List<Pair<Int, String>>?,
		bm: Map<String, List<Pair<Int, String>>>
	): Boolean {
		if (innerBags == null) return false
		if (innerBags.any { ib -> ib.second == "shiny gold" }) return true
		for (ib in innerBags) {
			if (canContainGoldenBag(bm[ib.second], bm)) return true
		}
		return false
	}

	fun innerBag(b: String): Pair<Int, String> {
		var r = Regex("""( no other bags.)|(\d)+ ([a-z]+ [a-z]+) bags?""")
		val gv = r.find(b)?.groupValues
		if (gv == null || gv.size == 1) {
			return Pair(0, "")
		}
		return Pair(gv[2].toInt(), gv[3])
	}

}
