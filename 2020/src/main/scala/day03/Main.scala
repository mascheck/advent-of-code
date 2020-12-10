package day03

import io.Input

object Main {
  val Slopes = List((1, 1), (3, 1), (5, 1), (7, 1), (1, 2))

  def main(args: Array[String]): Unit = {
    val input = Input.read("/day03.txt")
    val result1 = countTreesOnSlope(input, (3, 1))
    val result2 = Slopes.map(countTreesOnSlope(input, _)).product
    println(f"Result: $result1")
    println(f"Result: $result2")
  }

  def countTreesOnSlope(values: List[String], delta: (Int, Int)): Long =
    values.zipWithIndex.count(isTree(_, delta))

  def isTree(row: (String, Int), delta: (Int, Int)): Boolean =
    row._2 % delta._2 == 0 && row._1
      .charAt(((row._2 / delta._2) * delta._1) % row._1.length)
      .equals('#')
}
