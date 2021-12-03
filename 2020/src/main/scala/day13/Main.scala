package day13

import scala.util.control.Breaks.{break, breakable}

object Main {
  val departure = 1002462

  val linesInput =
    "37,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,601,x,x,x,x,x,x,x,x,x,x,x,19,x,x,x,x,17,x,x,x,x,x,23,x,x,x,x,x,29,x,443,x,x,x,x,x,x,x,x,x,x,x,x,13"
  val lines = Array(37, 41, 601, 19, 17, 23, 29, 443, 13)
  val deviations = Array(0, 27, 37, 49, 54, 60, 66, 68, 81)

  def task1(departure: Int, lines: Array[Int]): (Int, Int) = {
    var bestLine = Integer.MAX_VALUE
    var waitingTime = Integer.MAX_VALUE

    for (line <- lines) {
      var temp = (departure / line).toInt
      var actualWaitingTime = ((temp + 1) * line) - departure
      if (actualWaitingTime < waitingTime) {
        waitingTime = actualWaitingTime
        bestLine = line
      }
    }
    (bestLine, waitingTime)
  }

  def task1Marcel(departure: Int, lines: Array[Int]): (Int, Int) = {
    var bestLine = Integer.MAX_VALUE
    var waitingTime = Integer.MAX_VALUE

    for (line <- lines) {
      var temp = departure % line
      var actualWaitingTime = line - temp
      if (actualWaitingTime < waitingTime) {
        waitingTime = actualWaitingTime
        bestLine = line
      }
    }
    (bestLine, waitingTime)
  }

  def task2(input: String): Long = {
    val readLines = input.split(',')
    var iteration = 1L
    //val readLines = linesInput.split(',')
    while (true) {
      var firstArrival = readLines(0).toLong * iteration
      breakable {
        for (i <- 1 to readLines.length - 1)
          if (readLines(i) != "x") {
            var line = readLines(i).toInt
            if ((firstArrival + i) % line != 0) break
          }
        return firstArrival
      }
      if (iteration % 100000000000L == 0) println(f"Iteration: $iteration")
      iteration += 1
    }
    iteration
  }

  def main(args: Array[String]): Unit = {
    val result1 = task1(departure, lines)
    println(result1)
    println(result1._1 * result1._2)
    val result1Marcel = task1(departure, lines)
    println(result1Marcel)
    println(result1Marcel._1 * result1Marcel._2)
    val result2 = task2(linesInput)
    println(result2)

  }
}
