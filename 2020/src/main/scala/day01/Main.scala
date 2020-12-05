package day01

import scala.io.Source


object Main {
  def main(args: Array[String]): Unit = {
    val input = readInput()

    val t1 = System.currentTimeMillis()
    val result1 = find2020Pair(input)
    val t2 = System.currentTimeMillis()

    println(f"Result: $result1 = ${result1._1 * result1._2}")
    println(f"Took: ${t2-t1}ms")

    val t3 = System.currentTimeMillis()
    val result2 = find2020Triple(input)
    val t4 = System.currentTimeMillis()

    println(f"Result: $result2 = ${result2._1 * result2._2 * result2._3}")
    println(f"Took: ${t4-t3}ms")
  }

  def readInput(): List[Int] = {
    val bufferedSource = Source.fromInputStream(getClass.getResourceAsStream("/day01.txt"))
    val input = bufferedSource.getLines().toList.map(_.toInt)
    bufferedSource.close()
    input
  }

  def find2020Pair(values: List[Int]): (Int, Int) = {
    values.foreach(i => {
      values.foreach(j => {
        if (i + j == 2020) return (i,j)
      })
    })
    (0,0)
  }
  def find2020Triple(values: List[Int]): (Int, Int, Int) = {
    values.foreach(i => {
      values.foreach(j => {
        values.foreach(k => {
          if (i + j + k == 2020) return (i,j, k)
        })
      })
    })
    (0,0, 0)
  }
}

