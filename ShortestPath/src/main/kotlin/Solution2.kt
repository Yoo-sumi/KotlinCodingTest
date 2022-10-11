package main.kotlin

class Solution2(val n: Int, val m: Int) {
    val graph = Array(n) { Array(n) { 0 } }
    fun solution(): Int {
        var answer = 0
        repeat(m) {
            val (a, b) = readln().split(" ").map { it.toInt() - 1 }
            graph[a][b] = -1
            graph[b][a] = 1
        }
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (graph[i][j] == -1) {
                    graph[j].forEachIndexed { index, it ->
                        if (it == -1) {
                            graph[i][index] = -1
                        }
                    }
                } else if (graph[i][j] == 1) {
                    graph[j].forEachIndexed { index, it ->
                        if (it == 1) {
                            graph[i][index] = 1
                        }
                    }
                }
            }
        }
        graph.forEach {
            if (it.filter { it == 0 }.size == 1) {
                answer++
            }
        }
        return answer
    }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val result = Solution2(n, m).solution()
    println(result)
}