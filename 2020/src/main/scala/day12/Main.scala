package day12

import io.Input

case class Instruction(command: Char, units: Int)

class Ship {
  var x = 0
  var y = 0
  var direction = 90

  def this(x: Int, y: Int, direction: Int) = {
    this()
    this.x = x
    this.y = y
    this.direction = direction
  }

  def instruct(instruction: Instruction): Ship = {
    if (instruction.command == 'E' | instruction.command == 'W') wentX(instruction)
    else if (instruction.command == 'N' | instruction.command == 'S') wentY(instruction)
    else if (instruction.command == 'F') forward(instruction)
    else
      changeDirection(instruction)
    this
  }

  def wentX(instruction: Instruction): Unit =
    if (instruction.command == 'E') x += instruction.units else x -= instruction.units

  def wentY(instruction: Instruction): Unit =
    if (instruction.command == 'N') y += instruction.units else y -= instruction.units

  def changeDirection(instruction: Instruction): Unit = {
    if (instruction.command == 'L') direction -= instruction.units
    else direction += instruction.units
    direction = (direction + 360) % 360
  }

  def forward(instruction: Instruction) =
    if (direction == 90) wentX(new Instruction('E', instruction.units))
    else if (direction == 270) wentX(new Instruction('W', instruction.units))
    else if (direction == 0) wentY(new Instruction('N', instruction.units))
    else
      wentY(new Instruction('S', instruction.units))

  def getX(): Integer =
    if (x < 0) x * -1 else x

  def getY(): Integer =
    if (y < 0) y * -1 else y

  override def equals(obj: Any): Boolean = {
    obj match {
      case otherShip: Ship =>
        return x == otherShip.x && y == otherShip.y && direction == otherShip.direction
      case _ =>
    }
    false
  }

  override def toString: String = f"x: $x y: $y direction: $direction"
}

class ShipWithWaypoint {
  var x = 0
  var y = 0
  var wayPoint = (10, 1)

  def this(x: Int, y: Int, wayPoint: (Int, Int)) = {
    this()
    this.x = x
    this.y = y
    this.wayPoint = wayPoint
  }

  def instruct(instruction: Instruction): ShipWithWaypoint = {
    if (instruction.command == 'E' | instruction.command == 'W') moveWayPointX(instruction)
    else if (instruction.command == 'N' | instruction.command == 'S') moveWayPointY(instruction)
    else if (instruction.command == 'F') forward(instruction)
    else
      changeDirection(instruction)
    this
  }

  def moveWayPointX(instruction: Instruction): Unit =
    if (instruction.command == 'E') wayPoint = (wayPoint._1 + instruction.units, wayPoint._2)
    else wayPoint = (wayPoint._1 - instruction.units, wayPoint._2)

  def moveWayPointY(instruction: Instruction): Unit =
    if (instruction.command == 'N') wayPoint = (wayPoint._1, wayPoint._2 + instruction.units)
    else wayPoint = (wayPoint._1, wayPoint._2 - instruction.units)

  def changeDirection(instruction: Instruction): Unit =
    for (i <- 1 to instruction.units / 90)
      if (instruction.command == 'L') wayPoint = (wayPoint._2 * -1, wayPoint._1)
      else wayPoint = (wayPoint._2, wayPoint._1 * -1)

  def forward(instruction: Instruction) = {
    x += wayPoint._1 * instruction.units
    y += wayPoint._2 * instruction.units
  }

  def getX(): Integer =
    if (x < 0) x * -1 else x

  def getY(): Integer =
    if (y < 0) y * -1 else y

  override def equals(obj: Any): Boolean = {
    obj match {
      case otherShip: ShipWithWaypoint =>
        return x == otherShip.x && y == otherShip.y && wayPoint == otherShip.wayPoint
      case _ =>
    }
    false
  }

  override def toString: String = f"x: $x y: $y wayPoint: $wayPoint"
}

object Main {

  def task1(input: List[Instruction]): (Int, Int) = {
    var shipState = new Ship(0, 0, 90)
    for (instruction <- input) shipState.instruct(instruction)
    (shipState.getX(), shipState.getY)
  }

  def task2(input: List[Instruction]): (Int, Int) = {
    var shipState = new ShipWithWaypoint(0, 0, (10, 1))
    for (instruction <- input) shipState.instruct(instruction)
    (shipState.getX(), shipState.getY)
  }

  def parse(input: String): Instruction = {
    val command = input.charAt(0)
    val units = Integer.valueOf(input.substring(1))
    new Instruction(command, units)
  }

  def main(args: Array[String]): Unit = {
    val input = Input.read("/day12.txt").map(parse)
    val result1 = task1(input)
    println(result1)
    println(result1._1 + result1._2)
    val result2 = task2(input)
    println(result2)
    println(result2._1 + result2._2)
  }
}
