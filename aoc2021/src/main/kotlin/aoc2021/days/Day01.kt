package aoc2021.days

import aoc.days.DayXX

class Day01 : DayXX {

	fun convertLines(lines: List<String>) = lines.map { l -> l.toInt() }

	override fun analyse(lines: List<String>): Int {
		val lines = convertLines(lines)
		return analyseIntList(lines)
	}

	private fun analyseIntList(lines: List<Int>): Int {
		var prev = lines[0];
		return lines.drop(1)
			.map { i -> val cmp = i > prev; prev = i; cmp }
			.filter { b -> b }
			.size
	}

	override fun analyse2(lines: List<String>): Int {
		val lines = convertLines(lines)

		var prev = lines.subList(0, 2);
		val slidedList = lines.drop(2)
			.map { l ->
				val ml = prev.toMutableList()
				ml.add(l)
				val sum = ml.sum()
				prev = ml.drop(1)
				sum
			}
		return analyseIntList(slidedList)
	}

}
