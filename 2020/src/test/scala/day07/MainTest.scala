package day07

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MainTest extends AnyFlatSpec with should.Matchers {

  "countAnswersForGroup" should "return 0 for empty list" in {
    val input = List[String]()

    val result = Main.countAnswersForGroup(input)

    result should be(0)
  }

  it should "return 1 for one answer" in {
    val input = List[String]("a")

    val result = Main.countAnswersForGroup(input)

    result should be(1)
  }

  it should "return 1 for two same answer" in {
    val input = List[String]("a", "a")

    val result = Main.countAnswersForGroup(input)

    result should be(1)
  }

  it should "return 2 for two answers" in {
    val input = List[String]("a", "b")

    val result = Main.countAnswersForGroup(input)

    result should be(2)
  }

  it should "return 3 for three answers" in {
    val input = List[String]("ac", "b")

    val result = Main.countAnswersForGroup(input)

    result should be(3)
  }

  "getStringChars" should "find chars" in {
    val s1 = "a"
    val s2 = "a"

    val result = Main.getStringChars(s1, s2)

    result should be("a")
  }

  "countOnlyAnswersForGroup" should "find chars 1" in {
    val input = List[String]("abc")

    val result = Main.countOnlyAnswersForGroup(input)

    result should be(3)
  }

  it should "find chars 2" in {
    val input = List[String]("a", "b", "c")

    val result = Main.countOnlyAnswersForGroup(input)

    result should be(0)
  }

  it should "find chars 3" in {
    val input = List[String]("ab", "ac")

    val result = Main.countOnlyAnswersForGroup(input)

    result should be(1)
  }

  it should "find chars 4" in {
    val input = List[String]("a", "a", "a", "a")

    val result = Main.countOnlyAnswersForGroup(input)

    result should be(1)
  }

  it should "find only chars" in {
    val s1 = "abc"
    val s2 = "bcd"

    val result = Main.getStringChars(s1, s2)

    result should be("bc")
  }

}
