// https://www.acmicpc.net/problem/18353

class Solution3(val n: Int) {
    val dp = Array(n) { 1 }
    fun solution() {
        val li = readln().split(" ").map { it.toInt() }
        for (i in 1 until n) {
            for (j in 0 until i) {
                if (li[j] > li[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1)
                }
            }
        }
        println(n - dp.sortedDescending()[0])
    }
}

fun main() {
    Solution3(readln().toInt()).solution()
}