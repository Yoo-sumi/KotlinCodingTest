// https://www.acmicpc.net/problem/14501

class Solution2(val n: Int) {
    val graph = Array<Pair<Int, Int>>(n) { Pair(0, 0) }
    val dp = Array(n + 1) { 0 }
    fun solution() {
        var result = 0
        repeat(n) {
            val (i, v) = readln().split(" ").map { it.toInt() }
            graph[it] = Pair(i, v)
        }
        for (i in n-1  downTo 0 step  1) {
            if (graph[i].first + i > n) {
                dp[i] = result
                continue
            }
            dp[i] = Math.max(result, dp[graph[i].first + i] + graph[i].second)
            result = dp[i]
        }
        println(result)
    }
}

fun main() {
    Solution2(readln().toInt()).solution()
}