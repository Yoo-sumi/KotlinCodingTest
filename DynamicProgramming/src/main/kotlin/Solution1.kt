// https://www.acmicpc.net/problem/1932

class Solution1(val n: Int) {
    fun solution() {
        val graph = Array(n) { MutableList(0) { 0 } }
        repeat(n) {
            val li = readln().split(" ").map { it.toInt() }
            li.forEach { num ->
                graph[it].add(num)
            }
        }
        for (i in 1 until n) {
            for (j in 0 until graph[i].size) {
                if (j == 0) {
                    graph[i][j] += graph[i-1][j]
                } else if (j == graph[i].lastIndex) {
                    graph[i][j] += graph[i-1][j-1]
                } else {
                    graph[i][j] += Math.max(graph[i-1][j-1], graph[i-1][j])
                }
            }
        }
        graph[n-1].sortDescending()
        println(graph[n - 1][0])
    }
}

fun main() {
    Solution1(readln().toInt()).solution()
}