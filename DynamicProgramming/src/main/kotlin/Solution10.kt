// https://www.acmicpc.net/problem/15988

class Solution10(val t: Int) {
    fun solution() {
        val dp = Array(1000001 ) { 0L }
        dp[1] = 1
        dp[2] = 2
        dp[3] = 4
        for (i in 4..1000000) {
            dp[i] = (dp[i-3] + dp[i-2] + dp[i-1]) % 1000000009
        }
        repeat(t) {
            println(dp[readln().toInt()])
        }
    }
}

fun main() {
    Solution10(readln().toInt()).solution()
}