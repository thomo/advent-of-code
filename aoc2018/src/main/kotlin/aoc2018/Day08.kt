package aoc2018

import aoc.days.DayXX

class Day08 : DayXX {
	data class Node(val children: List<Node>, val meta: List<Int>) {
		fun value(): Int {
			if (children.isEmpty()) return meta.sum()

			return meta
					.filterNot { m -> m == 0 }
					.map { m -> m - 1 }
					.filter { m -> m < children.size }
					.map { m -> children[m].value() }
					.sum()
		}

		fun metaValue(): Int = meta.sum() + children.map { c -> c.metaValue() }.sum()
	}

	// 40309
	override fun analyse(lines: List<String>): Any {
		var input = lines[0].split(' ').map { s -> s.toInt() }
		val root = extractNodes(input).first

		return root.metaValue()
	}


	private fun extractNodes(input: List<Int>): Pair<Node, Int> {
		val cntChildren = input[0]
		val cntMeta = input[1]
		var delta = 2
		var children = emptyList<Node>()

		(0 until cntChildren).forEach {
			val newInput = input.drop(delta)
			if (newInput.isNotEmpty()) {
				val result = extractNodes(newInput)
				delta += result.second
				children = children + result.first
			}
		}
		var meta = input.drop(delta)
		val n = Node(children, meta.take(cntMeta))

		return Pair(n, delta + cntMeta)
	}

	// 28779
	override fun analyse2(lines: List<String>): Any {
		var input = lines[0].split(' ').map { s -> s.toInt() }
		val root = extractNodes(input).first
		return root.value()
	}

}
