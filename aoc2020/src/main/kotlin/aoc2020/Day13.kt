package aoc2020

import kotlin.math.ceil

class Day13 {
	fun run1(lines: List<String>) {
		val earliest = lines[0].toInt()
		val busses = lines[1].split(",").filter { s -> !s.equals("x") }.map { s -> s.toInt() }

		val r = busses.map { b -> Pair(b, ceil(1.0 * earliest / b).toInt() * b - earliest) }
			.minByOrNull { p -> p.second }!!

		println(r.first * r.second)

	}

	fun run(lines: List<String>) {
		val busses = lines[1].split(",")
			.mapIndexedNotNull() { i, s -> if (s == "x") null else Pair(i.toLong(), s.toLong()) }
		// .sortedByDescending { it.second }

		println(busses)

		println("x: ${calc(busses)}")
	}

	fun calc(busses: List<Pair<Long, Long>>): Long {
		var time = busses[0].second - busses[0].first
		var x = busses[0].second

		var list = busses.drop(1)
		var yd = list[0].first
		var y = list[0].second

		while (list.isNotEmpty()) {
			if (Math.floorMod(time + yd, y) == 0L) {
				x *= y
				list = list.drop(1)
				if (list.isEmpty()) break
				yd = list[0].first
				y = list[0].second
			} else {
				time += x
			}
		}
		return time
	}

	private fun ggT(n: Long, m: Long): Long {
		if (m != 0L)
			return ggT(m, n % m)
		else
			return n

	}

	private fun match(busses: List<Pair<Long, Long>>, x: Long): Boolean =
		busses.all { p -> (x + p.first).rem(p.second) == 0L }

}
