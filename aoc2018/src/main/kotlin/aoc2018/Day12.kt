package aoc2018

import aoc.days.DayXX

data class Tunnel(val pods: String, val left: Long) {
	fun reduce() = reduceLeft().reduceRight()

	private fun reduceRight(): Tunnel = if (pods.endsWith("....")) Tunnel(pods.dropLast(2), left).reduceRight() else this

	private fun reduceLeft(): Tunnel = if (pods.startsWith("....")) Tunnel(pods.drop(2), left - 2).reduceLeft() else this

}

class Day12 : DayXX {
	private fun nextGen(gen: Tunnel, rules: Map<String, String>) =
			(gen.pods.indices)
					.map { idx -> rules[sub(gen.pods, idx)] ?: '.' }
					.joinToString("")
					.extend(gen.left)
					.reduce()
//          .apply { println(this) }

	private fun sub(gen: String, idx: Int): String {
		return "..$gen..".substring(idx, idx + 5)
	}

	private fun run(lines: List<String>, maxGen: Long): Long {
		val initial = Tunnel(".." + lines[0].split(":")[1].trim() + "..", 2)
		val rules = lines.drop(2).map { line -> line.split(" ").run { Pair(this[0], this[2]) } }.toMap()

		return (1L..maxGen).fold(initial) { gen, _ -> nextGen(gen, rules) }
				.run {
					calcPuzzleResult()
				}
	}

	private fun Tunnel.calcPuzzleResult() = pods
			.mapIndexed { idx, ch -> if (ch == '#') (0L + idx) - this.left else 0L }
			.sum()

	fun run2(lines: List<String>, maxGen: Long): Any {
		val initial = Tunnel(".." + lines[0].split(":")[1].trim() + "..", 2)
		val rules = lines.drop(2).map { line -> line.split(" ").run { Pair(this[0], this[2]) } }.toMap()

		var gen = initial
		val hist = HashMap<String, Pair<Long, Tunnel>>()
		var idx = 0L
		while (!hist.containsKey(gen.pods) || !isMatch(maxGen, idx, hist[gen.pods])) {
			hist[gen.pods] = Pair(idx, gen)
			idx += 1L
//      print("${idx} ")
			gen = nextGen(gen, rules)
		}

		val remainingGen = maxGen - idx
		val diff = idx - hist[gen.pods]!!.first

		val diffLeft = gen.left - hist[gen.pods]!!.second.left
		val mult = remainingGen / diff

		val t = Tunnel(gen.pods, gen.left + diffLeft * mult)
		return t.calcPuzzleResult()

	}

	private fun isMatch(maxGen: Long, idx: Long, p: Pair<Long, Tunnel>?): Boolean {

		if (p == null)
			return false

		val diff = idx - p.first

		return ((maxGen - idx) % diff) == 0L
	}

	// 3221
	override fun analyse(lines: List<String>): Any {
		return run(lines, 20)
	}

	// 2600000001872
	override fun analyse2(lines: List<String>): Any {
		return run2(lines, 50_000_000_000L)
	}

}

private fun String.extend(left: Long): Tunnel {
	val prefix = if (this[0] == '#') ".." else if (this[1] == '#') "." else ""
	val postfix = if (this[lastIndex] == '#') ".." else if (this[lastIndex - 1] == '#') "." else ""
	return Tunnel(prefix + this + postfix, left + prefix.length)
}
