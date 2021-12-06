import java.io.File


data class Point(val x: Int, val y: Int)

operator fun Point.plus(b: Point): Point {
    return Point(x = x+b.x, y = y + b.y)
}

fun List<Point>.sum(): Point {
    var sum = Point(0,0)
    for (element in this) {
        sum += element
    }
    return sum
}

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()
