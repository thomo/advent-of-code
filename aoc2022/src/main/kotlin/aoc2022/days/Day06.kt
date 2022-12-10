package aoc2022.days

import aoc.days.DayXX

class Day06 : DayXX {
	override fun analyse(lines: List<String>): Any {
		val data = lines[0].toCharArray().toList()
		var last = (listOf('x') + data.take(3)).toMutableList()

		data.drop(3).forEachIndexed { index, c ->
			last.add(c)
			last = last.drop(1).toMutableList()
			if (last.toSet().size == 4) return index + 4
		}
		return -1
	}

	override fun analyse2(lines: List<String>): Any {
		val data = lines[0].toCharArray().toList()
		var last = (listOf('x') + data.take(13)).toMutableList()

		data.drop(13).forEachIndexed { index, c ->
			last.add(c)
			last = last.drop(1).toMutableList()
			if (last.toSet().size == 14) return index + 14
		}
		return -1
	}

}
