package aoc2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Day13Test {

	private lateinit var busses: List<Pair<Long, Long>>
	private lateinit var cut: Day13

	@BeforeEach
	fun setup() {
		busses = listOf(
			Pair(0L, 17L), Pair(11L, 37L), Pair(17L, 409L), Pair(19L, 29L),
			Pair(30L, 13L), Pair(40L, 23L), Pair(48L, 373L), Pair(58L, 41L), Pair(67L, 19L)
		)
		cut = Day13()
	}

	@Test
	fun d13_2() {
		val x = cut.calc(busses.subList(0, 2))
		assertEquals(0L, Math.floorMod(x + 0L, 17L))
		assertEquals(0L, Math.floorMod(x + 11L, 37L))
		assertEquals(544, x)
	}

	@Test
	fun d13_4() {
		val x = cut.calc(busses.subList(0, 4))
		assertEquals(0L, Math.floorMod(x + 0L, 17L))
		assertEquals(0L, Math.floorMod(x + 11L, 37L))
		assertEquals(0L, Math.floorMod(x + 17L, 409L))
		assertEquals(0L, Math.floorMod(x + 19L, 29L))
		assertEquals(5388558, x)
	}

	@Test
	fun d13_5() {
		val x = cut.calc(busses.subList(0, 5))
		assertEquals(0L, Math.floorMod(x + 0L, 17L))
		assertEquals(0L, Math.floorMod(x + 11L, 37L))
		assertEquals(0L, Math.floorMod(x + 17L, 409L))
		assertEquals(0L, Math.floorMod(x + 19L, 29L))
		assertEquals(0L, Math.floorMod(x + 30L, 13L))
		assertEquals(79994248, x)
	}

	@Test
	fun d13_6() {
		val x = cut.calc(busses.subList(0, 6))
		assertEquals(0L, Math.floorMod(x + 0L, 17L))
		assertEquals(0L, Math.floorMod(x + 11L, 37L))
		assertEquals(0L, Math.floorMod(x + 17L, 409L))
		assertEquals(0L, Math.floorMod(x + 19L, 29L))
		assertEquals(0L, Math.floorMod(x + 30L, 13L))
		assertEquals(0L, Math.floorMod(x + 40L, 23L))
		assertEquals(1049868218, x)
	}

	@Test
	fun d13_7() {
		val x = cut.calc(busses.subList(0, 7))
		assertEquals(0L, Math.floorMod(x + 0L, 17L))
		assertEquals(0L, Math.floorMod(x + 11L, 37L))
		assertEquals(0L, Math.floorMod(x + 17L, 409L))
		assertEquals(0L, Math.floorMod(x + 19L, 29L))
		assertEquals(0L, Math.floorMod(x + 30L, 13L))
		assertEquals(0L, Math.floorMod(x + 40L, 23L))
		assertEquals(0L, Math.floorMod(x + 48L, 373L))
		assertEquals(828643326819, x)
	}

	@Test
	fun d13_8() {
		val x = cut.calc(busses.subList(0, 8))
		assertEquals(0L, Math.floorMod(x + 0L, 17L))
		assertEquals(0L, Math.floorMod(x + 11L, 37L))
		assertEquals(0L, Math.floorMod(x + 17L, 409L))
		assertEquals(0L, Math.floorMod(x + 19L, 29L))
		assertEquals(0L, Math.floorMod(x + 30L, 13L))
		assertEquals(0L, Math.floorMod(x + 40L, 23L))
		assertEquals(0L, Math.floorMod(x + 48L, 373L))
		assertEquals(0L, Math.floorMod(x + 58L, 41L))
		assertEquals(18301795782942, x)
	}

	@Test
	fun d13_9() {
		val x = cut.calc(busses)
		assertEquals(0L, Math.floorMod(x + 0L, 17L), "17")
		assertEquals(0L, Math.floorMod(x + 11L, 37L), "37")
		assertEquals(0L, Math.floorMod(x + 17L, 409L), "409")
		assertEquals(0L, Math.floorMod(x + 19L, 29L), "29")
		assertEquals(0L, Math.floorMod(x + 30L, 13L), "13")
		assertEquals(0L, Math.floorMod(x + 40L, 23L), "23")
		assertEquals(0L, Math.floorMod(x + 48L, 373L), "373")
		assertEquals(0L, Math.floorMod(x + 58L, 41L), "41")
		assertEquals(0L, Math.floorMod(x + 67L, 19L), "19")
		assertEquals(530015546283687, x)
	}
}
