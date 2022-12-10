package aoc.util.dd

import kotlin.math.abs
import kotlin.math.max

/**
 * (0,0) is upper left
 * (x,y)
 *   (0,0) (1,0) (2,0)
 *   (0,1) (1,1) (2,1)
 *
 */
data class Coord(val x: Int, val y: Int) {

	companion object {
		fun sortByReadingOrder(coords: List<Coord>) = coords.sortedWith(compareBy { c: Coord -> c.y }.thenBy { it.x })
	}

	fun straightNeighbors() = listOf(Coord(x + 1, y), Coord(x - 1, y), Coord(x, y + 1), Coord(x, y - 1))

	fun neighbors() =
		straightNeighbors() + listOf(Coord(x + 1, y + 1), Coord(x + 1, y - 1), Coord(x - 1, y + 1), Coord(x - 1, y - 1))

	fun left(i: Int = 1) = Coord(x - i, y)
	fun toWest(i: Int = 1) = left(i)
	fun right(i: Int = 1) = Coord(x + i, y)
	fun toEast(i: Int = 1) = right(i)
	fun up(i: Int = 1) = Coord(x, y - i)
	fun toNorth(i: Int = 1) = up(i)
	fun down(i: Int = 1) = Coord(x, y + i)
	fun toSouth(i: Int = 1) = down(i)

	fun move(dir: Char) = when (dir.lowercaseChar()) {
		'u', 'n' -> up()
		'd', 's' -> down()
		'e', 'r' -> right()
		'w', 'l' -> left()
		else -> throw IllegalArgumentException("Unsupported move direction '$dir'")
	}

	fun isAbove(pos: Coord) = y < pos.y
	fun isBelow(pos: Coord) = y > pos.y
	fun isLeftOf(pos: Coord) = x < pos.x

	fun isNeighbor(pos: Coord) = max(abs(x - pos.x), abs(y - pos.y)) == 1
}
