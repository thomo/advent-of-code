package aoc2020

import aoc.days.DayXX

class Day03 : DayXX {

	fun calcX(x: Int, inc: Int, max: Int): Int = (x + inc) % max

	private fun calc(lines: List<String>, dx: Int, dy: Int): Long {
		val h = lines.size
		val w = lines[0].length
		var x = 0
		var y = 0

		var cnt = 0

		do {
			x = calcX(x, dx, w)
			y += dy

			if (lines[y][x] == '#') {
				++cnt
			}
		} while (y < (h - 1))
		return cnt.toLong()
	}

	// 232
	override fun analyse(lines: List<String>): Any {
		return calc(lines, 3, 1)
	}

	// 3952291680
	override fun analyse2(lines: List<String>): Any {
		return calc(lines, 1, 1) *
				calc(lines, 3, 1) *
				calc(lines, 5, 1) *
				calc(lines, 7, 1) *
				calc(lines, 1, 2)
	}
}
