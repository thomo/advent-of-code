package aoc.util.dd

/**
 * A vector has a start point and a velocity direction
 */
data class Vector(val start: Coord, val vx: Int, val vy: Int) {
	fun move(cnt: Int = 1) = Vector(Coord(start.x + cnt * vx, start.y + cnt * vy), vx, vy)
}
