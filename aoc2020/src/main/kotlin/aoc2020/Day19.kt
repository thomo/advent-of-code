package aoc2020

class Day19 {
	class Rule(val left: String, rule: String) {
		private val right: List<List<String>>

		init {
			right = if (rule.contains('"')) {
				listOf(listOf(rule.trim()[1].toString()))
			} else {
				rule.split('|').map { s -> s.trim().split(' ') }
			}
		}

		fun match(s: String) = left == s
		fun result(): List<List<String>> = right

		override fun toString(): String = "$left -> ${right.toString()}"
	}

	fun run(lines: List<String>) {

		var (rules, inputs) = readInput(lines)

		var words = generateLang("0", rules)
		val notFound = mutableListOf<String>()
		var cnt = 0
		for (input in inputs) {
			// println("> $input")
			if (!words.contains(input))
				notFound.add(input)
			else
				cnt += 1
		}

		println("found: $cnt - not found: ${notFound.size}")

		var words42 = generateLang("42", rules)
		var words31 = generateLang("31", rules)

		println("31: ${words31.size} - $words31")
		println("42: ${words42.size} - $words42")

		for (nf in notFound) {
			if (check8_11(nf, words42, words31)) {
				println(" found rule 11 word")
				cnt += 1
			}
		}
		println("finally found: $cnt")
		// too low: 253, 377, 383
	}

	private fun check8_11(input: String, words42: List<String>, words31: List<String>): Boolean {
		// mindest 42 42 31 muss enthalten sein
		val (cnt, remainInput) = checkRule11(input, words42, words31)
		if (cnt == 0) return false
		return checkRule8(remainInput, words42) > 0
	}

	private fun checkRule8(input: String, words42: List<String>): Int {
		val prefixes = words42.filter { input.startsWith(it) }
		if (prefixes.isEmpty()) {
			println(" 8 $input -> xxx")
			return 0
		} else if (prefixes.size > 1) {
			println("multiple match 8")
		}
		var newInput = input.substring(prefixes[0].length)
		println(" 8 $input ->  (${prefixes[0]}) $newInput ")
		assert(input == prefixes[0] + newInput)
		return if (newInput.isEmpty()) 1 else 1 * checkRule8(newInput, words42)
	}

	private fun checkRule11(input: String, words42: List<String>, words31: List<String>): Pair<Int, String> {
		val prefixes = words42.filter { input.startsWith(it) }
		if (prefixes.isEmpty()) {
			println("11 42 $input -> xxx")
			return Pair(0, input)
		} else if (prefixes.size > 1) {
			println("multiple match 11 left")
		}
		var newInput = input.substring(prefixes[0].length)
		val postfixes = words31.filter { newInput.endsWith(it) }
		if (postfixes.isEmpty()) {
			println("11 31 $input -> xxx")
			return Pair(0, input)
		} else if (postfixes.size > 1) {
			println("multiple match 11 right")
		}
		newInput = newInput.take(newInput.length - postfixes[0].length)
		println("11 $input ->  (${prefixes[0]}) $newInput (${postfixes[0]}) ")
		assert(input == prefixes[0] + newInput + postfixes[0])
		return if (newInput.isEmpty()) Pair(1, "") else Pair(1, checkRule11(newInput, words42, words31).second)
	}

	private fun isRule11(input: String, words42: List<String>, words31: List<String>): Boolean {
		val prefixes = words42.filter { input.startsWith(it) }
		if (prefixes.isEmpty()) {
			println("11 $input -> xxx")
			return false
		} else if (prefixes.size > 1) {
			println("multiple match 11 left")
		}
		var newInput = input.substring(prefixes[0].length)
		val postfixes = words31.filter { newInput.endsWith(it) }
		if (postfixes.isEmpty()) {
			println("11 $input -> xxx")
			return false
		} else if (postfixes.size > 1) {
			println("multiple match 11 right")
		}
		newInput = newInput.take(newInput.length - postfixes[0].length)
		println("11 $input ->  (${prefixes[0]}) $newInput (${postfixes[0]}) ")
		assert(input == prefixes[0] + newInput + postfixes[0])
		return if (newInput.isEmpty()) true else isRule11(newInput, words42, words31)
	}

	private fun isRule8(input: String, words42: List<String>): Boolean {
		val prefixes = words42.filter { input.startsWith(it) }
		if (prefixes.isEmpty()) {
			println(" 8 $input -> xxx")
			return false
		} else if (prefixes.size > 1) {
			println("multiple match 8")
		}
		var newInput = input.substring(prefixes[0].length)
		println(" 8 $input ->  (${prefixes[0]}) $newInput ")
		assert(input == prefixes[0] + newInput)
		return if (newInput.isEmpty()) true else isRule8(newInput, words42)
	}

	private fun generateLang(startRule: String, rules: List<Rule>): List<String> {
		return rules.first { it.left == startRule }.result()
			.flatMap { start -> replace(start, rules).map { it.joinToString("") } }
	}

	private fun replace(start: List<String>, rules: List<Rule>): List<List<String>> {
		val i = start.indexOfFirst { it != "a" && it != "b" }
		return if (i >= 0) {
			val replacements = rules.first { it.match(start[i]) }.result()
			val prefix = start.take(i)
			val postfix = start.drop(i + 1)
			replacements.flatMap { r -> replace(listOf(prefix, r, postfix).flatten(), rules) }
		} else listOf(start)
	}

	private fun readInput(lines: List<String>): Pair<List<Rule>, List<String>> {
		val it = lines.iterator()
		val rules = extractRules(it)
		val inputs = readPatterns(it)
		return Pair(rules, inputs)
	}

	fun extractRules(it: Iterator<String>): List<Rule> {
		val rules = mutableListOf<Rule>()
		var line = it.next()
		while (line.isNotEmpty()) {
			var parts = line.split(':')

			rules.add(Rule(parts[0], parts[1]))

			line = it.next()
		}
		return rules
	}

	fun readPatterns(it: Iterator<String>): List<String> {
		val inputs = mutableListOf<String>()
		while (it.hasNext()) {
			var line = it.next().trim()
			inputs.add(line)
		}
		return inputs
	}

}
