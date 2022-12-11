package aoc2021.days

import aoc.days.DayXX
import utils.FieldPos

class Day20 : DayXX {
	fun parseImage(lines: List<String>) =
		lines.flatMapIndexed { y: Int, line: String ->
			line.mapIndexedNotNull { x, c ->
				if (c == '#') FieldPos(
					x,
					y
				) else null
			}
		}

	fun printImage(inputImage: List<FieldPos>, default: Boolean) {
		(-5..15).forEach { y ->
			(-5..15).forEach { x ->
				print(if (inputImage.contains(FieldPos(x, y)) == default) '#' else ' ')
			}
			println()
		}
		println("-".repeat(80))
	}

	fun transform(inputImage: List<FieldPos>, algo: String, cmpChar: Char, invert: Boolean): List<FieldPos> {
		val candidates = inputImage.flatMap { fp -> getNeighbors(fp) }.toSet()
		val map = inputImage.toSet()
		return candidates.mapNotNull { fp ->
			if (algo[algoIndexCalc(
					fp,
					map, invert
				)] == cmpChar
			) fp else null
		}
	}

	fun algoIndexCalc(fp: FieldPos, inputImage: Set<FieldPos>, invert: Boolean) =
		getNeighbors(fp).map { nfp -> if (inputImage.contains(nfp) xor invert) '1' else '0' }
			.joinToString("").toInt(2)

	fun getNeighbors(fp: FieldPos): List<FieldPos> =
		(-1..1).flatMap { i -> (-1..1).map { j -> FieldPos(fp.x + j, fp.y + i) } }

	override fun analyse(lines: List<String>): Any {
		val algo = lines[0]

		var inputImage = parseImage(lines.drop(2))

		val firstIsDot = (algo[0] == '.')

		inputImage = transform(inputImage, algo, if (firstIsDot) '#' else '.', false)
		inputImage = transform(inputImage, algo, '#', !firstIsDot)
		return inputImage.size
		// ok: 5225
	}

	override fun analyse2(lines: List<String>): Any {
		val algo = lines[0]

		var inputImage = parseImage(lines.drop(2))

		val firstIsDot = (algo[0] == '.')

		for (i in 0 until 25) {
			inputImage = transform(inputImage, algo, if (firstIsDot) '#' else '.', false)
			inputImage = transform(inputImage, algo, '#', !firstIsDot)
		}
		return inputImage.size
		// ok: 18131
	}

}
