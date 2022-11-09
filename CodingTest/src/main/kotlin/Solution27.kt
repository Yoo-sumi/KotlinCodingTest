class Solution27(val n: Int) {
    val color = hashMapOf<Int, Int>()
    val balls = mutableListOf<Ball>()
    fun solution() {
        repeat(n) {
            val (a, b) = readln().trim().split(" ").map { it.toInt() }
            balls.add(Ball(a-1,b,it))
            color[a-1] = 0
        }
        balls.sortWith(compareBy ({ it.size }, {it.color}))
        val result = Array(n) { 0 }
        var total = 0
        var j = 0
        for (i in balls.indices) {
            val ball = balls[i]
            while (ball.size > balls[j].size) {
                total += balls[j].size
                j++
            }
            result[ball.index] = total - color[ball.color]!!
            color[ball.color] = color[ball.color]!! + ball.size
        }
        result.forEach {
            println(it)
        }
    }
}

data class Ball(
    val color: Int,
    val size: Int,
    val index: Int
)

fun main() {
    Solution27(readln().trim().toInt()).solution()
}