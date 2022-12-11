package aoc2021.days

import aoc.days.DayXX

class Day04 : DayXX {

	data class BoardRow(private val row: List<String>) {
		fun hasNumber(number: String) = row.any { v -> v == number }
		fun mark(number: String) =
			if (hasNumber(number)) BoardRow(row.map { v -> if (v == number) "*" else v }) else this

		override fun toString() = row.toString()

		fun isWinner() = row.all { v -> v == "*" }
		fun get(i: Int) = row[i]
		fun sumOfUnmarked() = row.sumOf { v -> if (v == "*") 0 else v.toInt() }
	}

	data class Board(private val board: List<BoardRow>) {

		fun sumOfUnmarked() = board.sumOf { r -> r.sumOfUnmarked() }

		fun play(number: String): Board {
			if (!hasNumber(number)) return this
			return Board(board.map { r -> r.mark(number) })
		}

		fun hasNumber(number: String) = board.any { br -> br.hasNumber(number) }

		override fun toString() = board.toString()

		fun isWinner(): Boolean {
			return board.any { r -> r.isWinner() }.or(isColumnWinner())
		}

		private fun isColumnWinner() = (0..4).any { i -> getColumnAsRow(i).isWinner() }

		private fun getColumnAsRow(i: Int) = BoardRow(board.map { r -> r.get(i) })
	}

	override fun analyse(lines: List<String>): Any {
		val inputSeqIterator = lines[0].split(',').iterator()
		var boards = extractBoards(lines.drop(1))

		var number = ""
		var winner = emptyList<Board>()
		do {
			number = inputSeqIterator.next()
			boards = play(boards, number)
			winner = getWinner(boards)
		} while (winner.isEmpty())
		return winner.first().sumOfUnmarked() * number.toInt()
	}

	private fun getWinner(boards: List<Day04.Board>) = boards.filter { b -> b.isWinner() }

	private fun play(boards: List<Day04.Board>, number: String) = boards.map { b -> b.play(number) }

	fun extractBoards(lines: List<String>): List<Board> {
		val result = mutableListOf<Board>()
		var i = 0
		while (i < lines.size) {
			val boardLines = lines.subList(i + 1, i + 6)
			result.add(createBoard(boardLines))
			i += 6
		}
		return result
	}

	fun createBoard(boardLines: List<String>) = Board(toBoardRows(boardLines))

	private fun toBoardRows(lines: List<String>) = lines.map { l -> BoardRow(l.trim().split(Regex("\\s+"))) }

	override fun analyse2(lines: List<String>): Any {
		val inputSeqIterator = lines[0].split(',').iterator()
		var boards = extractBoards(lines.drop(1))

		var number = ""
		var winner = emptyList<Board>()
		do {
			number = inputSeqIterator.next()
			boards = play(boards, number)
			winner = getWinner(boards)
			boards = if (winner.isNotEmpty()) boards - winner else boards
		} while (boards.isNotEmpty())
		return winner.first().sumOfUnmarked() * number.toInt()
	}

}
