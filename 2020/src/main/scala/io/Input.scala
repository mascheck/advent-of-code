package io

import scala.io.Source

object Input {

  def read(file: String): List[String] = {
    val source = Source.fromInputStream(getClass.getResourceAsStream(file))
    val input = source.getLines().toList
    source.close()
    input
  }
}
