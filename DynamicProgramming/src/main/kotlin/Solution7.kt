// https://www.acmicpc.net/problem/11726

class Solution7 {
    fun solution() {
        val n = readln().toInt()
        val dp = Array(n + 1) { 0 }
        if (n == 1) {
            println(1)
            return
        }
        if (n == 2) {
            println(2)
            return
        }
        dp[1] = 1
        dp[2] = 2
        for (i in 3..n) {
            dp[i] = (dp[i-2] + dp[i-1]) % 10007
        }
        println(dp[n])
    }
}

fun main() {
    Solution7().solution()
}