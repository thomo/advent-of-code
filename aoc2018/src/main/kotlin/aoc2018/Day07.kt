package aoc2018

import aoc.days.DayXX

class Day07 : DayXX {

	private val IDLE: Char = '.'

	private fun parse(lines: List<String>) = lines.map { line -> Pair(line[5], line[36]) }

	// SCLPAMQVUWNHODRTGYKBJEFXZI
	override fun analyse(lines: List<String>): Any {
		val instructions = parse(lines)
		val node2preconditions = instructions.groupBy { i -> i.second }.mapValues { kv -> kv.value.map { ab -> ab.first } }
		val unfinishedNodes = node2preconditions.keys.toMutableSet()
		val readyNodes = instructions.map { i -> i.first }.minus(unfinishedNodes).toMutableSet().toMutableList()


		while (unfinishedNodes.isNotEmpty()) {
			val next = findCandidates(node2preconditions, unfinishedNodes, readyNodes).first()
			readyNodes.add(next)
			unfinishedNodes.remove(next)
		}
		return readyNodes.joinToString("")
	}

	private fun findCandidates(node2preconditions: Map<Char, List<Char>>, unfinishedNodes: Set<Char>, readyNodes: List<Char>) =
			unfinishedNodes.filter { n -> readyNodes.containsAll(node2preconditions[n]!!) }.sorted()

	// 1234
	override fun analyse2(lines: List<String>) = a2(lines, 5, 60)

	fun a2(lines: List<String>, cnt: Int, offset: Int): Any {
		var workers = listOf<Pair<Char, Int>>()
		val instructions = parse(lines)
		val node2preconditions = instructions.groupBy { i -> i.second }.mapValues { kv -> kv.value.map { ab -> ab.first } }
		val unfinishedNodes = node2preconditions.keys.toMutableSet()
		val wipNodes = instructions.map { i -> i.first }.minus(unfinishedNodes).toMutableSet().toMutableList()
		val readyNodes = mutableListOf<Char>()
		var duration = 0

		workers = workers + getWip(wipNodes[0], offset)

		while (unfinishedNodes.isNotEmpty() || wipNodes.isNotEmpty()) {
			outputWorker(duration, workers)

			workers = workers.map { Pair(it.first, it.second - 1) }

			val finished = workers.filter { it.second == 0 }.map { it.first }.sorted()
			if (finished.isNotEmpty()) {
				readyNodes.addAll(finished)
				wipNodes.removeAll(finished)

				workers = workers.filter { it.second > 0 }.toMutableList()

				val next = findCandidates(node2preconditions, unfinishedNodes - wipNodes, readyNodes).take(cnt - workers.size)
				next.forEach { workers = workers + getWip(it, offset) }
				wipNodes.addAll(next)
				unfinishedNodes.removeAll(next)
			}
			duration += 1
		}
		return duration
	}

	private fun getWip(c: Char, offset: Int) = Pair(c, offset + c.code - 64)


	private fun outputWorker(duration: Int, workers: List<Pair<Char, Int>>) {
		println("$duration " + workers.joinToString(" ") { w -> w.first.toString() })
	}

}

