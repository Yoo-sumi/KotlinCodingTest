// https://www.acmicpc.net/problem/9095

class Solution6(val t: Int) {
    fun solution() {
        repeat(t) {
            val n = readln().toInt()
            val dp = Array(n + 1) { 0 }
            if (n == 1)  {
                println(1)
                return@repeat
            }
            if (n == 2) {
                println(2)
                return@repeat
            }
            if (n == 3) {
                println(4)
                return@repeat
            }
            dp[1] = 1
            dp[2] = 2
            dp[3] = 4
            for (i in 4..n) {
                dp[i] = dp[i-3] + dp[i-2] + dp[i-1]
            }
            println(dp[n])
        }
    }
}

fun main() {
    Solution6(readln().toInt()).solution()
}