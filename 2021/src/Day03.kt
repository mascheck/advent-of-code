import java.util.*
import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        fun sum(l: List<Int>, s: String): List<Int> = l.mapIndexed { i, c -> c + s.getOrElse(i, 0) }

        fun mostCommon(s: String, threshold: Int): String =
             s.map { threshold.compareTo(it.digitToInt()).coerceAtLeast(0) }.joinToString("")

        val n = input[0].length
        val threshold = input.size/2

        val s0 = "00000"
        val ss= input.fold(s0) {acc, s -> sum(acc, s) }
        val sss = mostCommon(ss, threshold)
        println(sss)
        return 0
    }

    fun part1_2(input: List<String>): Int {


        val n = input[0].length
        val size = input.size
        val counters = IntArray(n)

        input.forEach {
            for ((i,c) in it.reversed().withIndex()) {
                counters[i] = counters[i] + c.digitToInt()
            }
        }

        val gamma = BitSet(n)
        for ((i,c) in counters.withIndex()) {
            if (c > size/2) {
                gamma.set(i)
            }
        }

        val g = gamma.toLongArray()[0].toInt()
        val e = 2f.pow(n).toInt() - (g+1)
        return g * e
    }

    fun part2(input: List<String>): Int {
        return input.windowed(4).count{it[0] < it[3]}
    }




    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    println(part1(testInput))
    check(part1(testInput) == 198)
    println(part1(input))

    println(part2(testInput))
    check(part1(testInput) == 5)
    println(part2(input))
}
