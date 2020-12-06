package day02

import io.Input

import scala.util.matching.Regex

object Main {
  val DBPattern: Regex = "^(\\d{1,2})-(\\d{1,2})\\s(\\w):\\s(.*)$".r

  def main(args: Array[String]): Unit = {
    val input = Input.read("/day02.txt").map(parse)
    val result1 = input.count(isValidOld)
    val result2 = input.count(isValidNew)
    println(f"Result: $result1")
    println(f"Result: $result2")
  }

  def parse(input: String): (Int, Int, Char, String) = {
    val DBPattern(a, b, c, d) = input
    (a.toInt, b.toInt, c.charAt(0), d)
  }

  def isValidOld(entry: (Int, Int, Char, String)): Boolean = {
    val letterOccurrence = entry._4.count(_ == entry._3)
    entry._1 <= letterOccurrence && letterOccurrence <= entry._2
  }

  def isValidNew(entry: (Int, Int, Char, String)): Boolean = {
    val a = entry._4.charAt(entry._1 - 1)
    val b = entry._4.charAt(entry._2 - 1)
    (a == entry._3) != (b == entry._3)
  }
}
