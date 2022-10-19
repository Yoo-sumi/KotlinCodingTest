// https://www.acmicpc.net/problem/1303

class Solution10(val n: Int, val m: Int) {
    val graph = Array(m) { listOf<Char>() }
    val temp = Array(m) { Array(n) { 0 } }
    val dx = listOf(-1,1,0,0)
    val dy = listOf(0,0,-1,1)
    fun bfs(x: Int, y: Int, count: Int): Int {
        val type = graph[x][y]
        temp[x][y] = count
        val q = ArrayDeque<Pair<Int,Int>>()
        q.add(Pair(x,y))
        var total = 0
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            total++
            for (i in 0..3) {
                val nx = now.first + dx[i]
                val ny = now.second + dy[i]
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue
                if (graph[nx][ny] == type && temp[nx][ny] == 0) {
                    q.add(Pair(nx,ny))
                    temp[nx][ny] = count
                }
            }
        }
        return Math.pow(total.toDouble(), 2.0).toInt()
    }
    fun solution() {
        repeat(m) {
            val li = readln().toList()
            graph[it] = li
        }
        var count = 1
        var xx = 0
        var yy = 0
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (temp[i][j] == 0) {
                    val re = bfs(i, j, count)
                    if (graph[i][j] == 'W') {
                        xx += re
                    } else if (graph[i][j] == 'B') {
                        yy += re
                    }
                    count++
                }
            }
        }
        println("$xx $yy")
    }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    Solution10(n, m).solution()
}