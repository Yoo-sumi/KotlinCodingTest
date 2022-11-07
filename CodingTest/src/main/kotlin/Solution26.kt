// https://www.acmicpc.net/problem/3665

class Solution26(val t: Int) {
    fun solution() {
        repeat(t) {
            val n = readln().trim().toInt()
            val degree = Array(n) { 0 }
            val graph = Array(n) { Array(n) { 0 } }
            val li = readln().trim().split(" ").map { it.toInt() - 1 }
            for (i in 0 until n) {
                for (j in i+1 until n) {
                    degree[li[j]]++
                    graph[li[i]][li[j]] = 1
                    graph[li[j]][li[i]] = -1
                }
            }
            val m = readln().trim().toInt()
            repeat(m) {
                val (a, b) = readln().split(" ").map { it.toInt() - 1 }
                if (graph[a][b] == 1) {
                    graph[a][b] = -1
                    graph[b][a] = 1
                    degree[a]++
                    degree[b]--
                } else if (graph[a][b] == -1) {
                    graph[a][b] = 1
                    graph[b][a] = -1
                    degree[a]--
                    degree[b]++
                }
            }
            val q = ArrayDeque<Int>()
            val result = mutableListOf<Int>()
            for (i in degree.indices) {
                if (degree[i] == 0) {
                    q.add(i)
                }
            }
            while (q.isNotEmpty()) {
                if (q.size > 1) {
                    println("?")
                    return@repeat
                }
                val now = q.removeFirst()
                result.add(now)
                for (i in graph[now].indices) {
                    if (graph[now][i] == 1) {
                        degree[i]--
                    }
                }
                for (i in degree.indices) {
                    if (degree[i] == 0 && i !in result) {
                        q.add(i)
                    }
                }
            }
            if (result.size != n) {
                println("IMPOSSIBLE")
            } else {
                result.forEach { print("${it+1} ") }
            }
        }

    }
}

fun main() {
    Solution26(readln().trim().toInt()).solution()
}