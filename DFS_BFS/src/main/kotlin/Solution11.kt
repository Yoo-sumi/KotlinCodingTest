// https://www.acmicpc.net/problem/2178

class Solution11(val n: Int, val m: Int) {
    val graph = Array(n) { listOf<Int>() }
    val temp = Array(n) { Array(m) { INF } }
    val dx = listOf(-1,1,0,0)
    val dy = listOf(0,0,-1,1)
    fun solution() {
        repeat(n) {
            graph[it] = readln().toList().map { it.digitToInt() }
        }
        temp[0][0] = 1
        val q = ArrayDeque<Pair<Int, Int>>()
        q.add(Pair(0,0))
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            if (now.first == n-1 && now.second == m-1) continue
            for (i in 0..3) {
                val nx = now.first + dx[i]
                val ny = now.second + dy[i]
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue
                if (temp[now.first][now.second] + 1 < temp[nx][ny] && graph[nx][ny] == 1) {
                    temp[nx][ny] = temp[now.first][now.second] + 1
                    q.add(Pair(nx, ny))
                }
            }
        }
        println(temp[n-1][m-1])
    }
    companion object {
        const val INF = 1e9.toInt()
    }
}

fun main() {
    val (n, m)  = readln().split(" ").map { it.toInt() }
    Solution11(n, m).solution()
}