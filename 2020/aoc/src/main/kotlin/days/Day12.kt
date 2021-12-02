package days

import kotlin.math.abs

class Day12 {
    enum class Direction {
        NORTH, EAST, SOUTH, WEST;

        fun rotate(r: Int): Direction {
            val count: Int = Direction.values().size
            return Direction.values()[Math.floorMod((ordinal + r), count)]
        }
    }

    class Ship(val face: Day12.Direction, val n: Int = 0, val e: Int = 0) {

        fun execute(action: String, value: Int): Day12.Ship {
            when (action) {
                "N" -> return  Ship(face, n + value, e)
                "S" -> return  Ship(face, n - value, e)
                "E" -> return  Ship(face, n, e + value)
                "W" -> return  Ship(face, n, e - value)
                "F" -> return execute(face.toString().substring(0,1), value)
                "R" -> return Ship(face.rotate(value/90), n, e)
                "L" -> return Ship(face.rotate(value/-90), n, e)
            }
            return Ship(Direction.EAST, 0, 0)
        }

        fun distance(): Int = abs(n) + abs(e)

        override fun toString(): String = "Face: $face  Pos: $n:$e Distance: ${distance()}"
    }

    class Ship2(val n: Int, val e: Int, val wn: Int, val we: Int) {
        fun execute(action: String, value: Int): Day12.Ship2 {
            when (action) {
                "N" -> return Ship2(n, e, wn+value, we)
                "S" -> return Ship2(n, e,  wn-value, we)
                "E" -> return Ship2(n, e,  wn, we+value)
                "W" -> return Ship2(n, e,  wn, we-value)
                "F" -> return Ship2(n+value*wn, e+value*we,  wn, we)
                "R" -> return Ship2(n,e, rotate(value/90).first, rotate(value/90).second)
                "L" -> return Ship2(n,e, rotate(value/-90).first, rotate(value/-90).second)
            }
            return this
        }

        private fun rotate(steps: Int): Pair<Int, Int> {
            when (Math.floorMod(steps, 4)) {
                0 -> return Pair(wn, we)
                1 -> return Pair(we * -1, wn )
                2 -> return Pair(wn * -1, we * -1)
                3 -> return Pair(we, wn *  -1)
            }
            return Pair(wn, we)
        }

        fun distance(): Int = abs(n) + abs(e)

        override fun toString(): String = "Pos: $n:$e WP: $wn:$we Distance: ${distance()}"
    }

    fun run(lines: List<String>) {
        var ship = Ship(Direction.EAST)
        var ship2 = Ship2(0, 0, 1, 10)
        for (cmd in lines) {
            val action = cmd.substring(0,1)
            val value = cmd.substring(1).toInt()
            ship = ship.execute(action, value)
            ship2 = ship2.execute(action, value)
            // println(cmd + " -> " + ship2)
        }

        //println(ship)
        println(ship2)
    }

}
