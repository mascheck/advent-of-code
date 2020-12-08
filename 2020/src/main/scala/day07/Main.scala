package day07

import io.Input

import scala.collection.mutable
import scala.util.matching.Regex

case class Bag(color: String, body: List[(String, String)]) {

  def contains(color: String): Boolean =
    body.exists(_._2 == color)
}

sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](value: A, children: List[Tree[A]]) extends Tree[A]

object Main {
  val InputPattern: Regex = "(?>(?>\\s(\\d|no)\\s)|^)(.*?)\\sbags?".r

  def main(args: Array[String]): Unit = {
    val input = Input.read("/day07.txt").map(parse)
    val result1 = task1(input)
    val result2 = task2(input)
    println(result1)
    println(result2)
  }

  def parse(input: String): Bag = {
    val matches = InputPattern
      .findAllIn(input)
      .matchData
      .map(m => (m.group(1), m.group(2)))
      .toList
    Bag(matches.head._2, matches.drop(1))
  }

  def task1(input: List[Bag]): Int = {
    var countContainingBags = mutable.ListBuffer("shiny gold")
    val containingBags = mutable.HashSet[String]()
    while (countContainingBags.nonEmpty) {
      val newlyFound = mutable.ListBuffer[String]()
      countContainingBags.foreach { color =>
        input.filter(bag => bag.contains(color)).foreach { b =>
          newlyFound.addOne(b.color)
          containingBags.add(b.color)
        }
      }
      countContainingBags = newlyFound
    }
    containingBags.size
  }

  def task2(input: List[Bag]): Int = {
    val tree = growTree(input, ("1", "shiny gold"), 1)
    calcBags(tree) - 1
  }

  def calcBags(
      tree: Tree[(String, String)],
  ): Int =
    tree match {
      case Leaf(value)             => 0
      case Branch(value, children) => value._1.toInt + children.map(calcBags).sum
    }

  def growTree(
      input: List[Bag],
      value: (String, String),
      prevValue: Int,
  ): Tree[(String, String)] = {
    if (value._1 == "no") return Leaf(value = ("0", value._2))
    val bag = input.find(_.color == value._2).get
    val x = value._1.toInt * prevValue
    val tree =
      Branch(value = (x.toString, value._2), children = bag.body.map(growTree(input, _, x)))
    tree
  }

}
