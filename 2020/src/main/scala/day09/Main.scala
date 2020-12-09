package day09

import io.Input

object Main {
  val preambleSize = 25

  def main(args: Array[String]): Unit = {
    val input = Input.read("/day09.txt").map(_.toLong)
    val result1 = task1(input)
    val result2 = task2(input, result1)
    println(result1)
    println(result2._1 + result2._2)
    println(result2)
  }

  def task1(input: List[Long]): Long = {
    for (i <- preambleSize to input.length) {
      val prev = input.slice(i - preambleSize, i)
      if (isWeak(prev, input(i))) return input(i)
    }
    0
  }

  def task2(input: List[Long], value: Long): (Long, Long) = {
    for (i <- 0 to input.length)
      for (j <- i + 1 to input.length) {
        val slice = input.slice(i, j)
        if (slice.sum == value) return (slice.max, slice.min)
      }
    (0, 0)
  }

  def isWeak(prev: List[Long], value: Long): Boolean =
    !prev.combinations(2).exists(_.sum == value)
}
