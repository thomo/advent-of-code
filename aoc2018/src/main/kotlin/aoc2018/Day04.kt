package aoc2018

import aoc.days.DayXX

data class SleepPeriod(val id: Int, val fallAsleep: Int, val wakeUp: Int) {
	fun duration() = wakeUp - fallAsleep
	fun sleepMinutes() = fallAsleep until wakeUp
}

class Day04 : DayXX {
	fun getMostSleptMinute(periods: List<SleepPeriod>, id: Int) = periods
			.filter { p -> p.id == id } // select sleep periods of guard
			.flatMap { p -> p.sleepMinutes() } // get all sleep minutes as a flat list
			.groupingBy { m -> m }.eachCount() // count how often a sleep minute exist
			.maxOfWithOrNull(Comparator { a, b -> a.value.compareTo(b.value) }) { e -> e } // select the max entry
			?.key

	fun searchMostSleeplingGuard(periods: List<SleepPeriod>) = periods
			.groupBy { sp -> sp.id }  // group by guard id -> id, list of sleeping periods
			.mapValues { entry -> entry.value.sumOf { sp -> sp.duration() } } // -> id, sum of sleeping
			.entries.sortedBy { entry -> entry.value } // sort by duration
			.last() // get the biggest
			.key // get id


	fun extractMinute(line: String) = """\[\d{4}-\d{2}-\d{2} \d{2}:(\d{2})\].*""".toRegex()
			.matchEntire(line)!!.groups[1]!!.value.toInt()

	fun extractGuardId(line: String) = """\[.*\] Guard #(\d+) begins shift""".toRegex()
			.matchEntire(line)!!.groups[1]!!.value.toInt()

	fun mapToSleepPeriods(lines: List<String>): List<SleepPeriod> {
		var guardId: Int = 0
		var fallsAsleep: Int = 0

		var result = mutableListOf<SleepPeriod>()

		for (line in lines) {
			when {
				line.contains("Guard") -> guardId = extractGuardId(line)
				line.contains("falls asleep") -> fallsAsleep = extractMinute(line)
				line.contains("wakes up") -> result.add(SleepPeriod(guardId, fallsAsleep, extractMinute(line)))
			}
		}

		return result
	}

	private fun getGuardIds(lines: List<String>) = lines
			.filter { line -> line.contains("Guard") }
			.map { line -> extractGuardId(line) }

	// 71748
	override fun analyse(lines: List<String>): Any {
		val sleepPeriods = mapToSleepPeriods(lines.sorted())
		val id = searchMostSleeplingGuard(sleepPeriods)
		val mostSleptMinute = getMostSleptMinute(sleepPeriods, id)!!
		return id * mostSleptMinute
	}

	// 106850
	override fun analyse2(lines: List<String>): Any {
		val sleepPeriods = mapToSleepPeriods(lines.sorted())
		val p = getGuardIds(lines.sorted())
				.map { id -> Pair(id, getMostSleptMinute(sleepPeriods, id)) }
				.filter { pair -> pair.second != null }
				.maxOfWith(Comparator { a, b -> a.second!!.compareTo(b.second!!) }) { pair -> pair }
		return p.first * p.second!!
	}

}

