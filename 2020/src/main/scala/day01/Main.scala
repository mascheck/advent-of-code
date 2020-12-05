package day01

import io.Input

object Main {

  def main(args: Array[String]): Unit = {
    val input = Input.read("/day01.txt").map(_.toInt)
    val result1 = findSummands(input, 2020, 2)
    val result2 = findSummands(input, 2020, 3)
    println(f"Result: $result1 = ${result1.product}")
    println(f"Result: $result2 = ${result2.product}")
  }

  def findSummands(values: List[Int], sum: Int, n: Int): List[Int] =
    values.combinations(n).find(_.sum == sum).getOrElse(List())
}
