package aoc2020

import aoc.days.DayXX

private class Cmd(var op: String, val value: Int, var used: Boolean) {
	fun isUsed(): Boolean {
		return used
	}

	fun setUsed() {
		used = true
	}

	fun toggleOp() {
		when (op) {
			"nop" -> op = "jmp"
			"jmp" -> op = "nop"
		}
	}

	fun resetUsed() {
		used = false
	}
}

class Day08 : DayXX {
	private fun resetUsedFlag(cmds: List<Cmd>) {
		for (c in cmds) {
			c.resetUsed()
		}
	}

	private fun changeNextCmd(idx: Int, cmds: List<Cmd>): Int {
		if (idx >= 0) cmds[idx].toggleOp()

		val newIdx = searchNextNopJmp(idx, cmds)

		// println("Change cmd $newIdx")
		cmds[newIdx].toggleOp()

		return newIdx
	}

	private fun searchNextNopJmp(idx: Int, cmds: List<Cmd>): Int {
		var newIdx = idx
		do {
			newIdx += 1
		} while (cmds[newIdx].op != "jmp" && cmds[newIdx].op != "nop")
		return newIdx
	}

	private fun buildCommand(l: String): Cmd {
		val p = l.split(" ")
		return Cmd(p[0], p[1].toInt(), false)
	}

	// 2058
	override fun analyse(lines: List<String>): Any {
		val cmds = lines.map { l -> buildCommand(l) }

		var acc = 0
		var pc = 0

		while (!cmds[pc].isUsed()) {
			cmds[pc].setUsed()
			when (cmds[pc].op) {
				"nop" -> pc += 1
				"acc" -> {
					acc += cmds[pc].value
					pc += 1
				}

				"jmp" -> pc += cmds[pc].value
			}
			if (pc >= cmds.size) {
				break
			}
		}
		return acc
	}

	// 1000
	override fun analyse2(lines: List<String>): Any {
		val cmds = lines.map { l -> buildCommand(l) }

		var finished = false
		var acc: Int
		var idx = -1
		do {
			resetUsedFlag(cmds)
			idx = changeNextCmd(idx, cmds)
			var pc = 0
			acc = 0

			while (!cmds[pc].isUsed()) {
				cmds[pc].setUsed()
				when (cmds[pc].op) {
					"nop" -> pc += 1
					"acc" -> {
						acc += cmds[pc].value
						pc += 1
					}

					"jmp" -> pc += cmds[pc].value
				}
				if (pc >= cmds.size) {
					finished = true
					break
				}
			}
		} while (!finished)
		return acc
	}
}
