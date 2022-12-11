package aoc2020

class Day17 {

	class Cube(val x: Int, val y: Int, val z: Int, val w: Int) {
		override fun equals(other: Any?): Boolean {
			if (this === other) return true
			if (javaClass != other?.javaClass) return false

			other as Cube

			if (x != other.x) return false
			if (y != other.y) return false
			if (z != other.z) return false
			if (w != other.w) return false

			return true
		}

		override fun hashCode(): Int {
			var result = x
			result = 31 * result + y
			result = 31 * result + z
			result = 31 * result + w
			return result
		}
	}

	var world: MutableList<Cube> = mutableListOf()

	fun run(lines: List<String>) {
		buildWorld(lines)

		for (i in 1..6) {
			cycleWorld()
		}

		println(world.size)
	}

	fun cycleWorld() {
		var coords = calcCoordsToCheck()
		var world2: MutableList<Cube> = mutableListOf()
		for (crd in coords) {
			var cnt = countActiveNeighbors(crd)
			if (activateCube(crd, cnt)) world2.add(crd)
		}
		world = world2
	}

	private fun activateCube(crd: Cube, cnt: Int): Boolean {
		if (cnt == 3) return true
		if (cnt == 2 && crd in world) return true
		return false
	}

	fun countActiveNeighbors(crd: Cube): Int {
		var cnt = 0
		for (x in -1..1) {
			for (y in -1..1) {
				for (z in -1..1) {
					for (w in -1..1) {
						if (world.contains(Cube(crd.x + x, crd.y + y, crd.z + z, crd.w + w))) cnt += 1
					}
				}
			}
		}
		if (world.contains(crd)) cnt -= 1
		return cnt
	}

	fun calcCoordsToCheck(): Set<Cube> {
		var result = mutableSetOf<Cube>()
		for (cube in world) {
			for (x in -1..1) {
				for (y in -1..1) {
					for (z in -1..1) {
						for (w in -1..1) {
							result.add(Cube(cube.x + x, cube.y + y, cube.z + z, cube.w + w))
						}
					}
				}
			}
		}
		return result
	}

	fun buildWorld(lines: List<String>) {
		var i = 0
		for (line in lines) {
			var j = 0
			for (cube in line.toCharArray()) {
				if (cube == '#') world.add(Cube(i, j, 0, 0))
				j += 1
			}
			i += 1
		}
	}

}
