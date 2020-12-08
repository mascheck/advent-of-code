package day06

import day04.Main.{isPassportValid, parsePassport}

import scala.collection.mutable.ListBuffer
import scala.io.{BufferedSource, Source}

object Main {

  def main(args: Array[String]): Unit = {
    val source = Source.fromInputStream(getClass.getResourceAsStream("/day06.txt"))
    val result1 = countAnyAnswers(source)
    source.close()
    println(f"Result: $result1")
    val source2 = Source.fromInputStream(getClass.getResourceAsStream("/day06.txt"))
    val result2 = countEveryAnswers(source2)
    source2.close()
    println(f"Result: $result2")
  }

  def countAnyAnswers(source: BufferedSource): Int = {
    val lines = new ListBuffer[String]()
    var answers = 0
    for (line <- source.getLines())
      if (line.isEmpty) {
        answers += countAnswersForGroup(lines.toList)
        lines.clear()
      } else
        lines += line
    answers += countAnswersForGroup(lines.toList)
    answers
  }

  def countEveryAnswers(source: BufferedSource): Int = {
    val lines = new ListBuffer[String]()
    var answers = 0
    for (line <- source.getLines())
      if (line.isEmpty) {
        answers += countOnlyAnswersForGroup(lines.toList)
        lines.clear()
      } else
        lines += line
    answers += countOnlyAnswersForGroup(lines.toList)
    answers
  }

  def countAnswersForGroup(answers: List[String]): Int =
    answers.flatMap(_.split("")).toSet.size

  def countOnlyAnswersForGroup(answers: List[String]): Int = {
    val r = answers.reduce(getStringChars)
    r.length
  }

  def getStringChars(s1: String, s2: String): String =
    s1.toCharArray.filter(s2.contains(_)).mkString("")
}
