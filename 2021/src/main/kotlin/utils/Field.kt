package utils

import java.util.*

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

    fun getEastNeighborPos(i: Int, endless: Boolean = false) = getEastNeighborPos(getPos(i), endless)

    fun getEastNeighborPos(p: FieldPos, endless: Boolean = false) =
        if (p.x + 1 != dimX)
            Optional.of(FieldPos(p.x + 1, p.y))
        else if (endless)
            Optional.of(FieldPos(0, p.y))
        else Optional.empty()

    fun getSouthNeighborPos(i: Int, endless: Boolean = false) = getSouthNeighborPos(getPos(i), endless)

    fun getSouthNeighborPos(p: FieldPos, endless: Boolean = false) =
        if (p.y + 1 != dimY)
            Optional.of(FieldPos(p.x, p.y + 1))
        else if (endless)
            Optional.of(FieldPos(p.x, 0))
        else Optional.empty()

    fun getValues(pos: List<FieldPos>) = pos.map { get(it) }

    fun setValues(pos: List<FieldPos>, newValue: X): Field<X> {
        val newData = mutableListOf<List<X>>()
        for (y in 0 until dimY) {
            val newRow = mutableListOf<X>()
            newData.add(newRow)
            for (x in 0 until dimX) {
                val fp = FieldPos(x, y)
                val value = if (pos.contains(fp)) newValue else get(fp)
                newRow.add(value)
            }
        }
        return Field(newData)
    }
}
