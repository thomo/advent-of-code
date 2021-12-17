package days

class Day11 : Day00 {
    data class MyFieldPos(val x: Int, val y: Int)

    data class MyField(val data: List<List<Int>>) {
        val dimX = data[0].size
        val dimY = data.size

        fun getStarNeighbors(p: MyFieldPos) = getStarNeighbors(p.x, p.y)

        fun getStarNeighbors(x: Int, y: Int): List<MyFieldPos> {
            return setOf(
                MyFieldPos(x - 1, y - 1),
                MyFieldPos(x, y - 1),
                MyFieldPos(x + 1, y - 1),
                MyFieldPos(x - 1, y),
                MyFieldPos(x + 1, y),
                MyFieldPos(x - 1, y + 1),
                MyFieldPos(x, y + 1),
                MyFieldPos(x + 1, y + 1)
            ).filter { it.x >= 0 && it.y >= 0 && it.x < dimX && it.y < dimY }
        }

        fun getPos(selector: (v: Int) -> Boolean) = data
            .flatMapIndexed { y, row ->
                row.mapIndexedNotNull { x, value -> if (selector(value)) MyFieldPos(x, y) else null }
            }.toSet()

        fun getValue(pos: Day11.MyFieldPos) = data[pos.y][pos.x]
        fun setValue(pos: Day11.MyFieldPos, i: Int) =
            MyField(data.mapIndexed { y, row -> if (y == pos.y) row.mapIndexed { x, v -> if (x == pos.x) i else v } else row })

    }

    override fun analyse(lines: List<String>): Any {
        var field = buildField(lines)
        var sum = 0
        for (i in 0 until 100) {
            val (tfield, tsum) = play(field, sum)
            sum = tsum
            field = tfield
        }
        return sum
    }

    fun play(field: MyField, sum: Int): Pair<MyField, Int> {
        var newField = increaseBy1(field)
        newField = flashOn(newField)
        val newSum = sum + countFlashed(newField)
        newField = flashOff(newField)
        return Pair(newField, newSum)
    }

    private fun flashOff(field: Day11.MyField) = Day11.MyField(field.data.map { it.map { v -> if (v > 9) 0 else v } })

    private fun countFlashed(field: Day11.MyField) = field.getPos { v -> v > 9 }.size

    fun flashOn(field: MyField): MyField {
        val allToFlash = field.getPos { v: Int -> v > 9 }
        return flashOn(field, allToFlash, emptySet())
    }

    private fun flashOn(
        field: Day11.MyField,
        newToFlash: Set<Day11.MyFieldPos>,
        alreadyFlashed: Set<MyFieldPos>
    ): Day11.MyField {
        if (newToFlash.isEmpty()) return field
        var newField = field
        newToFlash.map { posToFlash -> field.getStarNeighbors(posToFlash) }
            .forEach { listOfPosToInc ->
                listOfPosToInc.forEach { pos ->
                    newField = newField.setValue(pos, newField.getValue(pos) + 1)
                }
            }
        return flashOn(
            newField,
            newField.getPos { v: Int -> v > 9 } - newToFlash - alreadyFlashed,
            alreadyFlashed + newToFlash)
    }

    fun buildField(lines: List<String>) = MyField(lines.map { toIntList(it) })

    private fun toIntList(it: String) = it.toCharArray().toList().map { it.toString().toInt() }

    private fun increaseBy1(field: MyField) = MyField(field.data.map { it.map { it + 1 } })

    override fun analyse2(lines: List<String>): Any {
        var field = buildField(lines)
        var sum = 0
        var i = 0
        var sumDiff = 0
        do {
            i += 1
            val (tfield, tsum) = play(field, sum)
            sumDiff = tsum - sum
            sum = tsum
            field = tfield
        } while (sumDiff != 100)
        return i
    }

}
