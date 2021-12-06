fun main() {
    fun part1(input: List<String>): Int {
        fun String.toPoint(): Point {
            val s = this.split(" ")
            val direction = s[0]
            val distance = s[1].toInt()

            return when (direction) {
                "forward" -> Point(distance, 0)
                "up" -> Point(0, -distance)
                "down" -> Point(0, distance)
                else -> Point(0,0)
            }
        }

        val p = input.map { it.toPoint() }.sum()
        return p.x * p.y
    }

    fun part2(input: List<String>): Int {
        class Submarine {
            var position: Point = Point(0,0)
            var aim: Int = 0

            fun up(amount: Int) {
                this.aim -= amount
            }
            fun down(amount: Int) {
                this.aim += amount
            }
            fun forward(amount: Int) {
                this.position = Point(this.position.x + amount, this.position.y + this.aim * amount)
            }
            fun move(command: String) {
                val s = command.split(" ")
                val direction = s[0]
                val amount = s[1].toInt()

                when (direction) {
                    "forward" -> {
                        this.forward(amount)
                    }
                    "up" -> this.up(amount)
                    "down" -> this.down(amount)
                }
            }
        }

        val sub = Submarine()
        input.forEach{sub.move(it)}

       return sub.position.x * sub.position.y
    }

    val testInput = readInput("Day02_test")
    val input = readInput("Day02")

    println(part1(testInput))
    check(part1(testInput) == 150)
    println(part1(input))

    println(part2(testInput))
    check(part2(testInput) == 900)
    println(part2(input))
}

