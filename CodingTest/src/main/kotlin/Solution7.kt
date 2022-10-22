// https://www.acmicpc.net/problem/2638

class Solution7(val n: Int, val m: Int) {
    val graph = Array(n) { mutableListOf<Int>() }
    var temp = Array(n) { Array(m) { 0 } }
    val dx = listOf(-1,1,0,0)
    val dy = listOf(0,0,1,-1)
    fun dfs(x: Int, y: Int) {
        temp[x][y] = -1
        repeat(4) {
            val nx = x + dx[it]
            val ny = y + dy[it]
            if (nx < 0 || nx  >= n || ny < 0 || ny >= m) return@repeat
            if (graph[nx][ny] == 0 && temp[nx][ny] == 0) {
                dfs(nx, ny)
            }
        }
    }
    fun solution() {
        repeat(n) {
            graph[it] = readln().trim().split(" ").map { it.toInt() } as MutableList<Int>
        }
        var result = 0
        while (true) {
            var empty = 0
            temp = Array(n) { Array(m) { 0 } }
            dfs(0, 0)
            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (graph[i][j] == 0) {
                        empty++
                    }
                    if (graph[i][j] == 1) {
                        var count = 0
                        for (k in 0..3) {
                            val nx = i + dx[k]
                            val ny = j + dy[k]
                            if (temp[nx][ny] == -1) {
                                count++
                            }
                            if (count >= 2) break
                        }
                        if (count == 2) {
                            graph[i][j] = 0
                        }
                    }
                }
            }
            if (empty == n*m) break
            result++
        }
        println(result)
    }
}

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    Solution7(n, m).solution()
}