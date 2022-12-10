package aoc.util.ddd

data class Cell3d<T>(val pos: Coord3d, val content: T) {
	fun change(newContent: T) = Cell3d(pos, newContent)

	fun <R> morph(newContent: R) = Cell3d(pos, newContent)
}
