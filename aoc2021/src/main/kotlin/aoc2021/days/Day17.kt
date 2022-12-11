package aoc2021.days

import aoc.days.DayXX

class Day17 : DayXX {

	data class TargetArea(val x: IntRange, val y: IntRange)

	fun parseLine(line: String): TargetArea {
		// target area: x=153..199, y=-114..-75
		val xRegex = """x=([-]?\d+)\.\.([-]?\d+)""".toRegex()
		val yRegex = """y=([-]?\d+)\.\.([-]?\d+)""".toRegex()
		val (x1, x2) = xRegex.find(line)!!.destructured
		val (y1, y2) = yRegex.find(line)!!.destructured
		return TargetArea(x1.toInt()..x2.toInt(), y1.toInt()..y2.toInt())
	}

	fun collectX(x: IntRange) = (1..x.last).filter { isXhit(it, 0, x) }

	fun isXhit(xSpeed: Int, xPos: Int, x: IntRange): Boolean =
		if (x.contains(xPos)) true
		else {
			if (x.last() < xPos || xSpeed == 0) false
			else isXhit(xSpeed - 1, xPos + xSpeed, x)
		}

	fun isYhit(ySpeed: Int, yPos: Int, y: IntRange): Boolean =
		if (y.contains(yPos)) true
		else {
			if (y.first() > yPos) false
			else isYhit(ySpeed - 1, yPos + ySpeed, y)
		}

	fun collectY(y: IntRange) = (y.first()..-y.first()).filter { isYhit(it, 0, y) }

	override fun analyse(lines: List<String>): Any {
		val targetArea = parseLine(lines[0])
		val xCandidates = collectX(targetArea.x)
		val yCandidates = collectY(targetArea.y)
		val n = yCandidates.maxOf { it }
		return (n * n + n) / 2
	}

	override fun analyse2(lines: List<String>): Any {
		val targetArea = parseLine(lines[0])
		val xCandidates = collectX(targetArea.x)
		val yCandidates = collectY(targetArea.y)

		var pairs = 0
		for (xSpeed in xCandidates) {
			for (ySpeed in yCandidates) {
				if (doesHit(xSpeed, ySpeed, 0, 0, targetArea)) pairs += 1
			}
		}
		return pairs;
	}

	private fun doesHit(xSpeed: Int, ySpeed: Int, x: Int, y: Int, target: Day17.TargetArea): Boolean {
		if (target.x.contains(x) && target.y.contains(y)) return true
		if ((x > target.x.last) || (y < target.y.first)) return false
		return doesHit(if (xSpeed == 0) 0 else xSpeed - 1, ySpeed - 1, x + xSpeed, y + ySpeed, target)
	}

}
