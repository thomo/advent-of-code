package days

import java.awt.Point

class Day05 : DayX {
    override fun analyse(lines: List<String>): Any {
        var map: Map<Point, Int> = emptyMap()
        lines
            .map { l -> convertLine(l) }
            .filter { p -> isHorV(p) }
            .forEach { p ->
                val (startPoint, endPoint) = p
                val points = getPoints(startPoint, endPoint)
                map = updatePoints(map, points)
                // printMap(map)
            }
        return map.values.filter { v -> v > 1 }.size
    }

    private fun isHorV(p: Pair<Point, Point>) = (p.first.x == p.second.x || p.first.y == p.second.y)

    private fun printMap(map: Map<Point, Int>) {
        val max = map.keys.maxOf { p -> Math.max(p.x, p.y) }
        for (y in 0..max) {
            for (x in 0..max) {
                print(map.getOrDefault(Point(x, y), 0))
            }
            println()
        }
        println("-----------------")
    }

    fun updatePoints(map: Map<Point, Int>, points: Set<Point>): Map<Point, Int> {
        val result = map.toMutableMap()
        points.forEach { p ->
            if (result.containsKey(p)) {
                val v = result[p]; result[p] = v!! + 1
            } else result[p] = 1
        }
        return result
    }

    fun getPoints(startPoint: Point, endPoint: Point): Set<Point> {
        if (startPoint == endPoint) return setOf(startPoint)
        val result = mutableSetOf<Point>()
        if (startPoint.x == endPoint.x) {
            return if (startPoint.y < endPoint.y) {
                for (i in startPoint.y..endPoint.y) {
                    result += Point(startPoint.x, i)
                }
                result
            } else {
                getPoints(endPoint, startPoint)
            }
        } else if (startPoint.y == endPoint.y) {
            return if (startPoint.x < endPoint.x) {
                for (i in startPoint.x..endPoint.x) {
                    result += Point(i, startPoint.y)
                }
                result
            } else {
                getPoints(endPoint, startPoint)
            }
        } else { // diagonal
            val stepX = if (startPoint.x < endPoint.x) 1 else -1
            val stepY = if (startPoint.y < endPoint.y) 1 else -1
            var x = startPoint.x
            var y = startPoint.y
            result += startPoint
            do {
                x += stepX
                y += stepY
                result += Point(x, y)
            } while (x != endPoint.x)
            return result
        }
    }

    fun convertLine(line: String) = line
        .split(" -> ")
        .map { p ->
            val parts = p.split(",").map { it -> it.toInt() }
            Point(parts[0], parts[1])
        }
        .zipWithNext()
        .first()

    override fun analyse2(lines: List<String>): Any {
        var map: Map<Point, Int> = emptyMap()
        lines
            .map { l -> convertLine(l) }
            .forEach { p ->
                val (startPoint, endPoint) = p
                val points = getPoints(startPoint, endPoint)
                map = updatePoints(map, points)
                // printMap(map)
            }
        return map.values.filter { v -> v > 1 }.size

    }

}
