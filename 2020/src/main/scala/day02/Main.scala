package day02

import io.Input

object Main {

  def main(args: Array[String]): Unit = {
    val input = Input.read("/day02.txt")

    val t1 = System.currentTimeMillis()
    val result1 = countValidPasswords(input)
    val t2 = System.currentTimeMillis()

    println(f"Result: $result1")
    println(f"Took: ${t2 - t1}ms")

    val t3 = System.currentTimeMillis()
    val result2 = countValidPasswords2(input)
    val t4 = System.currentTimeMillis()

    println(f"Result: $result2")
    println(f"Took: ${t4 - t3}ms")
  }

  def countValidPasswords(passwords: List[String]): Int = {
    var valid = 0
    passwords.foreach { p =>
      val s = splitLine(p)
      val c = s._4.count(p => p == s._3)
      if (c <= s._2 && c >= s._1) valid += 1
    }
    valid
  }

  def countValidPasswords2(passwords: List[String]): Int = {
    var valid = 0
    passwords.foreach { p =>
      val s = splitLine(p)
      val pw = s._4
      val c1 = pw.charAt(s._1 - 1)
      val c2 = pw.charAt(s._2 - 1)
      var i = 0
      if (c1 == s._3) i += 1
      if (c2 == s._3) i += 1
      if (i == 1) valid += 1
    }

    valid
  }

  def splitLine(line: String): (Int, Int, Char, String) = {
    val s1 = line.replace(":", "")
    val s2 = s1.replace("-", " ")
    val a = s2.split(" ")
    (a(0).toInt, a(1).toInt, a(2).charAt(0), a(3))
  }
}
