package aoc2018

import aoc.days.DayXX
import aoc.util.dd.Coord
import aoc.util.dd.Vector

class Day10 : DayXX {

	var width = 0
	var heigth = 0

	private fun areCohesive(pointsWithVelo: List<Vector>): Boolean {
		val currentHeight = heigth(pointsWithVelo)
		if (currentHeight > heigth) {
			return false
		}
		heigth = currentHeight
		return true
	}

	private fun width(pointsWithVelo: List<Vector>): Int {
		val minX = pointsWithVelo.minOf { v -> v.start.x }
		return pointsWithVelo.maxOf { v -> v.start.x } - minX
	}

	private fun heigth(pointsWithVelo: List<Vector>): Int {
		val minY = pointsWithVelo.minOf { v -> v.start.y }
		return pointsWithVelo.maxOf { v -> v.start.y } - minY
	}

	private fun output(pointsWithVelo: List<Vector>): List<String> {
		val minX = pointsWithVelo.minOf { v -> v.start.x }
		val length = pointsWithVelo.maxOf { v -> v.start.x } - minX
		val emptyLine = ".".repeat(length + 1)

		val lines = pointsWithVelo.groupBy { v -> v.start.y }

		return lines.keys.toSortedSet().map {
			var outputLine = emptyLine.toCharArray()
			lines[it]!!.forEach { v -> outputLine[v.start.x - minX] = '#' }
			outputLine.joinToString("")
		}

	}

	// position=<-40106,  10242> velocity=< 4, -1>
	// 000000000 111111 2222222 3333333333 44 555
	private fun parseLines(lines: List<String>) = lines.map { line ->
		line.split("<", ">", ",")
				.drop(1)
				.filterIndexed { idx, _ -> idx != 2 }
				.filterNot { it.isBlank() }
				.map { s -> s.trim().toInt() }
				.run { Vector(Coord(get(0), get(1)), get(2), get(3)) }
	}

	private fun solve(lines: List<String>): Pair<Int, List<String>> {
		var pointsWithVelo = parseLines(lines)

		width = width(pointsWithVelo)
		heigth = heigth(pointsWithVelo)

		var cnt = 0
		do {
			// output(pointsWithVelo)
			pointsWithVelo = pointsWithVelo.map { it.move() }
			cnt += 1
		} while (areCohesive(pointsWithVelo))
		pointsWithVelo = pointsWithVelo.map { it.move(-1) }
		val output = output(pointsWithVelo)
//		output.forEach { println(it) }
//		println(cnt - 1) // Answer for part 2
		return Pair(cnt - 1, output)
	}

	// RBCZAEPP
	override fun analyse(lines: List<String>): Any {
		return solve(lines).second.joinToString("\n", prefix = "\n")
	}

	// 10076
	override fun analyse2(lines: List<String>): Any {
		return solve(lines).first
	}

}

