package aoc.util.dd

data class Cell<T>(val pos: Coord, val content: T) {
	fun change(newContent: T) = Cell(pos, newContent)

	fun <R> morph(newContent: R) = Cell(pos, newContent)
}
