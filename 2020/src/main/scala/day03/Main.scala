package day03

import io.Input

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Main {

  def main(args: Array[String]): Unit = {
    val input = Input.read("/day03.txt")

    val t1 = System.currentTimeMillis()
    val result1 = move(input)
    val t2 = System.currentTimeMillis()

    println(f"Result: $result1")
    println(f"Took: ${t2 - t1}ms")

    val t3 = System.currentTimeMillis()
    val result2 = checkAll(input)
    val t4 = System.currentTimeMillis()

    println(f"Result: $result2")
    println(f"Took: ${t4 - t3}ms")
  }

  def move(values: List[String]): Int = {
    var trees = 0
    var y = 0
    for (row <- values) {
      var x = y * 3
      while (x >= row.length)
        x -= row.length
      if (row.charAt(x).equals('#'))
        trees += 1
      y += 1
    }
    trees
  }

  def checkAll(values: List[String]): Long = {
    val trees = new ListBuffer[Int]()
    trees += moveCustom(values, 1, 1)
    trees += moveCustom(values, 3, 1)
    trees += moveCustom(values, 5, 1)
    trees += moveCustom(values, 7, 1)
    trees += moveCustom(values, 1, 2)

    var x: Long = 1
    for (i <- trees)
      x = x * i
    x
  }

  def moveCustom(values: List[String], deltaX: Int, deltaY: Int): Int = {
    var trees = 0
    var i = 0
    for (y <- values.indices by deltaY) {
      val row = values(y)
      var x = i * deltaX
      while (x >= row.length)
        x -= row.length
      if (row.charAt(x).equals('#'))
        trees += 1
      i += 1
    }
    trees
  }
}
