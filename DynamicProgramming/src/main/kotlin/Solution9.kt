// https://www.acmicpc.net/problem/12101
// https://velog.io/@jkh9615/series/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-1-2-3-%EB%8D%94%ED%95%98%EA%B8%B0-%EC%8B%9C%EB%A6%AC%EC%A6%88

class Solution9 {
    fun solution() {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val dp = Array<MutableList<String>>(n + 4) { mutableListOf() }
        dp[1] = mutableListOf("1")
        dp[2] = mutableListOf("1+1", "2")
        dp[3] = mutableListOf("1+1+1", "1+2", "2+1", "3")
        for (i in 4..n) {
            dp[i-1].forEach {
                dp[i].add("${it}+1")
            }
            dp[i-2].forEach {
                dp[i].add("${it}+2")
            }
            dp[i-3].forEach {
                dp[i].add("${it}+3")
            }
        }
        if (k-1 > dp[n].lastIndex) {
            println(-1)
            return
        }
        dp[n].sort()
        println(dp[n][k-1])
    }
}

fun main() {
    Solution9().solution()
}