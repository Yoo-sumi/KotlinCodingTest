// https://www.acmicpc.net/problem/1976

class Solution14(val n: Int) {
    val graph = Array(n) { Array(n) { 1e9.toInt() } }
    fun solution() {
        val m = readln().toInt()
        repeat(n) {
            val li = readln().split(" ").map { it.toInt() } as MutableList<Int>
            li.forEachIndexed { index, i ->
                if (i == 1) {
                    graph[it][index] = 1
                }
            }
        }
        for (i in 0 until n) {
            graph[i][i] = 0
        }
        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j])
                }
            }
        }
        val route = readln().split(" ").map { it.toInt() - 1 }
        for (i in 0 until route.size - 1) {
            if (graph[route[i]][route[i+1]] == 1e9.toInt()) {
                println("NO")
                return
            }
        }
        println("YES")
    }
}

fun main() {
    Solution14(readln().toInt()).solution()
}

/*
플로이드 와샬 알고리즘을 이용해서 풀었다.
*/