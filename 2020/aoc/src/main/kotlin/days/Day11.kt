package days

import kotlin.math.max
import kotlin.math.min
import kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver

class Day11 {
    private val EMPTY_SEAT: Char = 'L'
    private val OCCUPIED_SEAT: Char = '#'
    private val FLOOR: Char = '.'
    fun run(startLayout: List<String>) {
        var layout: List<List<Char>> = startLayout.map{ r -> r.toList() }
        do {
            val newLayout = applyRules(layout)
            val hasChanged = isDifferent(layout, newLayout)
            layout = newLayout
        } while (hasChanged)
        println(countOccupiedSeats(layout))

        layout = startLayout.map{ r -> r.toList() }
        do {
            val newLayout = applyRules2(layout)
            val hasChanged = isDifferent(layout, newLayout)
            layout = newLayout
        } while (hasChanged)
        println(countOccupiedSeats(layout))
    }

    private fun applyRules2(layout: List<List<Char>>): List<List<Char>> {
        var newLayout = mutableListOf<List<Char>>()
        for(x in 0..layout.size-1) {
            val row = mutableListOf<Char>()
            for (y in 0..layout[x].size-1) {
                row.add(evalRules2(layout, x, y))
            }
            newLayout.add(row)
        }
        return newLayout
    }

    private fun applyRules(layout: List<List<Char>>): List<List<Char>> {
        var newLayout = mutableListOf<List<Char>>()
        for(x in 0..layout.size-1) {
            val row = mutableListOf<Char>()
            for (y in 0..layout[x].size-1) {
                row.add(evalRules(layout, x, y))
            }
            newLayout.add(row)
        }
        return newLayout
    }

    private fun evalRules2(layout: List<List<Char>>, x: Int, y: Int): Char {
        val z = layout[x][y]
        when (z) {
            FLOOR -> return FLOOR
            EMPTY_SEAT -> return if (countOccupiedVisibleAdjacent(layout, x, y) == 0)  OCCUPIED_SEAT else EMPTY_SEAT
            OCCUPIED_SEAT ->  return if (countOccupiedVisibleAdjacent(layout, x, y) > 4)  EMPTY_SEAT else OCCUPIED_SEAT
        }
        return z
    }

    private fun evalRules(layout: List<List<Char>>, x: Int, y: Int): Char {
        val z = layout[x][y]
        when (z) {
            FLOOR -> return FLOOR
            EMPTY_SEAT -> return if (countOccupiedDirectAdjacent(layout, x, y) == 0)  OCCUPIED_SEAT else EMPTY_SEAT
            OCCUPIED_SEAT ->  return if (countOccupiedDirectAdjacent(layout, x, y) > 3)  EMPTY_SEAT else OCCUPIED_SEAT
        }
        return z
    }

    private fun countOccupiedVisibleAdjacent(layout: List<List<Char>>, x: Int, y: Int): Int {
        var cnt = 0
        for (i in -1..1) {
            for (j in -1..1) {
                if (i == 0 && j == 0) continue
                if (isNextSeat(layout, x, y, i, j) == OCCUPIED_SEAT) cnt += 1
            }
        }
        return cnt
    }

    private fun isNextSeat(layout: List<List<Char>>, x: Int, y: Int, dx: Int, dy: Int): Char {
        var i = x + dx
        var j = y + dy
        while (i != -1 && j != -1 && i != layout.size && j != layout[i].size) {
            when (layout[i][j]) {
                FLOOR -> {
                    i += dx
                    j += dy
                    continue
                }
                else -> return layout[i][j]
            }
        }
        return FLOOR
    }

    private fun countOccupiedDirectAdjacent(layout: List<List<Char>>, x: Int, y: Int): Int {
        var cnt = 0
        for (i in max(x-1,0)..min(x+1, layout.size-1)) {
            for (j in max(y-1, 0)..min(y+1, layout[i].size -1)) {
                if (i == x && j == y) continue
                if (layout[i][j] == OCCUPIED_SEAT) cnt += 1
            }
        }
        return cnt
    }

    private fun isDifferent(layout: List<List<Char>>, newLayout: List<List<Char>>): Boolean =
        layout.zip(newLayout).any { p -> p.first.zip(p.second).any {pc -> pc.first != pc.second} }

    private fun countOccupiedSeats(layout: List<List<Char>>): Int =
        layout.sumBy { r -> r.count { c -> c == OCCUPIED_SEAT } }

}
