package aoc2021.days

import aoc.days.DayXX

class Day07 : DayXX {
	override fun analyse(lines: List<String>): Any {
		val hp = readHorizontalPositions(lines[0])
		val tp = calcTargetPositions(hp)
		return calcFuel(hp, tp)
	}

	fun calcFuel(hp: List<Int>, tp: Int) = hp.map { Math.abs(it - tp) }.sum()

	fun readHorizontalPositions(line: String) = line.split(",").map { it.toInt() }

	fun calcTargetPositions(hp: List<Int>) = hp.sorted().get(hp.size / 2)

	override fun analyse2(lines: List<String>): Any {
		val hp = readHorizontalPositions(lines[0])
		return calcTargetPositions2(hp).second
	}

	private fun calcTargetPositions2(hp: List<Int>): Pair<Int, Int> {
		val min = hp.minOf { it }
		val max = hp.maxOf { it }

		return (min..max).map { i -> Pair(i, calcFuel2(hp, i)) }.minByOrNull { it.second }!!
	}

	fun calcFuel2(hp: List<Int>, tp: Int) = hp.map { Math.abs(it - tp) }.map { (it + 1) * it / 2 }.sum()
}
