package aoc2020

import aoc.days.DayXX

class Day07 : DayXX {

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
		val r = Regex("""( no other bags.)|(\d)+ ([a-z]+ [a-z]+) bags?""")
		val gv = r.find(b)?.groupValues
		if (gv == null || gv.size == 1) {
			return Pair(0, "")
		}
		return Pair(gv[2].toInt(), gv[3])
	}

	private fun buildBagMap(lines: List<String>): Map<String, List<Pair<Int, String>>> {
		val bagMap = lines
				.map { line -> line.split(" bags contain ") }
				.associate { Pair(it[0], it[1].split(Regex(", ")).map { b -> innerBag(b) }) }
		return bagMap
	}

	// 242
	override fun analyse(lines: List<String>): Any {
		val bagMap = buildBagMap(lines)

		return bagMap.keys.filter { b -> canContainGoldenBag(bagMap[b], bagMap) }.size
	}


	// 176035
	override fun analyse2(lines: List<String>): Any {
		val bagMap = buildBagMap(lines)

		return countContainingBags("shiny gold", bagMap)
	}

}
