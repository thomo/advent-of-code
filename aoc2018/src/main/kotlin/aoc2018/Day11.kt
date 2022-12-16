package aoc2018

import aoc.days.DayXX
import aoc.util.dd.Coord

class Day11 : DayXX {
	fun powerLevel(x: Int, y: Int, serial: Int) = (((x + 10) * y + serial) * (x + 10) % 1000) / 100 - 5

	private fun powerField(dim: Int, serialNumber: Int): Map<Coord, Int> {
		val field = HashMap<Coord, Int>()
		(1..dim).forEach { x ->
			(1..dim).forEach { y ->
				field[Coord(x, y)] = powerLevel(x, y, serialNumber)
			}
		}
		return field
	}

	/*
			Store in each cell the sum of all cells left and above

			x/y 0 1 2 3 4       x/y  0  1  2  3  4
			  0 1 1 1 1 1         0  1  2  3  4  5
			  1 1 1 1 1 1   =>    1  2  4  6  8 10
			  2 1 1 1 1 1         2  3  6  9 12 15
			  3 1 1 1 1 1         3  4  8 12 16 20
			  4 1 1 1 1 1         4  5 10 15 20 25
	 */
	private fun sumField(dim: Int, field: Map<Coord, Int>): Map<Coord, Int> {
		val sumField = HashMap<Coord, Int>()
		(1..dim).forEach { x ->
			var lineSum = 0
			(1..dim).forEach { y ->
				lineSum += field[Coord(x, y)]!!
				var aboveSum = sumField[Coord(x - 1, y)] ?: 0
				sumField[Coord(x, y)] = lineSum + aboveSum
			}
		}
		return sumField
	}

	private fun totalPower(field: Map<Coord, Int>, cellDim: Int, x: Int, y: Int) =
			(x..x + (cellDim - 1)).map { xi -> (y..y + (cellDim - 1)).sumOf { yi -> field[Coord(xi, yi)]!! } }.sum()

	private fun cellPowerField(field: Map<Coord, Int>, cellDim: Int, dim: Int): Coord {
		var resultCoord = Coord(1, 1)
		var resultValue = 0
		(1..dim - cellDim + 1).forEach { x ->
			(1..dim - cellDim + 1).forEach { y ->
				val p = totalPower(field, cellDim, x, y)
				if (p > resultValue) {
					resultCoord = Coord(x, y)
					resultValue = p
				}
			}
		}
		return resultCoord
	}

	fun solve1(serialNumber: Int, dim: Int, squareDim: Int): Coord {
		val field = powerField(dim, serialNumber)
		val sumfield = sumField(dim, field)
		return cellPowerField(field, squareDim, dim)
	}

	fun edgePower(field: Map<Coord, Int>, x: Int, y: Int, cellDim: Int) =
			((x until x + cellDim).map { xi -> Coord(xi, y + cellDim - 1) } +
					(y until y + cellDim - 1).map { yi -> Coord(x + cellDim - 1, yi) }).sumOf { field[it]!! }

	private fun cellPowerField2(field: Map<Coord, Int>, prevCpf: Map<Coord, Int>, dim: Int, cellDim: Int): Map<Coord, Int> {
		val f2 = HashMap<Coord, Int>()
		(1..dim - cellDim + 1).forEach { x ->
			(1..dim - cellDim + 1).forEach { y ->
				val delta = edgePower(field, x, y, cellDim)
				f2[Coord(x, y)] = prevCpf[Coord(x, y)]!! + delta
			}
		}
		return f2
	}

	fun solve2(serialNumber: Int, dim: Int): List<Int> {
		val field = powerField(dim, serialNumber)

		var prevCpf = field
		var maxCoord = field.maxByOrNull { it.value }!!.key
		var maxValue = field.maxByOrNull { it.value }!!.value
		var maxSquare = 1


		(2..dim).forEach { c ->
			val startTime = System.currentTimeMillis()

			val cpf = cellPowerField2(field, prevCpf, dim, c)

			var delta = System.currentTimeMillis() - startTime
			println("#size: $c, #cells: ${cpf.size} - $delta")

			val cm = cpf.maxByOrNull { it.value }!!

			if (cm.value > maxValue) {
				maxCoord = cm.key
				maxValue = cm.value
				maxSquare = c
			}

			prevCpf = cpf
		}
		return listOf(maxCoord.x, maxCoord.y, maxSquare)
	}

	// 20,51
	override fun analyse(lines: List<String>) = solve1(7803, 300, 3)

	// 230,272,17
	override fun analyse2(lines: List<String>) = solve2(7803, 300)
}


