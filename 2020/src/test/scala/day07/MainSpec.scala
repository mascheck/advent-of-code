package day07

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatest.prop.TableDrivenPropertyChecks

class MainSpec extends AnyFlatSpec with TableDrivenPropertyChecks with should.Matchers {

  "parse" should "parse all fields" in {
    val entries = Table(
      ("input", "expected"),
      (
        "light red bags contain 1 bright white bag, 2 muted yellow bags.",
        Bag("light red", List(("1", "bright white"), ("2", "muted yellow"))),
      ),
    )

    forAll(entries) { (input: String, expected: Bag) =>
      val result = Main.parse(input)
      result should be(expected)
    }
  }
}
