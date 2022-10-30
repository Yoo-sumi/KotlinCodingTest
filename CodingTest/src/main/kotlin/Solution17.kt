// https://www.acmicpc.net/problem/2234

class Solution17(val n: Int, val m: Int) {

    val graph = Array(n) { Array(m) { mutableListOf<Int>() } }
    var temp = Array(n) { Array(m) { 0 } }
    val dx = listOf(0,-1,0,1)
    val dy = listOf(-1,0,1,0)

    fun transfer(num: Int): List<Int> {
        val result = Array(4) { 0 }
        var temp = num
        var index = 0
        while (temp > 0) {
            result[index++] = temp % 2
            temp /= 2
        }
        return result.toList()
    }

    fun switch(num: Int): Int {
        return when(num) {
            0 -> 2
            2 -> 0
            1 -> 3
            3 -> 1
            else -> 0
        }
    }

    fun bfs(x: Int, y: Int, number: Int): Int {
        temp[x][y] = number
        val q = ArrayDeque<Pointss>()
        q.add(Pointss(x, y))
        var count = 0
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            count++
            repeat(4) {
                if (graph[now.x][now.y][it] == 1) return@repeat
                val nx = now.x + dx[it]
                val ny = now.y + dy[it]
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) return@repeat
                if (temp[nx][ny] == 0 && graph[nx][ny][switch(it)] == 0) {
                    temp[nx][ny] = number
                    q.add(Pointss(nx, ny))
                }
            }
        }
        return count
    }

    fun solution() {
        repeat(n) {
            val li = readln().split(" ").map { it.toInt() }
            li.forEachIndexed { index, i ->
                graph[it][index] = transfer(i) as MutableList<Int>
            }
        }
        var number = 1
        val li = hashMapOf<Int, Int>()
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (temp[i][j] == 0) {
                    li[bfs(i, j, number)] = number
                    number++
                }
            }
        }
        println(number - 1)
        val sorted = li.keys.sortedDescending()
        println(sorted[0])
        var result = sorted[0]
        for (i in 0 until n) {
            for (j in 0 until m) {
                repeat(4) {
                    if (graph[i][j][it] == 1) {
                        number = 1
                        temp = Array(n) { Array(m) { 0 } }
                        val nx = i + dx[it]
                        val ny = j + dy[it]
                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) return@repeat
                        graph[nx][ny][switch(it)] = 0
                        graph[i][j][it] = 0
                        result = Math.max(bfs(i, j, number), result)
                        graph[i][j][it] = 1
                        graph[nx][ny][switch(it)] = 1
                    }
                }
            }
        }
        println(result)
    }
}

data class Pointss(
    val x: Int,
    val y: Int
)

fun main() {
    val (m, n) = readln().split(" ").map { it.toInt() }
    Solution17(n, m).solution()
}