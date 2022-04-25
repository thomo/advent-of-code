package days

import utils.Field
import utils.FieldPos

class Day25 : Day00 {
    private val FREE_SPACE: Char = '.'
    private val EAST_MOVER: Char = '>'
    private val SOUTH_MOVER: Char = 'v'

    private fun getEastMovers(f: Field<Char>) = (0 until f.size)
        .filter { i -> f.get(i) == EAST_MOVER }
        .filter { i -> f.get(f.getEastNeighborPos(i, true).get()) == FREE_SPACE }
        .map { i -> f.getPos(i) }

    private fun moveEast(f: Field<Char>, em: List<FieldPos>) =
        f.setValues(em, FREE_SPACE).setValues(em.map { f.getEastNeighborPos(it, true).get() }, EAST_MOVER)

    private fun getSouthMovers(f: Field<Char>) = (0 until f.size)
        .filter { i -> f.get(i) == SOUTH_MOVER }
        .filter { i -> f.get(f.getSouthNeighborPos(i, true).get()) == FREE_SPACE }
        .map { i -> f.getPos(i) }

    private fun moveSouth(f: Field<Char>, em: List<FieldPos>) =
        f.setValues(em, FREE_SPACE).setValues(em.map { f.getSouthNeighborPos(it, true).get() }, SOUTH_MOVER)

    private fun nextStep(f: Field<Char>): Field<Char> {
        val em = getEastMovers(f)
        val fem = moveEast(f, em)
        val sm = getSouthMovers(fem)
        return moveSouth(fem, sm)
    }

    private fun play1(field: Field<Char>): Int {
        var cnt = 0
        var hash2 = field.hashCode()
        var f = field
        do {
            cnt += 1
            val hash1 = hash2
            f = nextStep(f)
            hash2 = f.hashCode()
        } while (hash1 != hash2)
        return cnt
    }

    private fun parseInput(lines: List<String>) = Field<Char>(lines.map { line -> line.toCharArray().toList() })

    override fun analyse(lines: List<String>): Any {
        val field = parseInput(lines)
        return play1(field)
    }

    override fun analyse2(lines: List<String>): Any {
        TODO("Not yet implemented")
    }

}
