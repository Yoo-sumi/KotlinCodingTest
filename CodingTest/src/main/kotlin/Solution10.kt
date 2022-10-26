import kotlin.math.abs

class Solution10(val r: Int, val c: Int) {
    val graph = Array(r) { Array(c) { '.' } }
    val dx = listOf(1, 1, 1, 0, 0, 0, -1, -1, -1)
    val dy = listOf(-1, 0, 1, -1, 0, 1, -1, 0, 1)
    fun solution() {
        var me = Pointt(0, 0)
        var robots = hashMapOf<Pointt, Int>()
        repeat(r) {
            val li = readln().trim()
            li.forEachIndexed { index, s ->
                graph[it][index] = li.get(index)
                if (s == 'I') {
                    me = Pointt(it, index)
                } else if (s == 'R'){
                    if (robots[Pointt(it, index)] == null) {
                        robots[Pointt(it, index)] = 1
                    } else {
                        robots[Pointt(it, index)] = robots[Pointt(it, index)]!! + 1
                    }
                }
            }
        }
        val direction = readln().map { it.digitToInt() - 1 }
        var count = 0
        for (i in direction.indices) {
            count++
            // 종수 이동
            var nx = me.x + dx[direction[i]]
            var ny = me.y + dy[direction[i]]
            if (nx in 0 until r && ny in 0 until c ) {
                me.x = nx
                me.y = ny
                if (Pointt(nx, ny) in robots) {
                    println("kraj ${count}")
                    return
                }
            }
            // 미친 아두이노
            val newRobots = hashMapOf<Pointt, Int>()
            for (key in robots.keys) {
                var distance = r * c * 2
                var robotDirection = 0
                for (j in 0..8) {
                    nx = key.x + dx[j]
                    ny = key.y + dy[j]
                    if (distance > abs(me.x - nx) + abs(me.y - ny)) {
                        robotDirection = j
                        distance = abs(me.x - nx) + abs(me.y - ny)
                    }
                }
                nx = key.x + dx[robotDirection]
                ny = key.y + dy[robotDirection]
                if (nx in 0 until r && ny in 0 until c ) {
                    if (newRobots[Pointt(nx, ny)]==null) {
                        newRobots[Pointt(nx, ny)] = 1
                    } else {
                        newRobots[Pointt(nx, ny)] = newRobots[Pointt(nx, ny)]!! + 1
                    }
                    if (nx == me.x && ny == me.y) {
                        println("kraj ${count}")
                        return
                    }
                }
            }
            val rotate = newRobots.map { it.key }
            for (key in rotate) {
                if (newRobots[key]!! > 1) {
                    newRobots.remove(key)
                }
            }
            robots = newRobots
        }
        for (ii in 0 until r) {
            for (jj in 0 until c) {
                if (me.x == ii && me.y == jj) {
                    print("I ")
                } else if (Pointt(ii, jj) in robots) {
                    print("R ")
                } else {
                    print(". ")
                }
            }
            println()
        }
    }
}
data class Pointt(
    var x: Int,
    var y: Int
)
fun main() {
    val (r, c) = readln().trim().split(" ").map { it.toInt() }
    Solution10(r, c).solution()
}