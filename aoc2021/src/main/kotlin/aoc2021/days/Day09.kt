package aoc2021.days

import aoc.days.DayXX
import utils.Field
import utils.FieldPos

class Day09 : DayXX {
	override fun analyse(lines: List<String>): Any {
		val map = parseInput(lines)
		val listOfLowest = getLowest(map)
		return listOfLowest.sumOf { map.get(it) + 1 }
	}

	fun getLowest(field: Field<Int>) =
		(0 until field.size).mapNotNull { idx -> if (isLowest(idx, field)) idx else null }

	fun isLowest(idx: Int, map: Field<Int>): Boolean {
		val v = map.get(idx)
		val neighborValues = map.getValues(map.getStraightNeighborPos(idx))
		return neighborValues.all { it > v }
	}

	fun parseInput(lines: List<String>) = Field(lines.map {
		it.toCharArray().map { it.toString().toInt() }
	})

	override fun analyse2(lines: List<String>): Any {
		val map = parseInput(lines)
		val listOfLowest = getLowest(map)
		return listOfLowest.map { getBasin(it, map).size }.sortedByDescending { it }.take(3)
			.fold(1) { acc, i -> acc * i }
	}

	fun getBasin(idx: Int, map: Field<Int>): Set<FieldPos> {
		var first = map.getStraightNeighborPos(idx).filter { map.get(it) != 9 }.toMutableSet()
		first.add(map.getPos(idx))
		var basin = first.toSet()
		do {
			val oldSize = basin.size
			basin = basin.flatMap { map.getStraightNeighborPos(it) }.filter { map.get(it) != 9 }.toSet()
		} while (oldSize != basin.size)
		return basin
	}

}
