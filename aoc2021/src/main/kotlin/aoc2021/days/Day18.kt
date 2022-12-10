package aoc2021.days

import aoc.days.DayXX

class Day18 : DayXX {

	data class Token(val c: String) {
		fun add(n: Long) = Token((number() + n).toString())
		fun isNumber() = c.matches("""\d+""".toRegex())
		fun number() = c.toLong()
	}

	fun reduce(input: List<Token>): List<Token> {
		val flatInput = replaceNestedPairs(input)
		return splitBigNumbers(flatInput)
	}

	private fun splitBigNumbers(line: List<Token>): List<Token> {
		val idx = line.indexOfFirst { it.isNumber() && it.number() >= 10L }
		return if (idx != -1) {
			val result = line.toMutableList()
			val n = line[idx].number()
			result.add(idx + 1, Token("]"))
			result.add(idx + 1, Token(((n + 1L) / 2L).toString()))
			result.add(idx + 1, Token(","))
			result.add(idx + 1, Token((n / 2L).toString()))
			result[idx] = Token("[")
			return reduce(result)
		} else line
	}

	private fun replaceNestedPairs(input: List<Token>): List<Token> {
		val result = input.toMutableList()
		var iter = input.iterator().withIndex()
		var cntBrackets = 0
		while (iter.hasNext()) {
			val t = iter.next()
			when (t.value.c) {
				"[" -> cntBrackets += 1
				"]" -> cntBrackets -= 1
				else -> {}
			}
			if (cntBrackets == 5) {
				val n1 = input[t.index + 1].number()
				val n2 = input[t.index + 3].number()

				val leftIndex = searchOnLeftSide(input.take(t.index))
				if (leftIndex != -1) result[leftIndex] = input[leftIndex].add(n1)

				val offset = t.index + 4
				val rightIndex = searchOnRightSide(input.drop(offset))
				if (rightIndex != -1) result[offset + rightIndex] = input[offset + rightIndex].add(n2)

				result.removeAt(t.index + 4) // ]
				result.removeAt(t.index + 3) // n2
				result.removeAt(t.index + 2) // ,
				result.removeAt(t.index + 1) // n1
				result[t.index] = Token("0")  //   ]
				return reduce(result)
			}
		}
		return input
	}

	private fun searchOnRightSide(input: List<Token>) = input.indexOfFirst { it.isNumber() }

	private fun searchOnLeftSide(input: List<Token>) = input.indexOfLast { it.isNumber() }

	fun tokenize(line: String): List<Token> {
		val reg = """(\[|]|,|\d+)""".toRegex()
		return reg.findAll(line).toList().map { Token(it.value) }
	}

	fun magnitude(line: String): Long {
		val tokens = tokenize(line).toMutableList()
		val idx = tokens.indexOfLast { it.c == "[" }
		if (idx == -1) return line.toLong()
		val n1 = tokens[idx + 1].number()
		val n2 = tokens[idx + 3].number()
		tokens.removeAt(idx + 4)
		tokens.removeAt(idx + 3)
		tokens.removeAt(idx + 2)
		tokens.removeAt(idx + 1)
		tokens[idx] = Token((3L * n1 + 2L * n2).toString())
		return magnitude(tokens.map { it.c }.joinToString(""))
	}

	fun calc(lines: List<String>): String {
		val init = reduce(tokenize(lines[0])).map { it.c }.joinToString("")
		val result = lines.drop(1).fold(init) { result, line ->
			reduce(tokenize(String.format("[%s,%s]", result, line))).map { it.c }.joinToString("")
		}
		return result
	}

	override fun analyse(lines: List<String>): Any {
		val result = calc(lines)
		return magnitude(result)
	}

	override fun analyse2(lines: List<String>): Any {
		var maxMagnitude = 0L
		val cnt = lines.size
		for (i in 0 until cnt) {
			for (j in 0 until cnt) {
				if (i == j) continue
				val m = magnitude(calc(listOf(lines[i], lines[j])))
				maxMagnitude = Math.max(m, maxMagnitude)
			}
		}
		return maxMagnitude
	}

}
