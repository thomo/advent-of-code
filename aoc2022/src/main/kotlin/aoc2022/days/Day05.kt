package aoc2022.days

import aoc.days.DayXX

class Day05 : DayXX {
	override fun analyse(lines: List<String>): Any {
		val stacks = readStacks(lines)
		val commands = readCommands(lines)
		return commands
			.fold(stacks) { acc, cmd ->
				val movedCarets = cmd[0]
				val srcIdx = cmd[1] - 1
				val dstIdx = cmd[2] - 1

				val moved = acc[srcIdx].takeLast(movedCarets).reversed()
				repeat(movedCarets) { acc[srcIdx].removeLast() }
				acc[dstIdx].addAll(moved)
				acc
			}
			.map {
				it.last()
			}
			.joinToString("")
	}

	private fun readCommands(lines: List<String>) =
		lines.dropWhile { it.isNotBlank() }.drop(1)
			.map { it.split(" ") }
			.map { listOf(it[1], it[3], it[5]).map { it.toInt() } }

	private fun readStacks(lines: List<String>): List<MutableList<Char>> {
		val stackLines = lines.takeWhile { it.isNotBlank() }.reversed()
		val max = stackLines.first().trim().split(" ").last().toInt() - 1
		val stacks = List<MutableList<Char>>(max + 1) { mutableListOf<Char>() }
		stackLines.drop(1).forEach { layer ->
			(0..max).forEach { i ->
				val caret = layer[1 + i * 4]
				if (caret != ' ') stacks[i].add(caret)
			}
		}
		return stacks
	}

	override fun analyse2(lines: List<String>): Any {
		val stacks = readStacks(lines)
		val commands = readCommands(lines)
		return commands
			.fold(stacks) { acc, cmd ->
				val movedCarets = cmd[0]
				val srcIdx = cmd[1] - 1
				val dstIdx = cmd[2] - 1

				val moved = acc[srcIdx].takeLast(movedCarets)
				repeat(movedCarets) { acc[srcIdx].removeLast() }
				acc[dstIdx].addAll(moved)
				acc
			}
			.map {
				it.last()
			}
			.joinToString("")

	}

}
