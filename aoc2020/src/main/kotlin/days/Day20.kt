package days

import kotlin.math.sqrt

class Day20 {
    class Tile(val id: Int, tileLines: List<String>) {
        val content = tileLines.drop(1).dropLast(1).map { it.drop(1).dropLast(1) }
        var _top = tileLines[0];
        var _right = tileLines.map { l -> l.last() }.joinToString("");
        var _bottom = tileLines.last();
        var _left = tileLines.map { l -> l.first() }.joinToString("");

        fun top(): String = _top
        fun right(): String = _right
        fun bottom(): String = _bottom
        fun left(): String = _left

        var mod = 0;

        fun fitBelow(tile: Day20.Tile): Boolean = top() == tile.bottom()
        fun fitRight(tile: Day20.Tile): Boolean = left() == tile.right()

        fun mod(): Int {
            mod += 1;
            mod %= 8;

            if (mod % 2 == 1) {
                // left <-> right
                _left = _right.also { _right = _left }

                // mirror top+bottom
                _top = _top.reversed()
                _bottom = _bottom.reversed()
            } else {
                _top = (_right.also { _right = _top.reversed() }).reversed()
                _bottom = (_left.also { _left = _bottom.reversed() }).reversed()
            }
            return mod;
        }

        fun content(): List<String> {
            var rotContent = content
            val length = content[0].length
            // need to rotate and flip
            var m = 0
            while (m < mod) {
                if (m % 2 == 0) {
                    // mirror
                    rotContent = rotContent.map { it.reversed() }
                } else {
                    // rotate
                    rotContent = rotContent.map { it.reversed() }
                    val newContent = mutableListOf<String>()
                    for (i in 0..(length - 1)) {
                        newContent.add(rotContent.map { it[i] }.joinToString("").reversed())
                    }
                    rotContent = newContent
                }
                m += 1
            }
            return rotContent
        }

        override fun toString(): String = "$id ($_top $_right $_bottom $_left)"

        fun reset() {
            while (mod != 0) {
                mod()
            }
        }
    }

    fun run(lines: List<String>) {
        var tileId = -1
        val tileLines = mutableListOf<String>()
        val tiles = mutableListOf<Tile>()
        for (line in lines) {
            when {
                line.isEmpty() -> {
                    tiles.add(Tile(tileId, tileLines))
                    tileLines.clear()
                    tileId = 0
                }
                line.startsWith("Tile") -> tileId = line.substring(5).take(4).toInt()
                else -> tileLines.add(line)
            }
        }

        val dim = sqrt(tiles.size.toDouble()).toInt()
        val result = arrangeTiles(dim, listOf<Tile>(), tiles)

        if (result.size != tiles.size) {
            println("Not completed!")
            return
        }

        val lu = result[0].id.toLong()
        val ru = result[dim - 1].id.toLong()
        val lb = result[dim * (dim - 1)].id.toLong()
        val rb = result[(dim * dim) - 1].id.toLong()

        println("$lu $ru")
        println("$lb $rb")
        println("${lu * lb * ru * rb}")

        var image = buildImage(result, dim)

        // image.forEach { println(it) }

        val hashCount = image.map { it.toList().count { c -> c == '#' } }.sum()
        var cnt = hashCount
        for (i in 0..7) {
//            println("-------------------------------------------------------------------------------------------")
//            image.forEach { println(it) }
//            println("-------------------------------------------------------------------------------------------")
            val monsterPos = searchMonster(image)
            if (monsterPos.isNotEmpty()) {
                cnt = hashCount - monsterPos.size
                println("Orientation: $i")
                image.forEach { println(it) }
                println("number of #: $hashCount")
                println("number of # that are not part of a sea monster: $cnt")
            }
            image = if (i % 2 == 1) mirror(image) else rotate(image)
        }
//        println("number of #: $hashCount")
//        println("number of # that are not part of a sea monster: $cnt")
    }

    private fun rotate(image: List<String>): List<String> {
        val newContent = mutableListOf<String>()
        val m = mirror(image)
        for (i in 0..(m[0].length - 1)) {
            newContent.add(m.map { it[i] }.joinToString("").reversed())
        }
        return newContent
    }

    private fun mirror(image: List<String>): List<String> = image.map { it.reversed() }

    private fun searchMonster(image: List<String>): Set<Pair<Int, Int>> {
        var result = mutableSetOf<Pair<Int, Int>>()
        val ymax = image.size - 3
        val xmax = image[0].length - 20
        var mc = 0
        for (y in 0..ymax) {
            for (x in 0..xmax) {
                if (matchMonster0(image[y].drop(x))
                    && matchMonster1(image[y + 1].drop(x))
                    && matchMonster2(image[y + 2].drop(x))
                ) {
                    mc += 1
                    println("$y - $x")
                    result.addAll(getMonsterPos(x, y))
                }
            }
        }
        if (mc > 0) println("Monsters: $mc (${mc*56})  ${result.size}")
        return result
    }

    private fun matchMonster0(line: String): Boolean {
//        println("0: $line")
//        print("0:                   #")
        val result = line[18] == '#'
//        return if (result) {println(" -> 1"); true} else {println(" -> 0"); false}
        return result
    }

    private fun matchMonster1(line: String): Boolean {
//        println("1: $line")
//        print("1: #    ##    ##    ###")
        val result = line[0] == '#' && line[5] == '#' && line[6] == '#' && line[11] == '#' && line[12] == '#' && line[17] == '#' && line[18] == '#' && line[19] == '#'
        //return if (result) {println(" -> 1"); true} else {println(" -> 0"); false}
        return result
    }

    private fun matchMonster2(line: String): Boolean {
        //println("2: $line")
        //print("2:  #  #  #  #  #  # ")
        val result =  line[1] == '#' && line[4] == '#' && line[7] == '#' && line[10] == '#' && line[13] == '#' && line[16] == '#'
//        return if (result) {println(" -> 1"); true} else {println(" -> 0"); false}
        return result
    }

    private fun getMonsterPos(x: Int, y: Int): Collection<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        //  0123456789012345678
        // "                  #" 1
        // "#    ##    ##    ###" 8
        // " #  #  #  #  #  # " 6
        result.add(Pair(x + 18, y))
        //  01234567890123456789
        // "#    ##    ##    ###"
        result.add(Pair(x + 0, y + 1))
        result.add(Pair(x + 5, y + 1))
        result.add(Pair(x + 6, y + 1))
        result.add(Pair(x + 11, y + 1))
        result.add(Pair(x + 12, y + 1))
        result.add(Pair(x + 17, y + 1))
        result.add(Pair(x + 18, y + 1))
        result.add(Pair(x + 19, y + 1))
        //  0123456789012345678
        // " #  #  #  #  #  # "
        result.add(Pair(x + 1, y + 2))
        result.add(Pair(x + 4, y + 2))
        result.add(Pair(x + 7, y + 2))
        result.add(Pair(x + 10, y + 2))
        result.add(Pair(x + 13, y + 2))
        result.add(Pair(x + 16, y + 2))

        return result
    }

    private fun buildImage(tiles: List<Day20.Tile>, dim: Int): List<String> {
        var contentTiles = tiles.map { it.content() }
        var dim = 12
        var contentDim = contentTiles[0].size
        assert(contentDim == contentTiles[0][0].length)

        val result = mutableListOf<String>()
        for (i in 0..(dim - 1)) {
            for (j in 0..(contentDim - 1)) {
                result.add(contentTiles.mapIndexedNotNull { index, t -> if (index / dim == i) t[j] else null }
                    .joinToString(""))
            }
        }
        return result
    }

    private fun arrangeTiles(dim: Int, arranged: List<Day20.Tile>, tiles: List<Day20.Tile>): List<Day20.Tile> {
        // println(" found: ${arranged.size} - rest: ${tiles.size}")
        if (tiles.isEmpty()) return arranged
        for (tile in tiles) {
            // print(" ${tile.id}")
            var mod = 0
            do {
                if (isFit(arranged, dim, tile)) {
                    val newArranged = arranged + tile
                    val result = arrangeTiles(dim, newArranged, tiles.filterNot { it.id == tile.id })
                    if (result.size == dim * dim) return result
                }
                mod = tile.mod()
            } while (mod != 0)
            tile.reset()
            // println()
        }
        return arranged
    }

    private fun isFit(arranged: List<Tile>, dim: Int, tile: Tile): Boolean {
        val x = arranged.size % dim
        val y = arranged.size / dim

        // print(" isFit: newPos($x,$y) ? ")
        // check above neighbor
        val yy = y - 1
        var idx = 0
        if (yy >= 0) {
            idx = yy * dim + x
            // print(" ($x,$yy = $idx)")
            if (!tile.fitBelow(arranged[yy * dim + x])) return false
        }

        // check left neighbor
        val xx = x - 1
        if (xx >= 0) {
            idx = y * dim + xx
            // print(" ($xx,$y = $idx)")
            if (!tile.fitRight(arranged[idx])) return false
        }
        return true
    }

}