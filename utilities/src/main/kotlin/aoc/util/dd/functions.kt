package aoc.util.dd

import kotlin.math.abs


fun <T> getConvexHull(cells: Set<Cell<T>>): Set<Cell<T>> {

	val cellList = cells.toList()
	val n = cellList.size
	var hull: Set<Cell<T>> = emptySet()

	// there must be at least 3 cells
	if (n < 3) return hull

	// Find the leftmost point
	var p = cells.indexOf(cells.minByOrNull { c -> c.pos.x }!!)

	// Start from leftmost point, keep moving counterclockwise until reach the start point
	// again. This loop runs O(h) times where h is number of points in result or output.
	val l = p
	var q: Int
	do {
		// Add current point to result
		hull = hull + cellList[p]

		// Search for a point 'q' such that orientation(p, q, x) is counterclockwise
		// for all points 'x'. The idea is to keep track of last visited most counterclock-
		// wise point in q. If any point 'i' is more counterclock-wise than q, then update q.
		q = (p + 1) % n
		for (i in 0 until n) {
			// If i is more counterclockwise than current q, then update q
			if (orientation(cellList[p].pos, cellList[i].pos, cellList[q].pos) == 2) q = i
		}

		for (i in 0 until n) {
			if (orientation(cellList[p].pos, cellList[i].pos, cellList[q].pos) == 0) hull = hull + cellList[i]
		}

		// Now q is the most counterclockwise with respect to p. Set p as q for next iteration,
		// so that q is added to result 'hull'
		p = q
	} while (p != l) // While we don't come to first

	return hull
}

fun orientation(p: Coord, q: Coord, r: Coord): Int {
	val ot = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y)
	if (ot == 0) return 0 // collinear
	return if (ot > 0) 1 else 2 // clock or counterclock wise
}

fun manhattenDistance(a: Coord, b: Coord) = abs(a.x - b.x) + abs(a.y - b.y)