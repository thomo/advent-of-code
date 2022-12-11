package aoc2020

class Day10 {
	fun run(lines: List<String>) {
		val numbers = lines.map { l -> l.toLong() }.sorted()
		//val c1 = countDiff(numbers,1, 0)
		//val c3 = countDiff(numbers,3, 0) + 1
		//println("1: " + c1 + ", 3: " + c3 + " Sum: " + (c1*c3) )

		println(countPaths(numbers))
	}

	private fun countPaths(numbers: List<Long>): Long {
		val counts = mutableListOf<Long>()
		for (i in 1..numbers.size) {
			counts.add(0)
		}
		counts.set(numbers.size - 1, 1L)
		for (i in (numbers.size - 2) downTo 0) {
			var v = numbers[i]
			var pathCount = 0L
			for (j in 1..3) {
				val ic = i + j
				if (ic < numbers.size && numbers[ic] - v < 4) pathCount += counts[ic]
			}
			counts[i] = pathCount
		}
		var pathCount = 0L
		for (j in 0..2) {
			if (numbers[j] < 4) pathCount += counts[j]
		}
		return pathCount
	}

	private fun countVariants(numbers: List<Long>, i: Int): Long {
		val maxIdx = numbers.size - 1
		if (i == maxIdx) return 0
		var variants = 0L
		if (numbers[i + 1] - numbers[i] < 4) variants += 1
		if (i < maxIdx - 1 && numbers[i + 2] - numbers[i] < 4) variants += 1
		if (i < maxIdx - 2 && numbers[i + 3] - numbers[i] < 4) variants += 1
		if (variants == 1L) variants = 0
		return variants
	}

	private fun countDiff(numbers: List<Long>, diff: Long, initialValue: Long): Long {
		var cmp = initialValue
		var cnt = 0L
		for (i in numbers) {
			if (i - cmp == diff) cnt += 1
			cmp = i
		}

		return cnt
	}

}
