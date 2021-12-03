fun main() {
    fun solveOne(binaryNumberList: List<String>): Long {
        val pairs = ArrayList<Pair>(binaryNumberList.size)
        for (index in 0 until binaryNumberList[0].length) {
            pairs.add(Pair())
        }
        for (binaryNumber in binaryNumberList) {
            val number = binaryNumber.toCharArray()
            for (index in number.indices) {
                pairs[index].incrementBy(number[index])
            }
        }
        val gamma = pairs.joinToString("", transform = Pair::mostCommon).toLong(2)
        val epsilon = pairs.joinToString("", transform = Pair::leastCommon).toLong(2)
        return gamma * epsilon
    }

    fun oxygenGeneratorRating(binaryNumberList: List<String>): Long {
        var index = 0
        val filter = StringBuilder()
        var filteredNumberList = binaryNumberList
        do {
            val ratingValue = filteredNumberList
                .map { s: String -> s.toCharArray()[index] }
                .count { character: Char -> character == '1' }
            index++
            if (ratingValue * 2 >= filteredNumberList.size) {
                filter.append('1')
            } else {
                filter.append('0')
            }
            filteredNumberList = filteredNumberList.filter { s -> s.startsWith(filter.toString()) }
        } while (filteredNumberList.size > 1)
        return filteredNumberList[0].toLong(2)
    }

    fun co2ScrubberRating(binaryNumberList: List<String>): Long {
        var index = 0
        val filter = StringBuilder()
        var filteredNumberList = binaryNumberList
        do {
            val ratingValue = filteredNumberList
                .map { s: String -> s.toCharArray()[index] }
                .count { character: Char -> character == '0' }
            index++
            if (ratingValue * 2 <= filteredNumberList.size) {
                filter.append('0')
            } else {
                filter.append('1')
            }
            filteredNumberList = filteredNumberList.filter { s -> s.startsWith(filter.toString()) }
        } while (filteredNumberList.size > 1)
        return filteredNumberList[0].toLong(2)
    }

    fun solveTwo(binaryNumberList: List<String>): Long {
        val oxygenGeneratorRating = oxygenGeneratorRating(binaryNumberList)
        val co2ScrubberRating = co2ScrubberRating(binaryNumberList)
        return oxygenGeneratorRating * co2ScrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(solveOne(testInput) == 198L)
    check(solveTwo(testInput) == 230L)

    val input = readInput("Day03")
    println(solveOne(input))
    println(solveTwo(input))
}


private class Pair {
    private var one = 0
    private var zero = 0

    fun incrementBy(value: Char) {
        if (value == '1') {
            incrementOnes()
        } else {
            incrementZeros()
        }
    }

    private fun incrementOnes() {
        one++
    }

    private fun incrementZeros() {
        zero++
    }

    fun mostCommon(): String {
        return if (one > zero) "1" else "0"
    }

    fun leastCommon(): String {
        return if (one < zero) "1" else "0"
    }
}