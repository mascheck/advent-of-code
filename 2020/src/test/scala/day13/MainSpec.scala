package day13

import day12.{Instruction, Ship}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatest.prop.TableDrivenPropertyChecks

class MainSpec extends AnyFlatSpec with TableDrivenPropertyChecks with should.Matchers {
  "parse" should "parse all fields" in {
    val entries = Table(
      ("input", "expected"),
      ("17,x,13,19", 3417),
      ("67,7,59,61", 754018),
      ("67,x,7,59,61", 779210),
      ("67,7,x,59,61", 1261476),
      ("1789,37,47,1889", 1202161486),
      ("7,13,x,x,59,x,31,19", 1068781),
    )

    forAll(entries)((input: String, expected: Int) => Main.task2(input) should be(expected))
  }
}
