package aoc2018

import aoc.days.DayXX
import aoc.util.dd.Cell
import aoc.util.dd.Coord
import java.io.File

data class Creature(val id: Int, val type: Char, val x: Int, val y: Int, val hp: Int) {
	fun attack(attackPower: Int) = Creature(id, type, x, y, if ((hp - attackPower) < 0) 0 else (hp - attackPower))
	fun moveTo(next: Coord) = Creature(id, type, next.x, next.y, hp)
	fun coord() = Coord(x, y)
	fun cell() = Cell(coord(), type)
}

class Day15 : DayXX {
	companion object {
		const val INITIAL_HITPOINTS = 200
		const val DEFAULT_ATTACK = 3
	}

	private lateinit var cavern: List<String>
	private lateinit var cells: List<Coord>

	private var cId: Int = 0

	private fun getCavern(lines: List<String>) = lines
			.map { it.replace('G', '.') }
			.map { it.replace('E', '.') }
			.map { it.replace('#', ' ') }

	private fun extractCells(lines: List<String>, c: Char) =
			lines.flatMapIndexed { y: Int, line: String -> line.mapIndexed { x, lc -> Cell(Coord(x, y), lc) } }.filter { it.content == c }

	private fun printMapCreatures(creatures: List<Creature>) {
		printMap(creatures.map { it.cell() })
	}

	private fun printMap(markedCells: List<Cell<Char>>) {
		cavern.forEachIndexed { y, line ->
			val inLine = markedCells.filter { it.pos.y == y }

			println(line
					.mapIndexed { x, c -> inLine.find { it.pos.x == x }?.content ?: c }
					.joinToString(""))
		}
		println()
	}

	private fun sortCreaturesByReadingOrder(creatures: List<Creature>) = creatures.sortedWith(compareBy { c: Creature -> c.y }.thenBy { it.x })

	private fun <T> sortPos(items: List<Cell<T>>) = items.sortedWith(compareBy { c: Cell<T> -> c.pos.y }.thenBy { it.pos.x })


	private fun creatureId(): Int {
		cId += 1;
		return cId;
	}

	override fun analyse(lines: List<String>): Any {
		cavern = getCavern(lines)
		val elves = extractCells(lines, 'E').map { c -> Creature(creatureId(), c.content, c.pos.x, c.pos.y, INITIAL_HITPOINTS) }
		val goblins = extractCells(lines, 'G').map { c -> Creature(creatureId(), c.content, c.pos.x, c.pos.y, INITIAL_HITPOINTS) }
		var creatures = elves + goblins
		cells = extractCells(lines, '.').map { it.pos } + creatures.map { it.coord() }

		printMapCreatures(creatures)

		var round = 0
		var incompleteRound = false;

		while (thereAreEnemies(creatures)) {
			val sortedIds = sortCreaturesByReadingOrder(creatures).map { it.id }
			sortedIds.forEach { id ->
				val cr = getById(creatures, id)
				if (cr != null) {
					if (creatures.none { it.type != cr.type }) {
						incompleteRound = true;
					} else {
						val target = getAttackTarget(cr, creatures)
						if (target != null) {
							creatures = replace(creatures, target.attack(DEFAULT_ATTACK))
						} else {
							//
							var movedCr = selectTargetAndMove(cr, creatures)
							creatures = replace(creatures, movedCr)

							val target = getAttackTarget(movedCr, creatures)
							if (target != null) {
								creatures = replace(creatures, target.attack(DEFAULT_ATTACK))
							}
						}
					}
				}
			}
			printMapCreatures(creatures)
			println("----------------------------------------------- " + round)
			round += if (incompleteRound) 0 else 1
		}

		return creatures.map { it.hp }.sum() * round
	}

	private fun selectTargetAndMove(cr: Creature, creatures: List<Creature>): Creature {
		// select all possible target pos
		var targets = creatures
				.filter { it.type != cr.type }
				.map { it.coord() }
				.toSet()

		if (targets.isEmpty()) return cr

		// find the nearest reachable target
		val target = findNearestReachableTarget(cr.coord(), targets, creatures)

		if (target == null) return cr

		// select possible next step
		val floodedMaze = flood(target, cr.coord(), creatures).map { it.pos }
		val candidates = cr.coord().straightNeighbors()
		val next = Coord.sortByReadingOrder(candidates.filter { floodedMaze.contains(it) }).first()

		// move to reading order pos
		return cr.moveTo(next)
	}

	private fun flood(src: Coord, dst: Coord, creatures: List<Creature>): Set<Cell<Int>> {
		val validCoords = cells - creatures.map { it.coord() }.toSet()
		val staticCells = setOf(Cell(src, 'S'), Cell(dst, 'T')) + creatures.map { it.cell() }

		var edgeCoords = src.straightNeighbors().filter { validCoords.contains(it) }
		var step = 0
		var visited = edgeCoords
		var flooded = edgeCoords.map { Cell(it, step) }.toSet()
		var dstNeighbors = dst.straightNeighbors().filter { validCoords.contains(it) }

		while (edgeCoords.intersect(dstNeighbors).size == 0) {
			// printMap(edgeCoords.map { Cell(it, 'X') } + flooded.map { Cell(it.pos, 'x') } + staticCells)
			edgeCoords = edgeCoords
					.flatMap { it.straightNeighbors() }
					.filterNot { visited.contains(it) }
					.filter { validCoords.contains(it) }
					.toSet()
					.toList()
			step += 1
			visited += edgeCoords
			flooded += edgeCoords.map { Cell(it, step) }
		}
		return flooded
	}

	private fun findNearestReachableTarget(src: Coord, targets: Set<Coord>, creatures: List<Creature>): Coord? {

		val srcAndTargetCells = targets.map { Cell(it, 'T') } + Cell(src, 'S') + creatures.map { it.cell() }
		val validCoords = cells - creatures.map { it.coord() }.filterNot { it == src }.toSet() + targets

		var step = 0;
		var flooded = setOf(Cell(src, step))
		var floodedCoords = setOf(src)
		var frontCoords = setOf(src) // coords am Rand der Flut

		while (floodedCoords.intersect(targets).size == 0 && frontCoords.isNotEmpty()) {
			// printMap(srcAndTargetCells + floodedCoords.map { Cell(it, '*') }.map { if (frontCoords.contains(it.pos)) Cell(it.pos, 'X') else it })
			step += 1
			var nextCoords = frontCoords
					.flatMap { it.straightNeighbors() }
					.filterNot { floodedCoords.contains(it) }
					.filter { validCoords.contains(it) }
					.toSet()
			floodedCoords += nextCoords
			flooded += nextCoords.map { Cell<Int>(it, step) }
			frontCoords = nextCoords
		}

		return Coord.sortByReadingOrder(floodedCoords.intersect(targets).toList()).firstOrNull()
	}

	private fun getById(creatures: List<Creature>, id: Int) = creatures.filter { c -> c.hp > 0 }.find { c -> c.id == id }

	/**
	 * Returns a new list of creatures, where the creature with nc.id is replaced by nc. Also it keeps only these creatures with hitpoints > 0.
	 *
	 */
	private fun replace(creatures: List<Creature>, nc: Creature) = creatures.map { if (it.id == nc.id) nc else it }.filter { it.hp > 0 }

	private fun getAttackTarget(cr: Creature, creatures: List<Creature>) =
			sortCreaturesByReadingOrder(creatures
					.filter { it.type != cr.type }
					.filter { target ->
						(target.x == cr.x + 1 && target.y == cr.y) ||
								(target.x == cr.x - 1 && target.y == cr.y) ||
								(target.x == cr.x && target.y == cr.y + 1) ||
								(target.x == cr.x && target.y == cr.y - 1)
					}).minByOrNull { creature -> creature.hp }


	private fun thereAreEnemies(creatures: List<Creature>) =
			creatures.groupBy { creature -> creature.type }.keys.size == 2


	override fun analyse2(lines: List<String>): Any {
		cavern = getCavern(lines)
		val elves = extractCells(lines, 'E').map { c -> Creature(creatureId(), c.content, c.pos.x, c.pos.y, INITIAL_HITPOINTS) }
		val goblins = extractCells(lines, 'G').map { c -> Creature(creatureId(), c.content, c.pos.x, c.pos.y, INITIAL_HITPOINTS) }

		cells = extractCells(lines, '.').map { it.pos } + (elves + goblins).map { it.coord() }

		printMapCreatures(elves + goblins)

		var elvesPower = DEFAULT_ATTACK - 1

		var creatures: List<Creature>
		var round = 0

		do {
			creatures = elves + goblins
			round = 0
			var incompleteRound = false;
			elvesPower += 1
			while (thereAreEnemies(creatures) && creatures.filter { it.type == 'E' }.filter { it.hp > 0 }.size == elves.size) {
				val sortedIds = sortCreaturesByReadingOrder(creatures).map { it.id }
				sortedIds.forEach { id ->
					val cr = getById(creatures, id)
					if (cr != null) {
						if (creatures.none { it.type != cr.type }) {
							incompleteRound = true;
						} else {
							val target = getAttackTarget(cr, creatures)
							if (target != null) {
								creatures = replace(creatures, target.attack(if (target.type == 'G') elvesPower else DEFAULT_ATTACK))
							} else {
								//
								var movedCr = selectTargetAndMove(cr, creatures)
								creatures = replace(creatures, movedCr)

								val target = getAttackTarget(movedCr, creatures)
								if (target != null) {
									creatures = replace(creatures, target.attack(if (target.type == 'G') elvesPower else DEFAULT_ATTACK))
								}
							}
						}
					}
				}
				printMapCreatures(creatures)
				println("----------------------------------------------- " + round)
				round += if (incompleteRound) 0 else 1
			}
		} while (creatures.filter { it.type == 'E' }.filter { it.hp > 0 }.size != elves.size)

		return creatures.map { it.hp }.sum() * round
	}

}
