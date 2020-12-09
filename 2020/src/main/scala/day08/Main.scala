package day08

import io.Input

import java.security.InvalidAlgorithmParameterException
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ArrayBuffer.from

object Main {

  def main(args: Array[String]): Unit = {
    val input = Input.read("/day08.txt")
    val result1 = task1(input)
    val result2 = task2(input)
    println(result1)
    println(result2)
  }

  def task1(input: List[String]): Int = {
    val code = input.map((_, mutable.ListBuffer[Int]()))
    var acc = 0
    var line = 0
    var step = 1

    while (!code.exists(_._2.length == 2)) {
      if (code(line)._2.length == 1) return acc
      code(line)._2.addOne(step)
      val result = execute(code(line)._1, acc, line)
      acc = result._1
      line = result._2
      step += 1
    }
    acc
  }

  def task2(input: List[String]): Int = {

    var acc = 0
    var line = 0
    var step = 1
    var lastChangedLine = 0
    var code = input.map((_, mutable.ListBuffer[Int]()))
    while (true) {
      if (code(line)._2.length == 1) {
        println("code repeated")
        acc = 0
        line = 0
        step = 1
        val change = changeCode(input, lastChangedLine)
        code = change._1.map((_, mutable.ListBuffer[Int]()))
        lastChangedLine = change._2
      }
      code(line)._2.addOne(step)
      val result = execute(code(line)._1, acc, line)
      acc = result._1
      line = result._2
      step += 1
      if (line == code.length) return acc
    }
    acc
  }

  def changeCode(
      code: List[String],
      lastLineChanged: Int,
  ): (List[String], Int) = {
    val mutableCode = code.to(ArrayBuffer)

    for (i <- lastLineChanged + 1 to code.length) {
      val op = code(i).split(" ")
      op(0) match {
        case "nop" =>
          println(f"changing $i to jmp")
          mutableCode(i) = "jmp " + op(1)
          return (mutableCode.toList, i)
        case "jmp" =>
          println(f"changing $i to nop")
          mutableCode(i) = "nop " + op(1)
          return (mutableCode.toList, i)
        case _ =>
      }
    }
    throw new Exception("Wait a little")
  }

  def execute(code: String, acc: Int, line: Int): (Int, Int) = {
    val command = code.split(" ")
    command(0) match {
      case "nop" =>
        (acc, line + 1)
      case "acc" =>
        (acc + command(1).toInt, line + 1)
      case "jmp" =>
        (acc, line + command(1).toInt)
    }
  }
}
