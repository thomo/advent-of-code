package aoc2021.days

import aoc.days.DayXX

class Day14 : DayXX {

	fun parseInput(input: List<String>): Pair<String, Map<String, String>> {
		val template = input[0]
		val rules = input.drop(2).map {
			val p = it.split(" -> ")
			Pair(p[0], p[1])
		}.toMap()
		return Pair(template, rules)
	}

	fun replace(template: String, rules: Map<String, String>, i: Int): String =
		if (i == 0) template
		else {
			val next: String = toChunks(template).map {
				it[0] + (rules[it] ?: ".")
			}.joinToString("") + template.last()
			replace(next, rules, i - 1)
		}

	fun toChunks(template: String) = (0 until template.length - 1).map { template.substring(it, it + 2) }

	override fun analyse(lines: List<String>): Any {
		val (template, rules) = parseInput(lines)
		val charGroups = replace(template, rules, 10).toCharArray().toList().groupBy { it }
		val counts = charGroups.map { it.value.size }
		return counts.maxOf { it } - counts.minOf { it }
	}

	fun calc(template: String, rules: Map<String, String>, i: Int): Map<Char, Long> {
		val extRules = generateExtRules(rules)
		val chunks = toChunks(template)

		return countChars(calcChunks(countChunks(chunks), extRules, i), template.first(), template.last())
	}

	fun countChunks(chunks: List<String>) = chunks.groupBy { it }.mapValues { it.value.size.toLong() }

	fun countChars(map: Map<String, Long>, first: Char, last: Char) =
		map.entries
			.flatMap { e -> e.key.toCharArray().toList().map { c -> Pair(c, e.value) } }
			.fold(mapOf(first to 1L, last to 1L)) { acc, e ->
				acc + Pair(
					e.first,
					acc.getOrDefault(e.first, 0L) + e.second
				)
			}
			.mapValues { e -> e.value / 2 }

	fun calcChunks(chunks: Map<String, Long>, extRules: Map<String, List<String>>, i: Int): Map<String, Long> {
		if (i == 0) return chunks
		val next = chunks.flatMap { extRules[it.key]!!.map { nchuck -> Pair(nchuck, it.value) } }
			.groupBy { it.first }
			.mapValues { it.value.sumOf { p -> p.second } }
		return calcChunks(next, extRules, i - 1)
	}

	private fun generateExtRules(rules: Map<String, String>) =
		rules.mapValues { listOf(it.key[0] + it.value, it.value + it.key[1]) }

	override fun analyse2(lines: List<String>): Any {
		val (template, rules) = parseInput(lines)
		val counts = calc(template, rules, 40).map { it.value }
		return counts.maxOf { it } - counts.minOf { it }
	}

}
