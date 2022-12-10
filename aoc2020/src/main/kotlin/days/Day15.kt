package days

import kotlin.reflect.jvm.internal.impl.utils.NumbersKt

class Day15 {

    private var numberIndex: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()

    fun run(start: String) {
        start(start)

        // println(turn(2020)) // 412
        println(turn(30000000))
    }

    fun start(s: String) {
        s.split(",").map { it.toInt() }.toList().forEachIndexed { index, i -> numberIndex[i] = Pair(0, index + 1) }
    }

    fun turn(i: Int): Int {
        if (i <= numberIndex.size) {
            return numberIndex.filterValues { value -> value.second == i }.keys.first()
        }

        var idx = numberIndex.size + 1
        var lastSpoken = 0

        while (idx <= i) {
            if (!numberIndex.containsKey(lastSpoken)) {
                numberIndex[lastSpoken] = Pair(0, idx)
                lastSpoken = 0
            } else {
                val lp = numberIndex[lastSpoken]!!
                if (lp.first == 0) {
                    lastSpoken = 0
                } else {
                    lastSpoken = lp.second - lp.first
                }
                val op = numberIndex[lastSpoken]
                numberIndex[lastSpoken] = Pair(op?.second ?: 0, idx)
            }
            idx += 1
        }
        return lastSpoken
    }

    override fun toString(): String {
        return numberIndex.toString()
    }



}
