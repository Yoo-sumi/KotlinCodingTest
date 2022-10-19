// https://www.acmicpc.net/problem/1743

class Solution12(val n: Int, val m: Int, val k: Int) {
    val graph = Array(n) { Array(m) { 0 } }
    val temp = Array(n) { Array(m) { 0 } }
    val dx = listOf(-1,1,0,0)
    val dy = listOf(0,0,-1,1)
    fun bfs(x: Int, y: Int, count: Int): Int {
        temp[x][y] = count
        val q = ArrayDeque<Pair<Int, Int>>()
        q.add(Pair(x, y))
        var total = 0

        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            total++
            for (i in 0..3) {
                val nx = now.first + dx[i]
                val ny = now.second + dy[i]
                if (nx < 0 || nx >= n || ny < 0 || ny >=m) continue
                if (temp[nx][ny]==0 && graph[nx][ny]==1) {
                    temp[nx][ny] = count
                    q.add(Pair(nx,ny))
                }
            }
        }
        return total
    }
    fun solution() {
        repeat(k) {
            val (x, y) = readln().split(" ").map { it.toInt() - 1 }
            graph[x][y] = 1
        }
        var count = 1
        var result = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (temp[i][j] == 0 && graph[i][j]==1) {
                    result = Math.max(bfs(i,j,count++), result)
                }
            }
        }
        println(result)
    }
}

fun main() {
    val (n, m, k) = readln().split(" ").map { it.toInt() }
    Solution12(n, m, k).solution()
}