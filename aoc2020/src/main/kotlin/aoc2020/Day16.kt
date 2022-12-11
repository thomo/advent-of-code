package aoc2020

class Day16 {
	class Rule(line: String) {
		// price: 39-683 or 693-956
		val r = """(.*): (\d+)-(\d+) or (\d+)-(\d+)""".toRegex().matchEntire(line)!!.groupValues
		val name = r[1]
		val ab = Pair(r[2].toInt(), r[3].toInt())
		val cd = Pair(r[4].toInt(), r[5].toInt())

		var index = -1

		fun match(x: Int): Boolean = (x in ab.first..ab.second) || (x in cd.first..cd.second)

		override fun toString(): String {
			return "$name: $ab $cd $index"
		}

	}

	fun run(lines: List<String>) {
		val rules = lines.takeWhile { s -> s != "your ticket:" }.filter { s -> s.isNotBlank() }.map { s -> Rule(s) }
		val myTicket: List<Int> =
			lines.dropWhile { s -> s != "your ticket:" }.drop(1).first().split(",").map { it.toInt() }
		val nearBy =
			lines.dropWhile { s -> s != "nearby tickets:" }.drop(1).map { s -> s.split(",").map { it.toInt() } }

		// println(nearBy.flatMap { t -> t.filterNot { f -> rules.any { rule -> rule.match(f) } } }.sum())
		var validTickets = nearBy.filter { t -> t.all { f -> rules.any { rule -> rule.match(f) } } }.toMutableList()
		validTickets.add(myTicket)

		val matchingRules = mutableListOf<List<Rule>>()
		for (i in 0..rules.size - 1) {
			matchingRules.add(i, rules.filter { rule -> validTickets.all { vt -> rule.match(vt[i]) } })
		}

		while (findSingleOpenRule(matchingRules)) {
			print(".")
		}
		println()
		println(rules)

		checkRules(validTickets, rules)

		val departureValues = rules.filter { r -> r.name.startsWith("departure") }.map { r -> myTicket[r.index] }

		println(departureValues)
		println(departureValues.map { i -> i.toLong() }.fold(1L, { total: Long, item: Long -> total * item }))
		println(1307550234719)
	}

	private fun checkRules(validTickets: MutableList<List<Int>>, rules: List<Rule>) {
		rules.forEach { rule ->
			if (!validTickets.all { t -> rule.match(t[rule.index]) }) println("Fail: $rule")
		}
	}

	private fun findSingleOpenRule(matchingRules: MutableList<List<Rule>>): Boolean {
		matchingRules.forEachIndexed { fieldIndex, mrs ->
			var ors = mrs.filter { r -> r.index < 0 }
			if (ors.size == 1) {
				ors[0].index = fieldIndex
				print(" $fieldIndex ")
				return true
			}
		}
		return false
	}

}
