// https://www.acmicpc.net/problem/1463

class Solution5 {
    fun solution() {
        val n = readln().toInt()
        val dp = Array( n + 1 ) { INF }
        dp[1] = 0
        for (i in 1..n) {
            val one = i + 1
            val two = i * 2
            val three = i * 3
            if (one <= dp.lastIndex) {
                dp[one] = Math.min(dp[one], dp[i] + 1)
            }
            if (two <= dp.lastIndex) {
                dp[two] = Math.min(dp[two], dp[i] + 1)
            }
            if (three <= dp.lastIndex) {
                dp[three] = Math.min(dp[three], dp[i] + 1)
            }
        }
        println(dp[n])
    }
    companion object {
        const val INF = 1e9.toInt()
    }
}

fun main() {
    Solution5().solution()
}