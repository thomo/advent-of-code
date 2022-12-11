package aoc2018

import aoc.days.DayXX

fun <T> ArrayDeque<T>.circle(steps: Int): ArrayDeque<T> {
	if (steps == 0) return this
	if (steps < 0) {
		val t = removeFirst()
		add(t)
		return circle(steps + 1)
	}
	val t = removeLast()
	addFirst(t)
	return circle(steps - 1)
}

class Day09 : DayXX {
	
	// 398371
	override fun analyse(lines: List<String>): Any {
		val token = lines[0].split(' ')
		val cntPlayes = token[0].toInt()
		val lastMarble = token[6].toInt()

		return calc(cntPlayes, lastMarble)
	}

	private fun calc(cntPlayes: Int, lastMarble: Int): Long {
		val players = MutableList<Long>(cntPlayes) { _ -> 0L }
		var circle = ArrayDeque<Long>()
		circle.addFirst(0L)

		var marble = 1L
		var index = 0
		var pNr = 1
		while (marble <= lastMarble) {
			if (marble % 23L == 0L) {
				index = calcIndex(index - 7, circle.size)
				circle.circle(7)
				players[pNr] += (marble + circle.removeLast())
				circle.circle(-1)
			} else {
				index = calcIndex(index + 2, circle.size)
				circle.circle(-1)
				circle.add(marble)
			}

			// println("[$pNr] " + circle.joinToString(" "))
			marble += 1
			pNr = (pNr + 1) % cntPlayes
		}

		players.sortDescending()
		return players.first()
	}

	private fun calcIndex(i: Int, circleSize: Int) = (i % circleSize + circleSize) % circleSize

	// 3212830280
	override fun analyse2(lines: List<String>): Any {
		val token = lines[0].split(' ')
		val cntPlayers = token[0].toInt()
		val lastMarble = token[6].toInt()

		return calc(cntPlayers, lastMarble * 100)
	}

}


//result 1: 398371
//analysis1 duration: 84
//---------------------------------------------
//result 2: 3212830280
//analysis2 duration: 30270235 - 8,4h

// After using ArrayDeque
//result 1: 398371
//analysis1 duration: 25
//---------------------------------------------
//result 2: 3212830280
//analysis2 duration: 214 - 0,2 sec

