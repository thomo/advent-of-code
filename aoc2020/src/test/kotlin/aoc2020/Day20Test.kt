package aoc2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Day20Test {
	private lateinit var cut: Day20

	@BeforeEach
	fun setup() {
	}

	@Test
	fun tileMod0() {
		val t = Day20.Tile(1, listOf("1234", "5678", "90ab", "cdef"))

		val content = t.content()

		assertEquals("67", content[0])
		assertEquals("0a", content[1])
	}

	@Test
	fun tileMod1() {
		val t = Day20.Tile(1, listOf("1234", "5678", "90ab", "cdef"))

		t.mod()
		assertEquals("4321", t.top())
		assertEquals("159c", t.right())
		assertEquals("fedc", t.bottom())
		assertEquals("48bf", t.left())

		val content = t.content()
		assertEquals("76", content[0])
		assertEquals("a0", content[1])
	}

	@Test
	fun tileMod2() {
		val t = Day20.Tile(1, listOf("1234", "5678", "90ab", "cdef"))

		t.mod()
		t.mod()
		assertEquals("c951", t.top())
		assertEquals("1234", t.right())
		assertEquals("fb84", t.bottom())
		assertEquals("cdef", t.left())

		val content = t.content()
		assertEquals("06", content[0])
		assertEquals("a7", content[1])
	}
}
