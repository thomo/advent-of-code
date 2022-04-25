package days

import kotlin.math.max

class Day21 : Day00 {

    fun roll(start: Int, dice: Int) =
        (start + adjustDiceValue(dice + 1) + adjustDiceValue(dice + 2) + adjustDiceValue(dice + 3)) % 10

    private fun adjustDiceValue(i: Int) = if (i > 100) i % 100 else i

    fun parse(lines: List<String>) = Pair(extractPos(lines[0]), extractPos(lines[1]))

    private fun extractPos(line: String): Int = line.split(":")[1].trim().toInt()

    private fun points(p: Int) = if (p == 0) 10 else p

    override fun analyse(lines: List<String>): Any {
        var (p1, p2) = parse(lines)
        var s1 = 0
        var s2 = 0
        var dice = 0
        do {
            p1 = roll(p1, dice)
            s1 += points(p1)
            dice += 3
            if (s1 >= 1000) {
                println("-> p1: ${p1}/${s1}, p2: ${p2}/${s2}, dice: ${dice}")
                return (s2 * dice)
            }
            p2 = roll(p2, dice)
            s2 += points(p2)
            dice += 3
            if (s2 >= 1000) {
                println("-> p1: ${p1}/${s1}, p2: ${p2}/${s2}, dice: ${dice}")
                return (s1 * dice)
            }
        } while (true)
    }

    private fun incPosition(p: Int) = (p + 1) % 10

    override fun analyse2(lines: List<String>): Any {
        var (p1, p2) = parse(lines)

        val p1Wins = playQuantumDie(p1, 0, 1).groupBy { it }.mapValues { me -> me.value.size.toLong() }
        val p2Wins = playQuantumDie(p2, 0, 1).groupBy { it }.mapValues { me -> me.value.size.toLong() }
        println(p1Wins)
        println(p2Wins)

        printUniverse(p1Wins, p2Wins)

//        val p1WinsUni = wins(p1Wins, 1)
//        val p1Loose = loose(p1WinsUni, 1)
//        val p2WinsUni = wins(p2Wins, 1)
//        val p2Loose = loose(p2WinsUni, 3)
        return 0L
    }

    private fun printUniverse(p1Wins: Map<Int, Long>, p2Wins: Map<Int, Long>) {
        val m = max(p1Wins.keys.maxOf { it } * 2 - 1, p2Wins.keys.maxOf { it } * 2)

    }

    private fun wins(p1Wins: Map<Int, Long>, i: Int): Map<Int, Long> {
        TODO()
    }

    private fun loose(winner: Map<Int, Long>, startCount: Int): Map<Int, Long> {
        TODO("Not yet implemented")
    }

    private fun playQuantumDie(pos: Int, points: Int, round: Int): List<Int> {

        if (points >= 21) return listOf(round)

        val result = mutableListOf<Int>()
        val pos1 = incPosition(pos)
        result.addAll(playQuantumDie(pos1, points + points(pos1), round + 1))

        val pos2 = incPosition(pos1)
        result.addAll(playQuantumDie(pos2, points + points(pos2), round + 1))

        val pos3 = incPosition(pos2)
        result.addAll(playQuantumDie(pos3, points + points(pos3), round + 1))

        return result
    }

}
