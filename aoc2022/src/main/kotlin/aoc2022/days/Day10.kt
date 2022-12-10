package aoc2022.days

import aoc.days.DayXX
import aoc.util.dd.Cell
import aoc.util.dd.Coord
import aoc.util.dd.Plane

class Day10 : DayXX {
	private fun faktor(xList: List<Long>, idx: Int) = xList[idx - 1] * idx

	private fun toCmd(line: String): Command = when (line) {
		"noop" -> Noop()
		else -> Addx(line.drop(5).toLong())
	}

	fun toCrtPos(cycle: Int): Coord = Coord((cycle - 1) % 40, (cycle - 1) / 40)

	private fun calcXList(lines: List<String>) = lines.asSequence()
		.map { toCmd(it) }
		.fold(listOf(1L)) { x, cmd -> x + cmd.exec(x.last()) }
		.take(240)

	override fun analyse(lines: List<String>): Any {
		val xList = calcXList(lines)
		return listOf(20, 60, 100, 140, 180, 220).map { faktor(xList, it) }.sum()
	}

	override fun analyse2(lines: List<String>): List<String> {
		val cells = calcXList(lines)
			.mapIndexed { index, x ->
				Cell(
					toCrtPos(index + 1),
					if (listOf(x - 1, x, x + 1).contains((index % 40).toLong())) '#' else '.'
				)
			}

		val plane = Plane(' ', cells)
		plane.outputln()
		return plane.lines()
	}

}

sealed interface Command {
	abstract fun exec(x: Long): List<Long>
}

class Noop : Command {
	override fun exec(x: Long) = listOf(x)
}

class Addx(val v: Long) : Command {
	override fun exec(x: Long) = listOf(x, x + v)
}
