package aoc2022.days

import aoc.days.DayXX
import aoc.util.dd.Cell
import aoc.util.dd.Coord
import aoc.util.dd.Plane

class Day09 : DayXX {

	fun headMovement(lines: List<String>) =
		lines
			.map { Pair(it[0], it.drop(2).toInt()) }
			.fold(listOf(Coord(0, 0))) { movements, cmd ->
				val steps = cmd.second
				val dir = cmd.first
				(1..steps).fold(movements) { moves, _ ->
					moves + moves.last().move(dir)
				}
			}

	override fun analyse(lines: List<String>) =
		headMovement(lines).fold(Cell(Coord(0, 0), setOf(Coord(0, 0)))) { tc, h ->
			val newT = moveTail(h, tc.pos)
			Cell(newT, tc.content + newT)
		}
			.content
			.apply { Plane('.', listOf(Cell(Coord(0, 0), 's')) + this.map { Cell(it, '#') }).outputln() }
			.size

	fun moveTail(h: Coord, t: Coord): Coord {
		if (h == t) return t

		if (h.isNeighbor(t)) return t

		val newT = if (h.x == t.x) if (h.isBelow(t)) t.down() else t.up()
		else if (h.y == t.y) if (h.isLeftOf(t)) t.left() else t.right()
		else {
			val a = if (h.isLeftOf(t)) t.left() else t.right()
			if (h.isBelow(t)) a.down() else a.up()
		}
		return newT
	}

	override fun analyse2(lines: List<String>): Any {
		var headMoves = headMovement(lines)

		repeat(9) {
			var tailMoves = moveSub(headMoves)
			headMoves = tailMoves.content
		}
		return headMoves
			.toSet()
			.apply { Plane('.', listOf(Cell(Coord(0, 0), 's')) + this.map { Cell(it, '#') }).outputln() }
			.size
	}

	private fun moveSub(headMovement: List<Coord>) =
		headMovement.fold(Cell(Coord(0, 0), listOf(Coord(0, 0)))) { tc, h ->
			val newT = moveTail(h, tc.pos)
			Cell(newT, tc.content + newT)
		}

}
