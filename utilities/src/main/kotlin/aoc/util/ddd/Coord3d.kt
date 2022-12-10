package aoc.util.ddd

/**
 * (0,0,0) is upper left
 * (x,y,z)
 *   (0,0) (1,0) (2,0)
 *   (0,1) (1,1) (2,1)
 *
 */
data class Coord3d(val x: Int, val y: Int, val z: Int) {
	fun rotateX(degree: Degree90): Coord3d =
		Coord3d(x, y * degree.cos() - z * degree.sin(), y * degree.sin() + z * degree.cos())

	fun rotateY(degree: Degree90): Coord3d =
		Coord3d(x * degree.cos() + z * degree.sin(), y, -x * degree.sin() + z * degree.cos())

	fun rotateZ(degree: Degree90): Coord3d =
		Coord3d(x * degree.cos() - y * degree.sin(), x * degree.sin() + y * degree.cos(), z)

	fun orientations() =
		Degree90.values().map { x -> rotateX(x) }
			.flatMap { xr -> Degree90.values().map { y -> xr.rotateY(y) } }
			.flatMap { xyr -> Degree90.values().map { z -> xyr.rotateZ(z) } }
			.toSet().toList()
}
