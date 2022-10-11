package main.kotlin

class Solution3(val t: Int) {
    val dx = listOf(-1, 1, 0, 0)
    val dy = listOf(0, 0, -1, 1)
    fun solution(): MutableList<Int> {
        val result = mutableListOf<Int>()
        repeat(t) {
            val n = readln().trim().toInt()
            val graph = Array(n) { Array(n) { 0 } }
            val visited = Array(n) { Array(n) { INF } }
            repeat(n) {
                graph[it] = readln().split(" ").map { num -> num.toInt() }.toTypedArray()
            }
            val q = ArrayDeque<List<Int>>()
            q.add(listOf(0,0))
            visited[0][0] = graph[0][0]
            while (q.isNotEmpty()) {
                val (x, y) = q.removeFirst()
                if (x == n-1 && y == n-1) {
                    continue
                }
                for (i in 0..3) {
                    val nx = x + dx[i]
                    val ny = y + dy[i]
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue
                    if (visited[nx][ny] > visited[x][y] + graph[nx][ny]) {
                        visited[nx][ny] = visited[x][y] + graph[nx][ny]
                        q.add(listOf(nx, ny))
                    }
                }

            }
            result.add(visited[n-1][n-1])
        }
        return result
    }
    companion object {
        const val INF = 1e9.toInt()
    }
}

fun main() {
    val result = Solution3(readln().trim().toInt()).solution()
    result.forEach { println(it) }
}

/*
3
3
5 5 4
3 9 1
3 2 7
5
3 7 2 0 1
2 8 0 9 1
1 2 1 8 1
9 8 9 2 0
3 6 5 1 5
7
9 0 5 1 1 5 3
4 1 2 1 6 5 3
0 7 6 1 6 8 5
1 1 7 8 3 2 3
9 4 0 7 6 4 1
5 8 3 2 4 8 3
7 4 8 4 8 3 4
*/