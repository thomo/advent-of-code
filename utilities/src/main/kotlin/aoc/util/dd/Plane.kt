package aoc.util.dd

import java.lang.Math.abs

/**
 * Umbauen in - keine Data Class
 * zwei constructoren
 * - background, dimX, dimY
 * - background, cells
 *
 *
 *  - add generate image
 */

data class Plane<T>(val background: T, val cells: Collection<Cell<T>>) {
	val dimX = minMax(cells.map { it.pos.x })
	val width = dimX.let { abs(it.second - it.first) + 1 }
	val dimY = minMax(cells.map { it.pos.y })
	val height = dimY.let { abs(it.second - it.first) + 1 }

	private val rowMap = cells.groupBy { cell -> cell.pos.y }

	private fun minMax(list: List<Int>) = Pair(list.minOf { it }, list.maxOf { it })

	fun getRow(y: Int) = cells.filter { it.pos.y == y }.sortedBy { it.pos.x }
	fun getCol(x: Int) = cells.filter { it.pos.x == x }.sortedBy { it.pos.y }

	fun getCell(pos: Coord) =
		if (isValid(pos)) {
			rowMap[pos.y]?.find { it.pos == pos } ?: Cell(pos, background)
		} else null

	fun isValid(pos: Coord) = (dimX.first..dimX.second).contains(pos.x) && (dimY.first..dimY.second).contains(pos.y)

	fun outputln() {
		output()
		println()
	}

	fun lines(): List<String> {
		val (xMin, _) = dimX
		val (yMin, _) = dimY

		return (0 until height)
			.map { y ->
				val inLine = cells.filter { it.pos.y == yMin + y }.filterNot { it.content == background }
				(0 until width)
					.map { x -> inLine.find { it.pos.x == xMin + x }?.content ?: background }
					.joinToString("")
			}
	}

	fun output() {
		val (xMin, _) = dimX
		val (yMin, _) = dimY

		lines().forEach { println(it) }

		println("($xMin, $yMin) - (${dimX.second}, ${dimY.second}) | w: $width, h: $height")

		println(cells
			.groupBy { c -> c.content }
			.mapValues { kv -> kv.value.size }
			.map { kv -> "${kv.key}: ${kv.value}" }
			.joinToString(" ")
		)
	}

	fun starNeighbors(c: Cell<T>, limit: Boolean) =
		(-1..1).flatMap { x ->
			(-1..1).mapNotNull { y ->
				val pos = Coord(c.pos.x + x, c.pos.y + y)
				if (x == 0 && y == 0) null
				else getCell(pos) ?: if (limit) null else Cell(pos, background)
			}
		}
}
