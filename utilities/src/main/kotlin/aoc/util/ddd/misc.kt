package aoc.util.ddd

fun <S, T> manhattenDistance(a: Cell3d<S>, b: Cell3d<T>) = manhattenDistance(a.pos, b.pos)

fun manhattenDistance(a: Coord3d, b: Coord3d) = Math.abs(a.x - b.x) + Math.abs(a.y - b.y) + Math.abs(a.z - b.z)