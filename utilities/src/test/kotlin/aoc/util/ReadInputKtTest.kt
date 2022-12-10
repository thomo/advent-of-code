package aoc.util

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ReadInputKtTest {

	@Test
	fun shouldChunkedByEmptyLines() {
		val input = listOf(
			"a",
			"a",
			"a",
			"      ",
			"b",
			"b",
			" ",
			"c",
			"c",
			"",
			"d",
			"d",
			"\t",
			"",
			"e",
			"e"
		)

		val result = chunkedByEmptyLines(input, false)

		assertAll(
			{ assertEquals(6, result.size) },
			{ assertEquals(listOf("a", "a", "a"), result[0]) },
			{ assertEquals(listOf("b", "b"), result[1]) },
			{ assertEquals(listOf("c", "c"), result[2]) },
			{ assertEquals(listOf("d", "d"), result[3]) },
			{ assertEquals(emptyList<String>(), result[4]) },
			{ assertEquals(listOf("e", "e"), result[5]) },
		)
	}

	@Test
	fun shouldChunkedByEmptyLinesButIgnoreEmptyOnes() {
		val input = listOf(
			"a",
			"a",
			"a",
			"      ",
			"      ",
			"      ",
			"b",
			"b",
			" ",
			"      ",
			"c",
			"c",
		)

		val result = chunkedByEmptyLines(input)

		assertAll(
			{ assertEquals(3, result.size) },
			{ assertEquals(listOf("a", "a", "a"), result[0]) },
			{ assertEquals(listOf("b", "b"), result[1]) },
			{ assertEquals(listOf("c", "c"), result[2]) }
		)
	}

}
