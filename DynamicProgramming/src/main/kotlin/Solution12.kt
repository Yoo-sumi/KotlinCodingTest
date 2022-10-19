// https://www.acmicpc.net/problem/15990

class Solution12(val t: Int) {
    fun solution() {
        val dp = Array(100001) { Array(4) { 0L } }
        dp[1][1] = 1
        dp[2][2] = 1
        dp[3][1] = 1
        dp[3][2] = 1
        dp[3][3] = 1

        for (i in 4 until dp.size) {
            dp[i][1] = (dp[i-1][2] + dp[i-1][3]) % 1000000009
            dp[i][2] = (dp[i-2][1] + dp[i-2][3]) % 1000000009
            dp[i][3] = (dp[i-3][1] + dp[i-3][2]) % 1000000009
        }

        repeat(t) {
            println(dp[readln().toInt()].sum() % 1000000009)
        }
    }
}

fun main() {
    Solution12(readln().toInt()).solution()
}