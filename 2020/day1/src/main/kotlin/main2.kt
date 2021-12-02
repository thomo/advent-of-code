fun main(args: Array<String>) {
    var input = readFileAsLinesUsingUseLines("input.txt")
    for (i in input) {
        var tail = input.drop(1)
        var summand = input.first()
        var result = searchSum(tail.first(), tail.drop(1), summand)
        if (result === null) {
        input = tail }
        else {
            print(summand * result.first * result.second)
            return
        }
    }
}

