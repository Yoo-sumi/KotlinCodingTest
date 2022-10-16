// https://www.acmicpc.net/problem/2839

class Solution4(val n: Int) {
    fun solution() {
        val dp = Array(n + 1) { INF }
        if (dp.lastIndex >= 3) {
            dp[3] = 1
        }
        if (dp.lastIndex >= 5) {
            dp[5] = 1
        }
        for(i in 1..n) {
            val three = i - 3
            val five = i -5
            if (three < 1 && five < 1) continue
            if (i == 3 || i == 5) continue
            if (five >= 1 && three >= 1) {
                dp[i] = Math.min(dp[three] + 1, dp[five] + 1)
                continue
            }
            if (five >= 1) {

                dp[i] = Math.min(INF, dp[five] + 1)
            }
            if (three >= 1) {
                dp[i] = Math.min(dp[three] + 1, INF)
            }
        }
        if (dp[n] >= INF) println(-1)
        else println(dp[n])
    }
    companion object {
        const val INF = 1e9.toInt()
    }
}

fun main() {
    Solution4(readln().toInt()).solution()
}