package day04

import scala.collection.mutable.ListBuffer
import scala.io.{BufferedSource, Source}


case class Passport(byr: Option[String], iyr: Option[String], eyr: Option[String], hgt: Option[String],
                    hcl: Option[String], ecl: Option[String], pid: Option[String], cid: Option[String]){
  def isValid: Boolean = {false}
}

object Main {
  val requiredFields: List[String] = List[String]("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
  val allowedEcl: List[String] = List[String]("amb", "blu", "brn", "gry", "grn", "hzl", "oth" )

  def main(args: Array[String]): Unit = {
    val t1 = System.currentTimeMillis()
    val bufferedSource = Source.fromInputStream(getClass.getResourceAsStream("/day04.txt"))
    val result1 = countValidPassports(bufferedSource)
    bufferedSource.close()
    val t2 = System.currentTimeMillis()

    println(f"Result: $result1")
    println(f"Took: ${t2-t1}ms")

//    val t3 = System.currentTimeMillis()
//    val result2 = find2020Triple(input)
//    val t4 = System.currentTimeMillis()
//
//    println(f"Result: $result2 = ${result2._1 * result2._2 * result2._3}")
//    println(f"Took: ${t4-t3}ms")
  }

  def countValidPassports(source: BufferedSource): Int = {
    val lines = new ListBuffer[String]()
    var validPassports = 0
    for (line <- source.getLines()) {
      if (line.isEmpty) {
        val map = parsePassport(lines.toList)
        lines.clear()
        if (isPassportValid(map)) validPassports += 1
      }
      else {
        lines += line
      }
    }
    val map = parsePassport(lines.toList)
    lines.clear()
    if (isPassportValid(map)) validPassports += 1
    validPassports
  }

  def parsePassport(input: List[String]): Map[String, String] = {
    val s = input.mkString(" ")
    val valuePairs = s.split(" ")
    var map = scala.collection.mutable.Map[String, String]()
    valuePairs.foreach(pair => {
      val keyVal = pair.split(":")
      map.addOne(keyVal(0), keyVal(1))
    })
    map.toMap
  }

  def isPassportValid(map: Map[String, String]): Boolean = {
    for (key <- requiredFields) {
      if (!map.contains(key)) {
        return false
      }
      key match {
        case "byr" =>
          try {
            val byr = map(key).toInt
            if (byr < 1920 || byr > 2002) return false
          } catch {
            case _: Throwable => return false
          }
        case "iyr" =>
          try {
            val value = map(key).toInt
            if (value < 2010 || value > 2020) return false
          } catch {
            case _: Throwable => return false
          }
        case "eyr" =>
          try {
            val value = map(key).toInt
            if (value < 2020 || value > 2030) return false
          } catch {
            case _: Throwable => return false
          }
        case "hgt" =>
          try {
            val value = map(key)
            val suffix = value.substring(value.length-2, value.length)
            val amount = value.substring(0, value.length-2).toInt
            suffix match {
              case "cm" =>
                if (amount < 150 || amount > 193 ) return false
              case "in" =>
                if (amount < 59 || amount > 76 ) return false
              case _ => return false
            }
          } catch {
            case _ : Throwable => return false
          }
        case "hcl" =>
          val value = map(key)
          if (!value.matches("#[0-9a-f]{6}")) return false
        case "ecl" =>
          val value = map(key)
          if (!allowedEcl.contains(value)) return false
        case "pid" =>
          val value = map(key)
          if (value.length != 9 || value.toIntOption.isEmpty) return false
      }
    }
    true
  }
}

