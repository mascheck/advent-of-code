package day03

import io.Input
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatest.prop.TableDrivenPropertyChecks

class MainSpec extends AnyFlatSpec with should.Matchers with TableDrivenPropertyChecks {

  val map: List[String] = Input.read("/day03.txt")

  "countTreesOnSlope" should "count trees correctly" in {
    val entries = Table(
      ("input", "expected"),
      ((1, 1), 2),
      ((3, 1), 7),
      ((5, 1), 3),
      ((7, 1), 4),
      ((1, 2), 2),
    )
    forAll(entries) { (input: (Int, Int), expected: Int) =>
      val result = Main.countTreesOnSlope(map, input)
      result should be(expected)
    }
  }

}
