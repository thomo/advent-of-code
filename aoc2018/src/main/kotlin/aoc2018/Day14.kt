package aoc2018

import aoc.days.DayXX

class Day14 : DayXX {

	// 2688510125
	override fun analyse(lines: List<String>): Any {
		val prefixLength = lines[0].toInt()
		val result = receipes(prefixLength + 10)
		return result.first.drop(prefixLength).take(10).joinToString("")
	}

	fun receipes(minLength: Int): Triple<List<Int>, Int, Int> {
		val r = mutableListOf<Int>()
		r.add(3)
		r.add(7)
		var f = 0
		var s = 1

		while (r.size < minLength) {
			val fs = nextReceipes(r, f, s)
			f = fs.first
			s = fs.second
		}

		return Triple(r, f, s)
	}

	private fun nextReceipes(r: MutableList<Int>, f: Int, s: Int): Pair<Int, Int> {
		val sum = r[f] + r[s]
		if (sum > 9) {
			r.add(1)
			r.add(sum - 10)
		} else {
			r.add(sum)
		}
		return Pair((f + r[f] + 1) % r.size, (s + r[s] + 1) % r.size)
	}

	// 20188250
	override fun analyse2(lines: List<String>): Any {

		val pattern = lines[0]
		val len = pattern.length + 1

		val r = mutableListOf<Int>()
		r.add(3)
		r.add(7)
		var f = 0
		var s = 1
		var last = ""

		do {
			val fs = nextReceipes(r, f, s)
			f = fs.first
			s = fs.second
			last = r.takeLast(len).joinToString("")
		} while (!last.contains(pattern))

		return r.size - len + if (last.startsWith(pattern)) 0 else 1
	}

}


fun main() {
	val app = Day14()
	val lines = listOf<String>("084601")// readFileAsLinesUsingUseLines("exercises/day14/thomo/input.txt")

	val startTime = System.currentTimeMillis()
	val result = app.analyse(lines)
	println("result 1: $result")
	println("analysis1 duration: " + (System.currentTimeMillis() - startTime))
	println("---------------------------------------------")

	val startTime2 = System.currentTimeMillis()
	val result2 = app.analyse2(lines)
	println("result 2: $result2")
	println("analysis2 duration: " + (System.currentTimeMillis() - startTime2))
}
