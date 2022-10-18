// https://www.acmicpc.net/problem/1890

class Solution8(val n: Int) {
    fun solution() {
        val graph = Array<Array<Int>>(n) { arrayOf() }
        val dp = Array(n) { Array(n) { 0L } }
        repeat(n) {
            graph[it] = readln().split(" ").map { it.toInt() }.toTypedArray()
        }
        dp[0][0] = 1
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (dp[i][j] != 0L && graph[i][j] !=0 && j + graph[i][j] < n) {
                    dp[i][j + graph[i][j]] += dp[i][j]
                }
                if (dp[i][j] != 0L && graph[i][j] !=0 && i + graph[i][j] < n) {
                    dp[i + graph[i][j]][j] += dp[i][j]
                }
            }
        }
        println(dp[n-1][n-1])
    }
}

fun main() {
    Solution8(readln().toInt()).solution()
}

// dp를 int 말고 long으로 바꾸니까 해결됐다.