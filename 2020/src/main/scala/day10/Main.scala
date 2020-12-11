package day10

import io.Input

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

sealed trait Tree[+A] {
  def paths(): Long
}

case class Leaf[A](value: A) extends Tree[A] {
  override def paths(): Long = 1
}

case class Branch[A](value: A, children: List[Tree[A]]) extends Tree[A] {
  override def paths(): Long = children.map(_.paths()).sum
}

object Main {

  def main(args: Array[String]): Unit = {
    val input = Input.read("/day10.txt").map(_.toLong)
    val result1 = task1(input)
    println(result1)
    println(result1._1 * result1._2)
    println(task2(input))
  }

  def task1(input: List[Long]): (Long, Long) = {
    val adapters = input.sorted.to(mutable.ArrayBuffer)
    val max = input.max + 3
    var delta1 = 0
    var delta3 = 0

    println(adapters)
    println(adapters.size)
    adapters.prepend(0)
    adapters.append(max)

    for ((adapter, i) <- adapters.zipWithIndex)
      if (i > 0) {
        val dif = adapter - adapters(i - 1)
        if (dif == 1) delta1 += 1
        if (dif == 3) delta3 += 1
      }
    (delta1, delta3)
  }

  def reverseProcessAdapters(adapters: ArrayBuffer[Long]): Long = {
    val adapterCount = adapters.length
    val countOfWaysToEnd = new Array[Long](adapterCount)
    countOfWaysToEnd(adapterCount - 1) = 1
    for (i <- adapterCount - 2 to 0 by -1) {
      var accessors = 0L
      try {
        if (adapters(i + 1) - adapters(i) <= 3)
          accessors += countOfWaysToEnd(i + 1)
        if (adapters(i + 2) - adapters(i) <= 3)
          accessors += countOfWaysToEnd(i + 2)
        if (adapters(i + 3) - adapters(i) <= 3)
          accessors += countOfWaysToEnd(i + 3)
      } catch {
        case _: IndexOutOfBoundsException => println("end of list")
      }
      countOfWaysToEnd(i) = accessors
    }
    countOfWaysToEnd(0)
  }

  def processAdapters(adapters: ArrayBuffer[Long]): Long = {
    var accessors = 0L
    for ((adapter, i) <- adapters.zipWithIndex) {
      if (i == adapters.length - 1) return accessors
      var accessor = 0L
      if (i + 1 < adapters.length && adapters(i + 1) - adapter <= 3) accessor += 1
      if (i + 2 < adapters.length && adapters(i + 2) - adapter <= 3) accessor += 1
      if (i + 3 < adapters.length && adapters(i + 3) - adapter <= 3) accessor += 1
      if (accessors == 0) accessors = accessor else accessors *= accessor
    }
    accessors
  }

  def processAdapters2(adapters: ArrayBuffer[Double]): Long =
    adapters.map(a => adapters.count(b => b > a && b <= a + 3)).takeWhile(_ != 0).product

  def task2(input: List[Long]): Long = {
    val adapters = input.sorted.to(mutable.ArrayBuffer)
    val max = input.max + 3

    adapters.prepend(0)
    adapters.append(max)
    println(processAdapters(adapters))
    println(reverseProcessAdapters(adapters))
    processAdapters2(adapters.map(_.toDouble))
  }

  def grow(value: Long, adapters: List[Long]): Tree[Long] = {
    //println(f"grow tree for $value, adapters left: ${adapters.size}")
    val possibleAdapters = getPossibleChildren(value, adapters)
    if (possibleAdapters.isEmpty) return Leaf(value = value)
    //println(f"possible adapters found: ${possibleAdapters.size}")
    Branch(
      value = value,
      children = possibleAdapters.map(i => grow(i, adapters.filter(_ > i))),
    )
  }

  def getPossibleChildren(value: Long, adapters: List[Long]): List[Long] =
    adapters.filter(_ - value <= 3)
}
