package days

class Day19 : Day00 {
    data class Point(val x: Int, val y: Int, val z: Int)

    data class Scanner(val origin: Point, val beacons: List<Beacon>)

    data class Beacon(val x: Int, val y: Int, val z: Int) {
        fun orientations(): List<Beacon> {
            val result = mutableListOf<Beacon>()
            for (i in -1..1 step 2) {
                for (j in -1..1 step 2) {
                    for (k in -1..1 step 2) {
                        result.add(Beacon(x, y, z))
                        result.add(Beacon(x, z, y * -1))
                        result.add(Beacon(x, y * -1, z * -1))
                        result.add(Beacon(x, z * -1, y))
                    }
                }
            }
            return result
        }
    }

    fun orientations(scanner: List<Day19.Beacon>) =
        scanner
            .flatMap { it.orientations().mapIndexed { idx, b -> Pair(idx, b) } } // list of list of beacon orientations
            .groupBy { p -> p.first } // regroup them
            .values // key (index) is not needed
            .map { plist -> plist.map { p -> p.second } } // convert pair with index to beacon only

    override fun analyse(lines: List<String>): Any {
        TODO("Not yet implemented")
    }

    override fun analyse2(lines: List<String>): Any {
        TODO("Not yet implemented")
    }

    fun match(s1: List<Day19.Beacon>, s2: List<Day19.Beacon>): Any {
        TODO()
    }

}
