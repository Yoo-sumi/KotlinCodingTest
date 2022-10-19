// https://www.acmicpc.net/problem/15989

class Solution11(val n: Int) {
    fun solution() {
        val dp = Array(10001) { Array(4) { 0 } }
        dp[1][1] = 1
        dp[2][1] = 1
        dp[2][2] = 1
        dp[3][1] = 1
        dp[3][2] = 1
        dp[3][3] = 1

        for (i in 4 until dp.size) {
            dp[i][1] = dp[i-1][1]
            dp[i][2] = dp[i-2][1] + dp[i-2][2]
            dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3]
        }

        repeat(n) {
            println(dp[readln().toInt()].sum())
        }
    }
}

fun main() {
    Solution11(readln().toInt()).solution()
}

// 2차원 배열로 dp를 만들어서 생각해야 한다.