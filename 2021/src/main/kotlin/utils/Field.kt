package utils

data class Field<X>(private val data: List<List<X>>) {
    val size: Int = data.sumOf { it.size }

    val dimX: Int = data[0].size
    val dimY: Int = data.size

    fun get(i: Int) = get(getPos(i))
    fun get(p: FieldPos) = get(p.x, p.y)
    fun get(x: Int, y: Int) = data[y][x]

    fun getPos(i: Int) = FieldPos(i % dimX, i / dimX)

    fun getStraightNeighborPos(i: Int): List<FieldPos> {
        val p = getPos(i)
        return getStraightNeighborPos(p)
    }

    fun getStraightNeighborPos(p: FieldPos) = getStraightNeighborPos(p.x, p.y)

    fun getStraightNeighborPos(x: Int, y: Int) =
        listOf(
            FieldPos(x - 1, y),
            FieldPos(x + 1, y),
            FieldPos(x, y - 1),
            FieldPos(x, y + 1)
        ).filter { it.x >= 0 && it.y >= 0 && it.x < dimX && it.y < dimY }

    fun getValues(pos: List<FieldPos>) = pos.map { get(it) }

}
