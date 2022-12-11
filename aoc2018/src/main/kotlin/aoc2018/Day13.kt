package aoc2018

import aoc.days.DayXX
import aoc.util.dd.Coord

private const val TURN_LEFT = "v>^<v"
private const val TURN_RIGHT = "v<^>v"

data class Cart(val id: Int, val x: Int, val y: Int, val c: Char, val turnIndex: Int) {
	fun crashWith(another: Cart) = id != another.id && x == another.x && y == another.y
	fun coord() = Coord(x, y)

	fun move() = when (c) {
		'<' -> Cart(id, coord().left().x, y, c, turnIndex)
		'>' -> Cart(id, coord().right().x, y, c, turnIndex)
		'v' -> Cart(id, x, coord().down().y, c, turnIndex)
		else -> Cart(id, x, coord().up().y, c, turnIndex)
	}

	fun turn(target: Char): Cart {
		var ti = turnIndex
		val dir = when ("$target $c") {
			"- <", "- >", "| v", "| ^" -> c

			"/ <", "\\ >" -> 'v'
			"/ >", "\\ <" -> '^'
			"/ ^", "\\ v" -> '>'
			"/ v", "\\ ^" -> '<'

			else /* '+' */ -> {
				ti = (turnIndex + 1) % 3
				when (turnIndex) {
					0 -> TURN_LEFT[TURN_LEFT.indexOf(c) + 1]
					1 -> c
					else -> TURN_RIGHT[TURN_RIGHT.indexOf(c) + 1]
				}
			}
		}

		return Cart(id, x, y, dir, ti)
	}
}

class Day13 : DayXX {
	private val INITIAL_TURN_INDEX: Int = 0;

	private fun parseInput(lines: List<String>): Pair<Map<Coord, Char>, List<Cart>> {
		val carts = mutableListOf<Cart>()
		val maze = mutableMapOf<Coord, Char>()
		var y = 0;
		var idx = 0;
		lines.forEach { line ->
			line.asSequence().forEachIndexed { x, c ->
				if (isCart(c)) {
					maze[Coord(x, y)] = cartReplacement(c)
					carts.add(Cart(idx, x, y, c, INITIAL_TURN_INDEX))
					idx += 1
				} else
					maze[Coord(x, y)] = c
			}
			y += 1
		}

		return Pair(maze, carts)
	}

	private fun cartReplacement(c: Char) = if (c == '<' || c == '>') '-' else '|'

	private fun isCart(c: Char) = (c == '<' || c == '>' || c == 'v' || c == '^')

	private fun findCrashCart(cart: Cart, carts: List<Cart>) = carts.find { c -> c.crashWith(cart) }

	private fun findCrashCoord(cart: Cart, carts: List<Cart>) = findCrashCart(cart, carts)?.coord()

	private fun tick(cart: Cart, maze: Map<Coord, Char>): Cart {
		val target = when (cart.c) {
			'<' -> maze[cart.coord().left()]
			'>' -> maze[cart.coord().right()]
			'v' -> maze[cart.coord().down()]
			else -> maze[cart.coord().up()]
		}

		if (target == null) {
			println("Error: $cart")
		}
		return cart.move().turn(target!!)
	}

	private fun sortCarts(carts: List<Cart>) = carts.sortedWith(compareBy({ it.y }, { it.x }))

	private fun cartsOnPos(carts: List<Cart>, x: Int, y: Int) = carts.filter { cart -> cart.x == x && cart.y == y }

	private fun printMaze(maxX: Int, maxY: Int, maze: Map<Coord, Char>, carts: List<Cart>) {
		(0..maxY).forEach { y ->
			(0..maxX).forEach { x ->
				var c = maze[Coord(x, y)] ?: ' '
				c = if (c != ' ') {
					val cs = cartsOnPos(carts, x, y)
					when (cs.size) {
						0 -> c
						1 -> cs[0].c
						else -> 'X'
					}
				} else c
				print(c)
			}
			println()
		}
		println()
		println("#carts: ${carts.size}")
		println()
	}

	// 26,92
	override fun analyse(lines: List<String>): Any {
		var (maze, carts) = parseInput(lines)

		println("found: ${carts.size} carts")

		val maxX = maze.keys.maxOf { p -> p.x }
		val maxY = maze.keys.maxOf { p -> p.y }

		printMaze(maxX, maxY, maze, carts)

		var ticker: Int = 0;
		var crash: Coord? = null
		do {
			val sorted = sortCarts(carts)
			val nextCarts = mutableListOf<Cart>()

			sorted.forEachIndexed { index, cart ->
				if (crash == null) {
					val nextCart = tick(cart, maze)
					nextCarts.add(nextCart)
					crash = findCrashCoord(nextCart, sorted.drop(index + 1))
							?: findCrashCoord(nextCart, nextCarts)
				}
			}
			ticker += 1
			carts = nextCarts
//      println(ticker)
//      printMaze(maxX, maxY, maze, carts)
		} while (crash == null)

		return crash!!
	}

	// 86,18
	override fun analyse2(lines: List<String>): Any {
		var (maze, carts) = parseInput(lines)

		println("found: ${carts.size} carts")

		val maxX = maze.keys.maxOf { p -> p.x }
		val maxY = maze.keys.maxOf { p -> p.y }

		// printMaze(maxX, maxY, maze, carts)

		var lastCoord: Coord? = null
		var ticker: Int = 0;
		do {
			val sorted = sortCarts(carts)
			val nextCarts = mutableListOf<Cart>()
			val deleted = mutableListOf<Int>()


			sorted.forEachIndexed { index, cart ->
				if (!deleted.contains(cart.id)) {
					val nextCart = tick(cart, maze)
					val crashCart = findCrashCart(nextCart, sorted.drop(index + 1))
							?: findCrashCart(nextCart, nextCarts)
					if (crashCart != null) {
						deleted.add(crashCart.id)
						nextCarts.remove(crashCart)
					} else {
						nextCarts.add(nextCart)
					}
				}
				val remaining = sorted.drop(index + 1).filterNot { deleted.contains(it.id) } + nextCarts
				if (remaining.size == 1) lastCoord = remaining[0].coord()
			}
			ticker += 1
			carts = nextCarts
//      println(ticker)
//      printMaze(maxX, maxY, maze, carts)
		} while (lastCoord == null)

		return lastCoord!!
	}

}

