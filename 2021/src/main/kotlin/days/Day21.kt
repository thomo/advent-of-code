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


        TODO()

    }


    fun analyse2_firstTry(lines: List<String>): Any {
        var (p1, p2) = parse(lines)

        var uni1 = emptyUni()
        var uni2 = emptyUni()

        uni1[p1][p1] = 1
        p1 = incPosition(p1)
        uni1[p1][points(p1)] = 1
        p1 = incPosition(p1)
        uni1[p1][points(p1)] = 1
        uni2[p2][p2] = 1
        p2 = incPosition(p2)
        uni2[p2][points(p2)] = 1
        p2 = incPosition(p2)
        uni2[p2][points(p2)] = 1

        val total1 = play(uni1)
        val total2 = 0L // play(uni2)
        return max(total1, total2)
    }

    private fun play(uniStart: Array<Array<Long>>): Long {
        var uni = uniStart
        var total = 0L
        do {
            //printUni(uni)
            val unew = rollDiracDice(uni)
            total += unew.map { space -> space[21] }.sum()
            unew.forEach { space -> space[21] = 0 }
            uni = unew
            printUni(uni)
            println(total)
        } while (uni.any { space -> space.any { games -> games > 0 } })
        return total
    }

    private fun printUni(uni: Array<Array<Long>>) {
        uni.forEachIndexed { i, space ->
            println("${i}: " + space.contentToString())
        }
        println("-".repeat(20))
    }

    private fun rollDiracDice(uni: Array<Array<Long>>): Array<Array<Long>> {
        val result = emptyUni()
        uni.forEachIndexed { i, space ->
            space.forEachIndexed { j, games ->
                if (games > 0) {
                    var spacePos = incPosition(i)
                    var newPoints = Math.min(21, j + spacePos)
                    result[spacePos][newPoints] = uni[spacePos][newPoints] + games
                    spacePos = incPosition(spacePos)
                    newPoints = Math.min(21, j + spacePos)
                    result[spacePos][newPoints] += (uni[spacePos][newPoints] + games)
                    spacePos = incPosition(spacePos)
                    newPoints = Math.min(21, j + spacePos)
                    result[spacePos][newPoints] += (uni[spacePos][newPoints] + games)
                }
            }
        }
        return result
    }

    private fun emptyUni() = Array(10) { _ -> Array(22) { _ -> 0L } }

}
