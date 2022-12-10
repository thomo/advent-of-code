package aoc2021.days

import aoc.days.DayXX

class Day24 : DayXX {
	data class Register(val w: Int, val x: Int, val y: Int, val z: Int) {
		fun setW(n: Int) = Register(n, x, y, z)
		fun setX(n: Int) = Register(w, n, y, z)
		fun setY(n: Int) = Register(w, x, n, z)
		fun setZ(n: Int) = Register(w, x, y, n)
	}

	data class Program(val instructions: List<Instruction>) {
		fun input(i: Int): List<Instruction> {
			return listOf(Instruction(instructions[0].cmd, instructions[0].op1, i.toString())) + instructions.drop(1)
		}

	}

	data class Instruction(val cmd: Cmd, val op1: Reg, val op2: String) {
		fun eval(reg: Day24.Register) =
			when (cmd) {
				Cmd.add -> add(reg)
				Cmd.div -> div(reg)
				Cmd.eql -> eql(reg)
				Cmd.inp -> inp(reg)
				Cmd.mod -> mod(reg)
				Cmd.mul -> mul(reg)
			}

		private fun add(reg: Day24.Register) = when (op1) {
			Reg.w -> reg.setW(reg.w + value(op2, reg))
			Reg.x -> reg.setX(reg.x + value(op2, reg))
			Reg.y -> reg.setY(reg.y + value(op2, reg))
			Reg.z -> reg.setZ(reg.z + value(op2, reg))
		}

		private fun div(reg: Day24.Register) = when (op1) {
			Reg.w -> reg.setW(reg.w / value(op2, reg))
			Reg.x -> reg.setX(reg.x / value(op2, reg))
			Reg.y -> reg.setY(reg.y / value(op2, reg))
			Reg.z -> reg.setZ(reg.z / value(op2, reg))
		}

		private fun eql(reg: Day24.Register) = when (op1) {
			Reg.w -> reg.setW(if (reg.w == value(op2, reg)) 1 else 0)
			Reg.x -> reg.setX(if (reg.x == value(op2, reg)) 1 else 0)
			Reg.y -> reg.setY(if (reg.y == value(op2, reg)) 1 else 0)
			Reg.z -> reg.setZ(if (reg.z == value(op2, reg)) 1 else 0)
		}

		private fun inp(reg: Day24.Register) = when (op1) {
			Reg.w -> reg.setW(value(op2, reg))
			Reg.x -> reg.setX(value(op2, reg))
			Reg.y -> reg.setY(value(op2, reg))
			Reg.z -> reg.setZ(value(op2, reg))
		}

		private fun mod(reg: Day24.Register) = when (op1) {
			Reg.w -> reg.setW(reg.w % value(op2, reg))
			Reg.x -> reg.setX(reg.x % value(op2, reg))
			Reg.y -> reg.setY(reg.y % value(op2, reg))
			Reg.z -> reg.setZ(reg.z % value(op2, reg))
		}

		private fun value(op2: String, reg: Day24.Register) =
			if (op2.matches(Regex("[wxyz]"))) {
				when (Reg.valueOf(op2)) {
					Reg.w -> reg.w
					Reg.x -> reg.x
					Reg.y -> reg.y
					Reg.z -> reg.z
				}
			} else op2.toInt()

		private fun mul(reg: Day24.Register) = when (op1) {
			Reg.w -> reg.setW(reg.w * value(op2, reg))
			Reg.x -> reg.setX(reg.x * value(op2, reg))
			Reg.y -> reg.setY(reg.y * value(op2, reg))
			Reg.z -> reg.setZ(reg.z * value(op2, reg))
		}
	}

	enum class Reg {
		w, x, y, z
	}

	enum class Cmd {
		add,
		div,
		eql,
		inp,
		mod,
		mul
	}

	fun parse(lines: List<String>): List<Program> {
		var cnt = 0
		return lines
			.map {
				parseInstruction(it)

			}
			.map {
				if (it.cmd == Cmd.inp) cnt += 1
				Pair(cnt, it)
			}
			.groupBy { it.first }
			.values
			.map { codelines -> codelines.map { cl -> cl.second } }
			.map { it -> Program(it) }
	}

	private fun parseInstruction(it: String): Instruction {
		val parts = it.split(" ")
		return Instruction(Cmd.valueOf(parts[0]), Reg.valueOf(parts[1]), if (parts.size == 3) parts[2] else "")
	}

	fun eval(prog: Day24.Program, reg: Register, i: Int) = prog.input(i).fold(reg) { r, instr -> instr.eval(r) }

	override fun analyse(lines: List<String>): Any {
		val progs = parse(lines)
		for (i in 1..9) {
			val result = eval(progs[0], Register(0, 0, 0, 0), i)
			println(result)
		}
		TODO("Not yet implemented")
	}

	override fun analyse2(lines: List<String>): Any {
		TODO("Not yet implemented")
	}

}
