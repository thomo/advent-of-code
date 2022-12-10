package aoc2022.days

import aoc.days.DayXX
import aoc.util.dd.Cell
import aoc.util.dd.Coord
import aoc.util.dd.Plane
import kotlin.math.min

class Day08 : DayXX {

	private fun extractTreee(lines: List<String>) = lines.flatMapIndexed { y, line ->
		line.mapIndexed { x, c -> Cell(Coord(x, y), c.digitToInt()) }
	}

	private fun getVisibleTrees(row: List<Cell<Int>>): List<Cell<Int>> {
		var visibleHeight = -1
		return row.filter {
			if (it.content > visibleHeight) {
				visibleHeight = it.content
				true
			} else {
				false
			}
		}
	}

	override fun analyse(lines: List<String>): Any {
		val trees = extractTreee(lines)
		val field = Plane(0, trees)

		val rowNumber = field.height
		val colNumber = field.width

		val visibleTrees = mutableSetOf<Cell<Int>>()
		visibleTrees.addAll(field.getRow(0))
		visibleTrees.addAll(field.getRow(rowNumber - 1))
		visibleTrees.addAll(field.getCol(0))
		visibleTrees.addAll(field.getCol(colNumber - 1))

		(1 until rowNumber - 1)
			.forEach { y ->
				val row = field.getRow(y)
				getVisibleTrees(row).forEach { visibleTrees.add(it) }
				getVisibleTrees(row.reversed()).forEach { visibleTrees.add(it) }
			}

		(1 until colNumber - 1)
			.forEach { x ->
				val row = field.getCol(x)
				getVisibleTrees(row).forEach { visibleTrees.add(it) }
				getVisibleTrees(row.reversed()).forEach { visibleTrees.add(it) }
			}

		return visibleTrees.size
	}

	override fun analyse2(lines: List<String>): Any {
		val trees = extractTreee(lines)
		val field = Plane(0, trees)

		val rowNumber = field.height
		val colNumber = field.width

		return (1 until rowNumber - 1)
			.flatMap { y ->
				(1 until colNumber - 1)
					.map { x ->
						countVisible(field.getRow(y), x) * countVisible(field.getCol(x), y)
					}
			}.max()
	}

	private fun countVisible(row: List<Cell<Int>>, idx: Int): Int {
		val height = row[idx].content

		val leftpart = row.take(idx).reversed()
		val rightpart = row.drop(idx + 1)

		return min(leftpart.takeWhile { it.content < height }.size + 1, leftpart.size) *
			min(rightpart.takeWhile { it.content < height }.size + 1, rightpart.size)
	}
}
