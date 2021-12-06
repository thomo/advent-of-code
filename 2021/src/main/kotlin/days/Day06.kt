package days

class Day06 : DayX {
    data class Group(val key: Int, val size: Long) {
        fun nextDay() = Group(if (key == 0) 6 else key - 1, size)
    }

    override fun analyse(lines: List<String>): Any {
        return play(lines, 80)
    }

    private fun play(lines: List<String>, days: Int): Long {
        var swarm = createSwarm(lines)
        for (i in 1..days) {
            swarm = nextDay(swarm)
        }
        return swarm.sumOf { it.size }
    }

    fun nextDay(swarm: List<Group>): List<Group> {
        val n = numberOfNew(swarm)
        return passOneDay(swarm) + createNew(n)
    }

    fun createSwarm(lines: List<String>) =
        lines[0].split(',').map { it.toInt() }.groupBy { it }.map { kv -> Group(kv.key, kv.value.size.toLong()) }

    fun createNew(n: Long) = Group(8, n)

    fun numberOfNew(swarm: List<Group>) = swarm.filter { it.key == 0 }.sumOf { it.size }

    fun passOneDay(swarm: List<Group>) = swarm.map { it.nextDay() }

    override fun analyse2(lines: List<String>): Any {
        return play(lines, 256)

    }

}
