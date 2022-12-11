package aoc2018

import aoc.days.DayXX
import aoc.util.dd.Cell
import aoc.util.dd.Coord
import aoc.util.dd.getConvexHull
import aoc.util.dd.manhattenDistance

class Day06 : DayXX {

	private lateinit var world: Map<Int, Set<Cell<Int>>>
	private val EQUAL_DIST: Int = 0
	lateinit var allPoints: MutableSet<Coord>

	private fun containsOnlyBoarderCells(cells: Set<Cell<Int>>, borderCells: Set<Cell<Int>>) =
			cells.map { c -> c.content }.toSet().minus(borderCells.map { c -> c.content }.toSet()).isNotEmpty()


	private fun getBorderCell(cells: Set<Cell<Int>>, boundaryBox: Pair<Coord, Coord>) =
			cells.filter { c -> c.pos.x == boundaryBox.first.x || c.pos.x == boundaryBox.second.x || c.pos.y == boundaryBox.first.y || c.pos.y == boundaryBox.second.y }.toSet()

	/**
	 * alle Cells die nicht auf dem Rand liegen
	 */
	fun getInnerCells(cells: Set<Cell<Int>>) = cells.minus(getConvexHull(cells));

	fun getStartCells(lines: List<String>) = lines
			.map { line ->
				line.split(",").map { s -> s.trim().toInt() }
			}
			.mapIndexed { idx, co -> Cell<Int>(Coord(co[0], co[1]), idx + 1) } // 0 ist reserviert
			.toSet()

	private fun updateWorld(newCells: Set<Cell<Int>>) {
		val id2cells = newCells.groupBy { c -> c.content }
		allPoints.addAll(newCells.map { c -> c.pos })
		world = world.mapValues { kv ->
			if (id2cells.containsKey(kv.key))
				mergeSets(kv.value, id2cells[kv.key]!!.toSet())
			else
				kv.value
		}
	}

	private fun mergeSets(a: Set<Cell<Int>>, b: Set<Cell<Int>>): Set<Cell<Int>> {
		val result = a.toMutableSet()
		result.addAll(b)
		return result
	}

	private fun containsInnerCells(cells: Set<Cell<Int>>, innerIds: Set<Int>) = cells.any { c -> innerIds.contains(c.content) }

	private fun calcNewCells(edgeCells: Set<Cell<Int>>) = edgeCells
			.flatMap { c ->
				listOf(
						Cell<Int>(Coord(c.pos.x + 1, c.pos.y), c.content), Cell<Int>(Coord(c.pos.x - 1, c.pos.y), c.content),
						Cell<Int>(Coord(c.pos.x, c.pos.y + 1), c.content), Cell<Int>(Coord(c.pos.x, c.pos.y - 1), c.content))
			}
			.filterNot { c -> edgeCells.contains(c) } // Zellen aus der letzten Runde ignorieren
			.filterNot { c -> allPoints.contains(c.pos) } // schon besetzte Zellen ignorieren
			.groupBy { c -> c.pos } // Koordinate 2 Cells
			.mapValues { kv -> if (kv.value.toSet().size > 1) EQUAL_DIST else kv.value[0].content }
			.map { kv -> Cell<Int>(Coord(kv.key.x, kv.key.y), kv.value) } // zurück in Cell wandeln
			.toSet()

	// 3989
	override fun analyse(lines: List<String>): Any {
		var newCells = getStartCells(lines)
		allPoints = newCells.map { c -> c.pos }.toMutableSet()
		world = newCells.groupBy { c -> c.content }.mapValues { kv -> kv.value.toSet() }

		val boundaryBox = Pair(Coord(newCells.minOf { c -> c.pos.x }, newCells.minOf { c -> c.pos.y }), Coord(newCells.maxOf { c -> c.pos.x }, newCells.maxOf { c -> c.pos.y }))

		val borderCells = getBorderCell(newCells, boundaryBox).toMutableSet()

		do {
			newCells = calcNewCells(newCells.filter { c -> c.content != EQUAL_DIST }.toSet())
			borderCells.addAll(getBorderCell(newCells, boundaryBox))
			updateWorld(newCells)
		} while (containsOnlyBoarderCells(newCells, borderCells))

		val borderIds = borderCells.map { c -> c.content }.toSet()
		val area2cells = world.filterNot { kv -> borderIds.contains(kv.key) }

		// calc area size
		val area2size = area2cells.mapValues { kv -> kv.value.size }

		// select max
		return area2size.values.maxOrNull() ?: 0
	}

	// 49715
	override fun analyse2(lines: List<String>): Any {
		val points = getStartCells(lines).map { c -> c.pos }

		val boundaryBox = Pair(Coord(points.minOf { c -> c.x }, points.minOf { c -> c.y }), Coord(points.maxOf { c -> c.x }, points.maxOf { c -> c.y }))
		return (boundaryBox.first.x..boundaryBox.second.x).map { x ->

			(boundaryBox.first.y..boundaryBox.second.y)
					.map { y -> points.fold(0) { sum, p -> sum + manhattenDistance(p, Coord(x, y)) } }
					.filter { sum -> sum < 10_000 }.count()

		}.sum()
	}
}