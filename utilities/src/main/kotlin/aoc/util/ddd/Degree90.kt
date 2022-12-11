package aoc.util.ddd

enum class Degree90 {
	D0, D90, D180, D270;

	fun cos() = listOf(1, 0, -1, 0)[ordinal]
	fun sin() = listOf(0, 1, 0, -1)[ordinal]
}
