package aoc2021.days

import aoc.days.DayXX
import aoc.util.chunkedByEmptyLines
import aoc.util.ddd.Coord3d

class Day19 : DayXX {

	data class Scanner(val origin: Coord3d, val beacons: List<Coord3d>)

	fun orientations(scanner: List<Coord3d>) =
		scanner
			.flatMap { it.orientations().mapIndexed { idx, b -> Pair(idx, b) } } // list of list of beacon orientations
			.groupBy { p -> p.first } // regroup them
			.values // key (index) is not needed
			.map { plist -> plist.map { p -> p.second } } // convert pair with index to beacon only

	override fun analyse(lines: List<String>): Any {
		val scanners = chunkedByEmptyLines(lines)
			.map { it.drop(1) } // ignore line with scanner number
			.map { Scanner(Coord3d(0, 0, 0), it.map { xyz -> line2coord(xyz) }) }
		TODO("Not yet implemented")
	}

	private fun line2coord(xyz: String) = xyz
		.split(',')
		.map { i -> i.toInt() }
		.run { Coord3d(this[0], this[1], this[2]) }

	override fun analyse2(lines: List<String>): Any {
		TODO("Not yet implemented")
	}

}
