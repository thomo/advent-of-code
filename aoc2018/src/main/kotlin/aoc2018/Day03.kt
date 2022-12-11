package aoc2018

import aoc.days.DayXX

data class Claim(val id: String, val x: Int, val y: Int, val w: Int, val h: Int)
data class IdPoint(val id: String, val x: Int, val y: Int, var c: Int = 0)

class Day03 : DayXX {

	// 101565
	override fun analyse(lines: List<String>) =
			lines
					.map { line -> parse(line) }
					.flatMap { claim -> sqi(claim) }
					.groupBy { sqi -> Pair(sqi.x, sqi.y) }
					.map { g -> g.value.size }
					.count { n -> n > 1 }


	private fun sqi(claim: Claim): List<IdPoint> {
		val result = mutableListOf<IdPoint>()
		for (dx in claim.x until claim.x + claim.w) {
			for (dy in claim.y until claim.y + claim.h) {
				result.add(IdPoint(claim.id, dx, dy))
			}
		}
		return result
	}

	private fun parse(line: String): Claim {
		val id = line.split('@')[0]
		val n = line.split('@')[1]
				.replace(':', ',')
				.replace('x', ',')
				.split(',')
				.map { p -> p.trim().toInt() }
		return Claim(id, n[0], n[1], n[2], n[3])
	}

	// 656
	override fun analyse2(lines: List<String>) =
			lines
					.map { line -> parse(line) }
					.flatMap { claim -> sqi(claim) } // get all squares of claim
					.groupBy { sqi -> Pair(sqi.x, sqi.y) } // group squares by coords
					.flatMap { me -> me.value.map { p -> p.c = me.value.size; p } } // get all claims in squares with number of other claims in same square
					.groupBy { p -> p.id } // group claims by id
					.map { idPoints -> Pair(idPoints.key, idPoints.value.maxOf { ip -> ip.c }) }// max number of overlapping claims per claim
					.filter { idCnt -> idCnt.second == 1 }
					.map { idCnt -> idCnt.first }

}

