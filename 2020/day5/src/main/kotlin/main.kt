import java.io.File

fun main(args: Array<String>) {
    val seatNumbers = getSeatNumbers()

    println("Max: " + seatNumbers.max())
    for (i in 2 .. 821 ) {
        if (existsSeat(seatNumbers, i-1) && !existsSeat(seatNumbers, i) && existsSeat(seatNumbers, i+1))
            println("Seat: " + i)
    }
}

fun existsSeat(seatNumbers: List<Int>, nr: Int): Boolean = seatNumbers.contains(nr)

private fun getSeatNumbers() = readFileAsLinesUsingUseLines("input.txt")
    .map { l -> l.replace('B', '1') }
    .map { l -> l.replace('F', '0') }
    .map { l -> l.replace('R', '1') }
    .map { l -> l.replace('L', '0') }
    .map { l -> Integer.parseInt(l, 2) }

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }