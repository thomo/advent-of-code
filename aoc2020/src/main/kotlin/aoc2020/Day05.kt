package aoc2020

import aoc.days.DayXX

class Day05 : DayXX {
	private fun getSeatNumbers(lines: List<String>) =
			lines
					.map { l -> l.replace('B', '1') }
					.map { l -> l.replace('F', '0') }
					.map { l -> l.replace('R', '1') }
					.map { l -> l.replace('L', '0') }
					.map { l -> Integer.parseInt(l, 2) }

	fun existsSeat(seatNumbers: List<Int>, nr: Int): Boolean = seatNumbers.contains(nr)

	// 822
	override fun analyse(lines: List<String>) = getSeatNumbers(lines).max()

	// 705
	override fun analyse2(lines: List<String>): Any {
		val seatNumbers = getSeatNumbers(lines)
		val maxNumber = seatNumbers.max()

		return (2 until maxNumber).find { i -> existsSeat(seatNumbers, i - 1) && !existsSeat(seatNumbers, i) && existsSeat(seatNumbers, i + 1) }!!
	}
}
