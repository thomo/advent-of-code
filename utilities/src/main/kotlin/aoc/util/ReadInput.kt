package aoc.util

import java.nio.file.Path
import kotlin.io.path.absolute

fun readFileAsLinesUsingUseLines(module: String, fileName: String): List<String> =
	Path.of(module, "src", "main", "resources", fileName).absolute().toFile().useLines { it.toList() }

fun chunkedByEmptyLines(lines: List<String>, ignoreEmptyChunks: Boolean = true): List<List<String>> =
	lines.fold(mutableListOf(mutableListOf<String>())) { acc, line ->
		if (line.isBlank()) acc += mutableListOf<String>()
		else {
			acc.last().add(line)
		}
		acc
	}.filterNot { it.isEmpty() && ignoreEmptyChunks }
