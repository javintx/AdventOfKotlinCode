fun main() {

    fun part1(input: List<String>): Int {
        return input
            .asSequence()
            .map { it.toInt() }
            .windowed(2)
            .count { it.last() > it.first() }
    }

    fun part2(input: List<String>): Int {
        return input
            .asSequence()
            .map { it.toInt() }
            .windowed(3)
            .map { it.sum() }
            .windowed(2)
            .count { it.last() > it.first() }
    }

    fun anotherSolve(input: List<String>, size: Int) =
        input
            .asSequence()
            .map { it.toInt() }
            .windowed(size).map { it.sum() }
            .zipWithNext().count { (prev, next) -> next > prev }

    // test if implementation meets criteria from the description
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))

    println(anotherSolve(input, 1))
    println(anotherSolve(input, 3))
}
