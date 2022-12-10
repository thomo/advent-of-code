import java.io.File

fun main(args: Array<String>) {
    val input = readFileAsLinesUsingUseLines("input.txt")
    print(searchSum(input.first(), input.drop(1), 0))
}

fun searchSum(first: Int, tail: List<Int>, summand: Int): Pair<Int, Int>? {
    for (i in tail) {
        if (first + i + summand == 2020) {
            return Pair(first, i)
        }
    }
    if (tail.size > 1) {
        return searchSum(tail.first(), tail.drop(1), summand)
    } else return null
}

fun readFileAsLinesUsingUseLines(fileName: String): List<Int>
        = File(fileName).useLines { it.toList().map{ s -> s.toInt() } }