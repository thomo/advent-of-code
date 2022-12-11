package aoc2021.days

import aoc.days.DayXX

class Day23 : DayXX {
	var s1 = mutableListOf<Char>()
	var s2 = mutableListOf<Char>()
	var s3 = mutableListOf<Char>()
	var s4 = mutableListOf<Char>()

	private fun parse(lines: List<String>) {
		s1.add(lines[2][3])
		s1.add(lines[3][3])

		s2.add(lines[2][5])
		s2.add(lines[3][5])

		s3.add(lines[2][7])
		s3.add(lines[3][7])

		s4.add(lines[2][9])
		s4.add(lines[3][9])
	}

	override fun analyse(lines: List<String>): Any {
		parse(lines)
		TODO("Not yet implemented")
	}

	override fun analyse2(lines: List<String>): Any {
		TODO("Not yet implemented")
	}

}
