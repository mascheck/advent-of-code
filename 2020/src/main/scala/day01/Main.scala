package day01

import scala.io.Source

object Main {

  def main(args: Array[String]): Unit = {
    val input = readInput()
    val result1 = findSummands(input, 2020, 2)
    val result2 = findSummands(input, 2020, 3)
    println(f"Result: $result1 = ${result1.product}")
    println(f"Result: $result2 = ${result2.product}")
  }

  def readInput(): List[Int] = {
    val source = Source.fromInputStream(getClass.getResourceAsStream("/day01.txt"))
    val input = source.getLines().toList.map(_.toInt)
    source.close()
    input
  }

  def findSummands(values: List[Int], sum: Int, n: Int): List[Int] =
    values.combinations(n).find(_.sum == sum).getOrElse(List())
}
