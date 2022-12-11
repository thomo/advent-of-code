package aoc2018

import aoc.days.DayXX
import aoc.util.dd.Coord

class Day11 : DayXX {
	fun a1(serialNumber: Int, dim: Int, cellDim: Int): Coord {
		val field = powerField(dim, serialNumber)
		return cellPowerField(field, cellDim, dim).first
	}

	private fun powerField(dim: Int, serialNumber: Int): HashMap<Coord, Int> {
		val field = HashMap<Coord, Int>()
		(1..dim).forEach { x ->
			(1..dim).forEach { y ->
				field[Coord(x, y)] = powerLevel(x, y, serialNumber)
			}
		}
		return field
	}

	private fun cellPowerField(field: HashMap<Coord, Int>, cellDim: Int, dim: Int): Pair<Coord, Int> {
		var result = Pair(Coord(1, 1), 0)
		(1..dim - cellDim + 1).forEach { x ->
			(1..dim - cellDim + 1).forEach { y ->
				val p = totalPower(field, cellDim, x, y)
				if (p > result.second) result = Pair(Coord(x, y), p)
			}
		}
		return result
	}

	private fun totalPower(field: HashMap<Coord, Int>, cellDim: Int, x: Int, y: Int) =
			(x..x + (cellDim - 1)).map { xi -> (y..y + (cellDim - 1)).sumOf { yi -> field[Coord(xi, yi)]!! } }.sum()


	fun a2(serialNumber: Int, dim: Int): List<Int> {
		val field = powerField(dim, serialNumber)

		var prevCpf = field
		var m = toResult(field.maxByOrNull { it.value }!!, 1)

		(2..dim).forEach { c ->
			val cpf = cellPowerField2(field, prevCpf, dim, c)
			val cm = cpf.maxByOrNull { it.value }!!
			if (cm.value > m.first.second) m = toResult(cm, c)
			prevCpf = cpf
		}
		return listOf(m.first.first.x, m.first.first.y, m.second)
	}

	private fun toResult(kv: Map.Entry<Coord, Int>, i: Int): Pair<Pair<Coord, Int>, Int> {
		return Pair(Pair(kv.key, kv.value), i)
	}

	private fun cellPowerField2(field: HashMap<Coord, Int>, prevCpf: HashMap<Coord, Int>, dim: Int, cellDim: Int): HashMap<Coord, Int> {
		val f2 = HashMap<Coord, Int>()
		(1..dim - cellDim + 1).forEach { x ->
			(1..dim - cellDim + 1).forEach { y ->
				val delta = edgePower(field, x, y, cellDim)
				f2[Coord(x, y)] = prevCpf[Coord(x, y)]!! + delta
			}
		}
		return f2

	}

	private fun edgePower(field: HashMap<Coord, Int>, x: Int, y: Int, cellDim: Int): Int {
		val xs = (x until x + cellDim).map { xi -> field[Coord(xi, y + cellDim - 1)]!! }.sum()
		val ys = (y until y + cellDim).map { yi -> field[Coord(x + cellDim - 1, yi)]!! }.sum()
		return xs + ys
	}

	fun powerLevel(x: Int, y: Int, serial: Int) = (((x + 10) * y + serial) * (x + 10) % 1000) / 100 - 5

	// 20,51
	override fun analyse(lines: List<String>) = a1(7803, 300, 3)

	// 230,272,17
	override fun analyse2(lines: List<String>) = a2(7803, 300)

}


