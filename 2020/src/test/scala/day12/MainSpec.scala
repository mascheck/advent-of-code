package day12

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatest.prop.TableDrivenPropertyChecks

class MainSpec extends AnyFlatSpec with TableDrivenPropertyChecks with should.Matchers {
  "parse" should "parse all fields" in {
    val entries = Table(
      ("input", "expected"),
      ((new Ship(0, 0, 90), new Instruction('E', 10)), new Ship(10, 0, 90)),
      ((new Ship(-8, 0, 90), new Instruction('E', 10)), new Ship(2, 0, 90)),
      ((new Ship(0, 0, 90), new Instruction('S', 10)), new Ship(0, -10, 90)),
      ((new Ship(0, 8, 90), new Instruction('S', 10)), new Ship(0, -2, 90)),
      ((new Ship(0, 0, 90), new Instruction('W', 10)), new Ship(-10, 0, 90)),
      ((new Ship(8, 0, 90), new Instruction('W', 10)), new Ship(-2, 0, 90)),
      ((new Ship(0, 0, 90), new Instruction('N', 10)), new Ship(0, 10, 90)),
      ((new Ship(0, -8, 90), new Instruction('N', 10)), new Ship(0, 2, 90)),
      ((new Ship(0, 0, 0), new Instruction('F', 10)), new Ship(0, 10, 0)),
      ((new Ship(0, 0, 90), new Instruction('F', 10)), new Ship(10, 0, 90)),
      ((new Ship(0, 0, 180), new Instruction('F', 10)), new Ship(0, -10, 180)),
      ((new Ship(0, 0, 270), new Instruction('F', 10)), new Ship(-10, 0, 270)),
      ((new Ship(0, 0, 0), new Instruction('R', 90)), new Ship(0, 0, 90)),
      ((new Ship(0, 0, 90), new Instruction('R', 90)), new Ship(0, 0, 180)),
      ((new Ship(0, 0, 180), new Instruction('R', 90)), new Ship(0, 0, 270)),
      ((new Ship(0, 0, 270), new Instruction('R', 90)), new Ship(0, 0, 0)),
      ((new Ship(0, 0, 0), new Instruction('L', 90)), new Ship(0, 0, 270)),
      ((new Ship(0, 0, 90), new Instruction('L', 90)), new Ship(0, 0, 0)),
      ((new Ship(0, 0, 180), new Instruction('L', 90)), new Ship(0, 0, 90)),
      ((new Ship(0, 0, 270), new Instruction('L', 90)), new Ship(0, 0, 180)),
    )

    forAll(entries) { (input: (Ship, Instruction), expected: Ship) =>
      input._1.instruct(input._2) should be(expected)
    }
  }

  "parseWithWayPoint" should "parse all fields" in {
    val entries = Table(
      ("input", "expected"),
      (
        (new ShipWithWaypoint(0, 0, (10, 1)), new Instruction('E', 10)),
        new ShipWithWaypoint(10, 0, (20, 1)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (-8, 1)), new Instruction('E', 10)),
        new ShipWithWaypoint(2, 0, (2, 1)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (10, 1)), new Instruction('S', 10)),
        new ShipWithWaypoint(0, -10, (10, -9)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (10, -1)), new Instruction('S', 10)),
        new ShipWithWaypoint(0, -2, (10, -11)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (0, 1)), new Instruction('W', 10)),
        new ShipWithWaypoint(-10, 0, (-10, 1)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (8, 1)), new Instruction('W', 10)),
        new ShipWithWaypoint(-2, 0, (-2, 1)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (10, 1)), new Instruction('N', 10)),
        new ShipWithWaypoint(0, 10, (10, 11)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (10, -8)), new Instruction('N', 10)),
        new ShipWithWaypoint(0, 2, (10, 2)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (10, 1)), new Instruction('F', 10)),
        new ShipWithWaypoint(100, 10, (10, 1)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (1, 10)), new Instruction('F', 10)),
        new ShipWithWaypoint(10, 100, (1, 10)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (-10, 1)), new Instruction('F', 10)),
        new ShipWithWaypoint(-100, 10, (-10, 1)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (1, -10)), new Instruction('F', 10)),
        new ShipWithWaypoint(10, -100, (1, -10)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (-10, -10)), new Instruction('F', 10)),
        new ShipWithWaypoint(-100, -100, (-10, -10)),
      ),
      (
        (new ShipWithWaypoint(27, 27, (-10, -10)), new Instruction('F', 10)),
        new ShipWithWaypoint(-73, -73, (-10, -10)),
      ),
      (
        (new ShipWithWaypoint(-27, -27, (10, 10)), new Instruction('F', 10)),
        new ShipWithWaypoint(73, 73, (10, 10)),
      ),
      (
        (new ShipWithWaypoint(27, 27, (10, 10)), new Instruction('F', 10)),
        new ShipWithWaypoint(127, 127, (10, 10)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (10, 1)), new Instruction('R', 90)),
        new ShipWithWaypoint(0, 0, (1, -10)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (1, -10)), new Instruction('R', 90)),
        new ShipWithWaypoint(0, 0, (-10, -1)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (10, 1)), new Instruction('R', 90)),
        new ShipWithWaypoint(0, 0, (10, 1)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (10, 1)), new Instruction('R', 90)),
        new ShipWithWaypoint(0, 0, (10, 1)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (10, 1)), new Instruction('L', 90)),
        new ShipWithWaypoint(0, 0, (10, 1)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (10, 1)), new Instruction('L', 90)),
        new ShipWithWaypoint(0, 0, (10, 1)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (10, 1)), new Instruction('L', 90)),
        new ShipWithWaypoint(0, 0, (10, 1)),
      ),
      (
        (new ShipWithWaypoint(0, 0, (10, 1)), new Instruction('L', 90)),
        new ShipWithWaypoint(0, 0, (10, 1)),
      ),
    )

    forAll(entries) { (input: (ShipWithWaypoint, Instruction), expected: ShipWithWaypoint) =>
      input._1.instruct(input._2) should be(expected)
    }
  }
}
