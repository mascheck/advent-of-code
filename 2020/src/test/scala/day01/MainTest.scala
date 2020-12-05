package day01

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MainTest extends AnyFlatSpec with should.Matchers {

  "findSummands" should "find 2 summands" in {
    val input = List[Int](1721, 979, 366, 299, 675, 1456)

    val result = Main.findSummands(input, 2020, 2)

    result should be(List(1721, 299))
  }

  it should "return (0, 0) when no summand was found" in {
    val input = List[Int](1731, 979, 366, 299, 675, 1456)

    val result = Main.findSummands(input, 2020, 2)

    result should be(List())
  }

  it should "not use the same summand twice" in {
    val input = List[Int](1010, 979)

    val result = Main.findSummands(input, 2020, 2)

    result should be(List())
  }

  it should "find 3 summands" in {
    val input = List[Int](1721, 979, 366, 299, 675, 1456)

    val result = Main.findSummands(input, 2020, 3)

    result should be(List(979, 366, 675))
  }
}
