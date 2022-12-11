package aoc2020

import kotlin.math.max
import kotlin.math.min

class Day9 {
	fun run(lines: List<String>) {
		val numbers = lines.map { l -> l.toLong() }

		for (i in 0..(numbers.size - 25)) {
			val slice = numbers.subList(i, i + 25)
			val checkedVal = numbers[i + 25]
			if (!isValid(slice, checkedVal)) {
				println(checkedVal)
				break
			}
		}

		val sum: Long = 21806024

		for (i in 0..numbers.size - 1) seachSum(numbers.drop(i), sum)?.let { p ->
			println("" + p.first + ", " + p.second + " = " + (p.first + p.second))
			return
		}
		// 900278, 2085917 = 1877905184926
	}

	private fun seachSum(numbers: List<Long>, sum: Long): Pair<Long, Long>? {
		var mysum = 0L
		var min = numbers[0]
		var max = numbers[0]
		for (i in numbers) {
			mysum += i
			min = min(min, i)
			max = max(max, i)
			if (mysum == sum) return Pair(min, max)
			if (mysum > sum) return null
		}
		return null
	}

	private fun isValid(list: List<Long>, checkedVal: Long): Boolean {
		if (list.size == 2) {
			return list[0] + list[1] == checkedVal
		}
		val head = list[0]
		for (summand in list.drop(1)) {
			if (head + summand == checkedVal) return true
		}
		return isValid(list.drop(1), checkedVal)
	}

}
