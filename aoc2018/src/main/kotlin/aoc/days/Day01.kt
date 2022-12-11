package aoc.days


class Day01 : Day00 {
  override fun analyse(lines: List<String>): Any {
    return lines.map { l -> l.toInt() }.fold(0){ akku, x -> akku+x}
  }

  override fun analyse2(lines: List<String>): Any {
    val freqs = mutableSetOf<Int>()
    var start = 0
    do {
      start = lines.map { l -> l.toInt() }.fold(start) { akku, x ->
        val sum = akku + x
        val isNew = freqs.add(sum)
        if (!isNew) return sum
        sum
      }
    } while (true)
  }
}