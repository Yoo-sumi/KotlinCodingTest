// https://www.acmicpc.net/problem/1113

class Solution13(val n: Int, val m: Int) {
    val graph = Array(n) { listOf<Int>() }
    var result = 0
    var visited = Array(n) { Array(m) { 0 } }
    val dx = listOf(-1,1,0,0)
    val dy = listOf(0,0,-1,1)
    fun bfs(x: Int, y: Int, k: Int) {
        val q = mutableListOf<Temp>()
        q.add(Temp(x, y))
        visited[x][y] = 1
        var flag = true
        var count = 1
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            repeat(4) {
                val nx = now.x + dx[it]
                val ny = now.y + dy[it]
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    flag = false
                    return@repeat
                }
                if (graph[nx][ny] < k && visited[nx][ny] == 0) {
                    q.add(Temp(nx, ny))
                    visited[nx][ny] = 1
                    count++
                }
            }
        }
        if (flag) {
            result += count
        }
    }

    fun solution() {
        repeat(n) {
            graph[it] = readln().map { it.digitToInt() }
        }
        for (k in 2 until 10 ) {
            visited = Array(n) { Array(m) { 0 } }
            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (visited[i][j] == 0 && graph[i][j] < k) {
                        bfs(i, j, k)
                    }
                }
            }
        }
        println(result)
    }
}
data class Temp(
    val x: Int,
    val y: Int
)

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    Solution13(n, m).solution()
}

/*
수영장의 높이는 1~9까지다. 1부터 9까지 차례대로 물을 채워서 가능한 곳만 카운트 해준다.
bfs로 물을 채우기로 한 벽의 높이(k)보다 작거나 같다면 그쪽으로 이동할 수 있는 것이다. 쭉 이동하면서 카운트 한다.
큐가 비면, 카운트 했던걸 result에 더해주면 되는데, 이동 도중에 범위에 벗어났다면 벽에 둘러싸여지지
않는 공간이기 때문에 flag로 체크해서 카운트를 걸러주었다. 이게 핵심이다.
*/
