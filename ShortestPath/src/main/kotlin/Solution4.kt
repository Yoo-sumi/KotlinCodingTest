package main.kotlin

class Solution4(val n: Int, val m: Int) {
    val graph = hashMapOf<Int, MutableList<Int>>()
    val visited = Array(n + 1) { INF }
    fun solution(): List<Int> {
        repeat(m) {
            val (a, b) = readln().split(" ").map { it.toInt() }
            if (graph[a] == null) {
                graph[a] = mutableListOf(b)
            } else {
                graph[a]!!.add(b)
            }
            if (graph[b] == null) {
                graph[b] = mutableListOf(a)
            } else {
                graph[b]!!.add(a)
            }
        }
        val q = ArrayDeque<Int>()
        q.add(1)
        visited[1] = 0
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            graph[now] ?: continue
            graph[now]?.forEach {
                if (visited[it] == INF) {
                    visited[it] = visited[now] + 1
                    q.add(it)
                }
            }
        }
        var result = 0
        visited.forEach {
            if (it != INF) {
                result = Math.max(result, it)
            }
        }
        val answer = mutableListOf<Int>()
        visited.forEachIndexed { index, it ->
            if (it == result) {
                answer.add(index)
            }
        }
        return listOf(answer[0], result, answer.size)
    }
    companion object {
        const val INF = 1e9.toInt()
    }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val result = Solution4(n, m).solution()
    result.forEach { print("$it ") }
}

/*
6 7
3 6
4 3
3 2
1 3
1 2
2 4
5 2
* */