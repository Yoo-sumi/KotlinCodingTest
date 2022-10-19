// https://www.acmicpc.net/problem/1260

class Solution9(val n: Int, val m: Int, val v: Int) {
    val graph = Array<MutableList<Int>>(n+1) { mutableListOf() }
    var flag = Array(n + 1) { false }
    fun dfs(start: Int): MutableList<Int> {
        val result = mutableListOf<Int>()
        result.add(start)
        flag[start] = true
        val root = graph[start]
        root.forEach {
            if (!flag[it]) {
                result += dfs(it)
            }
        }
        return result
    }
    fun bfs(start: Int): MutableList<Int> {
        val result = mutableListOf<Int>()
        val q = ArrayDeque<Int>()
        q.add(start)
        flag[start] = true
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            result.add(now)
            graph[now].forEach {
                if (!flag[it]) {
                    q.add(it)
                    flag[it] = true
                }
            }
        }
        return result
    }
    fun solution() {
        repeat(m) {
            val (a, b) = readln().split(" ").map { it.toInt() }
            graph[a].add(b)
            graph[b].add(a)
        }
        graph.forEach { it.sort() }

        dfs(v).map { print("$it ") }
        println()
        flag = Array(n + 1) { false }
        bfs(v).map { print("$it ") }
    }
}

fun main() {
    val (n, m, v)  = readln().split(" ").map { it.toInt() }
    Solution9(n,m,v).solution()
}