fun main() {
    fun part1(input: List<String>): Int {
        return input.windowed(2).count{it[0] < it[1]}
    }

    fun part2(input: List<String>): Int {
        return input.windowed(4).count{it[0] < it[3]}
    }

    val testInput = readInput("Day01_test")
    val input = readInput("Day01")

    println(part1(testInput))
    check(part1(testInput) == 7)
    println(part1(input))

    println(part2(testInput))
    check(part1(testInput) == 5)
    println(part2(input))
}
