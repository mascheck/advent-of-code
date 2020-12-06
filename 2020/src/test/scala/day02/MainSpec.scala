package day02

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatest.prop.TableDrivenPropertyChecks

class MainSpec extends AnyFlatSpec with TableDrivenPropertyChecks with should.Matchers {
  "parse" should "parse all fields" in {
    val entries = Table(
      ("input", "expected"),
      ("1-3 a: abcde", (1, 3, 'a', "abcde")),
      ("1-3 b: cdefg", (1, 3, 'b', "cdefg")),
      ("2-9 c: ccccccccc", (2, 9, 'c', "ccccccccc")),
      ("9-10 l: lllllllllk", (9, 10, 'l', "lllllllllk")),
    )

    forAll(entries) { (input: String, expected: (Int, Int, Char, String)) =>
      val result = Main.parse(input)
      result should be(expected)
    }
  }

  "isValidOld" should "validate correctly" in {
    val entries = Table(
      ("input", "expected"),
      ((1, 3, 'a', "abcde"), true),
      ((1, 3, 'b', "cdefg"), false),
      ((2, 9, 'c', "ccccccccc"), true),
    )

    forAll(entries) { (input: (Int, Int, Char, String), expected: Boolean) =>
      val result = Main.isValidOld(input)
      result should be(expected)
    }
  }

  "isValidNew" should "validate correctly" in {
    val entries = Table(
      ("input", "expected"),
      ((1, 3, 'a', "abcde"), true),
      ((1, 3, 'b', "cdefg"), false),
      ((2, 9, 'c', "ccccccccc"), false),
    )

    forAll(entries) { (input: (Int, Int, Char, String), expected: Boolean) =>
      val result = Main.isValidNew(input)
      result should be(expected)
    }
  }
}
