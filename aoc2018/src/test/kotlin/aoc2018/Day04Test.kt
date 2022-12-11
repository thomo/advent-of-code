package aoc2018

import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class Day04Test {
	private lateinit var sleepPeriods: List<SleepPeriod>
	private val cut = Day04()

	private val testInput = listOf(
			"[1518-11-01 00:00] Guard #10 begins shift",
			"[1518-11-01 00:05] falls asleep",
			"[1518-11-01 00:25] wakes up",
			"[1518-11-01 00:30] falls asleep",
			"[1518-11-01 00:55] wakes up",
			"[1518-11-01 23:58] Guard #99 begins shift",
			"[1518-11-02 00:40] falls asleep",
			"[1518-11-02 00:50] wakes up",
			"[1518-11-03 00:05] Guard #10 begins shift",
			"[1518-11-03 00:24] falls asleep",
			"[1518-11-03 00:29] wakes up",
			"[1518-11-04 00:02] Guard #99 begins shift",
			"[1518-11-04 00:36] falls asleep",
			"[1518-11-04 00:46] wakes up",
			"[1518-11-05 00:03] Guard #99 begins shift",
			"[1518-11-05 00:45] falls asleep",
			"[1518-11-05 00:55] wakes up"
	)

	@BeforeEach
	internal fun setUp() {
		sleepPeriods = cut.mapToSleepPeriods(testInput)
	}

	@Test
	internal fun shouldIdentifyMostSleptMinute() {
		assertEquals(24, cut.getMostSleptMinute(sleepPeriods, 10))
	}

	@Test
	internal fun shouldExtractGuardId() {
		assertEquals(10, cut.extractGuardId(testInput[0]))
	}

	@Test
	internal fun shouldExtractMinute() {
		assertEquals(25, cut.extractMinute(testInput[2]))
	}

	@Test
	internal fun shouldCreateSleepPeriod() {
		val result = cut.mapToSleepPeriods(testInput)

		assertThat(result, hasItem(SleepPeriod(10, 5, 25)))
		assertThat(result, hasItem(SleepPeriod(10, 30, 55)))
	}

	@Test
	internal fun shouldSearchMostSleeplingGuard() {
		assertEquals(10, cut.searchMostSleeplingGuard(sleepPeriods))
	}

	@Test
	fun analyse() {
		assertEquals(240, cut.analyse(testInput))
	}

	@Test
	fun analyse2() {
		assertEquals(4455, cut.analyse2(testInput))
	}
}

