package day05

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.io.{BufferedSource, Source}



object Main {

  val rowMax = 127
  val columnMax = 7

  def main(args: Array[String]): Unit = {
    val t1 = System.currentTimeMillis()
    val bufferedSource = Source.fromInputStream(getClass.getResourceAsStream("/day05.txt"))
    val result1 = getMaxSeatId(bufferedSource)
    bufferedSource.close()
    val t2 = System.currentTimeMillis()

    println(f"Result: $result1")
    println(f"Took: ${t2-t1}ms")

    val t3 = System.currentTimeMillis()
    val bufferedSource2 = Source.fromInputStream(getClass.getResourceAsStream("/day05.txt"))
    val result2 = findMySeat(bufferedSource2)
    bufferedSource2.close()
    val t4 = System.currentTimeMillis()

    println(f"Result: $result2")
    println(f"Took: ${t4-t3}ms")
  }

  def getMaxSeatId(source: BufferedSource): Int = {
    val seatIDs = new ListBuffer[Int]()
    for (line <- source.getLines()) {
      val seat = getSeat(line)
      val seatID = seat._1 * 8 + seat._2
      seatIDs += seatID
    }
    seatIDs.max
  }

  def getSeat(location: String) : (Int, Int) = {
    val rowCoordinate = location.substring(0, 7)
    val columnCoordinate = location.substring(7, 10)

    val rowPosition = calculatePosition(0, rowMax, rowCoordinate)
    val columnPosition = calculatePosition(0, columnMax, columnCoordinate)
    (rowPosition, columnPosition)
  }

  @tailrec
  def calculatePosition(min: Int, max: Int, s: String): Int = {
    s.charAt(0) match {
      case 'F' | 'L' =>
        if ((max - min) == 1) return min
        val newMax = max - ((max - min) / 2.0).ceil.toInt
        calculatePosition(min, newMax.toInt, s.substring(1))
      case 'B' | 'R' =>
        if ((max - min) == 1) return max
        val newMin = ((max - min) / 2 +1) + min
        calculatePosition(newMin, max, s.substring(1))
    }
  }

  def findMySeat(source: BufferedSource): Int = {
    val seatIDs = new ListBuffer[Int]()
    for (line <- source.getLines()) {
      val seat = getSeat(line)
      val seatID = seat._1 * 8 + seat._2
      seatIDs += seatID
    }

    val keys = seatIDs.sorted
    for (i <- 1 to keys.length) {
      val pre = keys(i-1)
      val value = keys(i)

      if ((value - pre) == 2) return value-1
    }
    0
  }
}

