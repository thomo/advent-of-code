package aoc2020

import aoc.days.DayXX
import aoc.util.chunkedByEmptyLines

class Day04 : DayXX {
	fun validValues(p: String): Boolean {
		val kvs = p.split(" ").map { kv: String -> kv.split(":") }
		val byr = getValue(kvs, "byr").toInt()
		val iyr = getValue(kvs, "iyr").toInt()
		val eyr = getValue(kvs, "eyr").toInt()
		val hgt = getValue(kvs, "hgt")
		val hcl = getValue(kvs, "hcl")
		val ecl = getValue(kvs, "ecl")
		val pid = getValue(kvs, "pid")

		return byr in 1920..2002 && iyr in 2010..2020 && eyr in 2020..2030
				&& validHgt(hgt) && validHcl(hcl) && validEcl(ecl) && validPid(pid)
	}

	fun validPid(pid: String): Boolean {
		return pid.matches(Regex("""\d{9}"""))
	}

	fun validEcl(ecl: String): Boolean {
		return ecl.matches(Regex("""(amb|blu|brn|gry|grn|hzl|oth)"""))
	}

	fun validHcl(hcl: String): Boolean {
		return hcl.matches(Regex("""#[0-9a-f]{6}"""))
	}

	fun validHgt(hgt: String): Boolean {
		val rex = Regex("""(\d+)(cm|in)""")
		if (!hgt.matches(rex)) return false
		val h = Regex("[0-9]+").findAll(hgt).map(MatchResult::value).first().toInt()
		return if (hgt.contains("in")) h in 59..76 else h in 150..193
	}

	fun getValue(kvs: List<List<String>>, key: String): String {
		return kvs.first { kv -> kv[0].trim().equals(key) }[1]
	}

	fun hasRequiredFields(p: String): Boolean {
		return p.contains(" byr:")
				&& p.contains(" iyr:")
				&& p.contains(" eyr:")
				&& p.contains(" hgt:")
				&& p.contains(" hcl:")
				&& p.contains(" ecl:")
				&& p.contains(" pid:")
	}

	// 182
	override fun analyse(lines: List<String>) =
			chunkedByEmptyLines(lines)
					.map { it.joinToString(" ", prefix = " ") }
					.filter { p -> hasRequiredFields(p) }
					.size

	// 109
	override fun analyse2(lines: List<String>) =
			chunkedByEmptyLines(lines)
					.map { it.joinToString(" ", prefix = " ") }
					.filter { p -> hasRequiredFields(p) }
					.filter { p -> validValues(p) }
					.size

}
