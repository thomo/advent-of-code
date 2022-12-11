package aoc2020

import aoc.days.DayXX

class Day01 : DayXX {

	fun searchSum(numbers: List<Int>, cmp: Int): Int? {
		if (numbers.isEmpty()) return null
		val a = numbers.first()
		return numbers.drop(1).find { a + it == cmp }?.run { this * a } ?: searchSum(numbers.drop(1), cmp)
	}

	// 605364
	override fun analyse(lines: List<String>): Any {
		val numbers = lines.map { it.toInt() }
		return searchSum(numbers, 2020)!!
	}

	private fun search3Sum(numbers: List<Int>): Any {
		val a = numbers.first()
		return searchSum(numbers.drop(1), 2020 - a)?.run { this * a } ?: search3Sum(numbers.drop(1))
	}

	// 128397680
	override fun analyse2(lines: List<String>): Any {
		val numbers = lines.map { it.toInt() }
		return search3Sum(numbers)
	}

}
