// https://www.acmicpc.net/problem/17182

class Solution3(val n: Int, val k: Int) {
    val graph = Array(n) { Array(0) { 0 } }
    var reuslt = 1e9.toInt()

    fun dfs(temp: MutableList<Int>, store: List<Int>, count: Int) {
        if (store.isEmpty()) {
            reuslt = Math.min(reuslt, count)
            return
        }
        val dd = temp[temp.lastIndex]
        for (i in 0..store.lastIndex) {
            if (store[i] == k) continue
            dfs((temp + listOf(store[i])).toMutableList(), store.filter { it != store[i] }, count + graph[dd][store[i]])
        }
    }

    fun solution() {
        repeat(n) {
            graph[it] = readln().split(" ").map { it.toInt() }.toTypedArray()
        }
        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j])
                }
            }
        }
        dfs(mutableListOf(k), (0 until n).filter { it!=k }.toList(), 0)
        println(reuslt)
    }
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    Solution3(n, k).solution()
}