package days

class Day12 : DayX {
    data class Edge(val a: String, val b: String)

    data class MyPath(val path: List<String>) {
        fun getEndNode() = path.last()

        fun isValidSuc(s: String) = (s.uppercase() == s) || !path.contains(s)

        fun add(s: String) = MyPath(path + s)

        fun isValidSuc2(s: String): Boolean {
            if (s.uppercase() == s) return true
            if (s == "start") return false
            val groupSizes = (path + s).filter { it.lowercase() == it }
                    .groupBy { it }
                    .map { it.value.size }
                    .filter { it > 1 }
            if (groupSizes.size > 1) return false // mehr als 1x mehrfach
            if (groupSizes.isEmpty()) return true // bisher keines mehrfach
            return groupSizes.first() < 3 // nur doppelt
        }
    }

    private fun calcNext(edges: List<Day12.Edge>, paths: Set<MyPath>): Set<MyPath> {
        val newPaths = paths.flatMap { p ->
            val currentEndNode = p.getEndNode()
            if (currentEndNode == "end") listOf(p) else {
                val suc = getSuccessors(edges, currentEndNode)
                suc.mapNotNull { s ->
                    if (p.isValidSuc(s)) {
                        p.add(s)
                    } else null
                }
            }
        }.toSet()
        return if (newPaths != paths) calcNext(edges, newPaths) else paths
    }

    private fun calcNext2(edges: List<Day12.Edge>, paths: Set<MyPath>): Set<MyPath> {
        val newPaths = paths.flatMap { p ->
            val currentEndNode = p.getEndNode()
            if (currentEndNode == "end") listOf(p) else {
                val suc = getSuccessors(edges, currentEndNode)
                suc.mapNotNull { s ->
                    if (p.isValidSuc2(s)) {
                        p.add(s)
                    } else null
                }
            }
        }.toSet()
        return if (newPaths != paths) calcNext2(edges, newPaths) else paths
    }

    private fun getSuccessors(edges: List<Day12.Edge>, node: String) = edges.filter { it.a == node }.map { it.b } + edges.filter { it.b == node }.map { it.a }

    fun parseInput(lines: List<String>) = lines.map { l ->
        val parts = l.split("-")
        Edge(parts[0], parts[1])
    }

    override fun analyse(lines: List<String>): Any {
        val edges = parseInput(lines)
        val newPaths = calcNext(edges, setOf(MyPath(listOf("start"))))
        return newPaths.size
    }

    override fun analyse2(lines: List<String>): Any {
        val edges = parseInput(lines)
        val newPaths = calcNext2(edges, setOf(MyPath(listOf("start"))))
        return newPaths.size
    }


}
