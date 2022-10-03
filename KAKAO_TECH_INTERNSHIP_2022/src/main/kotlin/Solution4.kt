import java.util.Collections

class Solution4 {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        val problems = problems.toMutableList()
        var maxAlp = Collections.max(problems.map { it[0] })
        var maxCop = Collections.max(problems.map { it[1] })
        if (alp >= maxAlp && cop >= maxCop) return 0
        maxAlp = Math.max(maxAlp, alp)
        maxCop = Math.max(maxCop, cop)
        val dp = (0..maxAlp).map { (0..maxCop).map { 1e9.toInt() }.toMutableList() }
        dp[alp][cop] = 0
        problems.add(intArrayOf(0, 0, 1, 0, 1))
        problems.add(intArrayOf(0, 0, 0, 1, 1))
        for (row in (alp..maxAlp)) {
            for (col in (cop..maxCop)) {
                problems.forEach {
                    val (a, b, c, d, e) = it
                    if (row >= a && col >= b) {
                        var nr = row + c
                        var nc = col + d
                        if (nr > maxAlp) nr = maxAlp
                        if (nc > maxCop) nc = maxCop
                        dp[nr][nc] = Math.min(dp[row][col] + e, dp[nr][nc])
                    }
                }
            }
        }
        return dp[dp.lastIndex][dp[0].lastIndex]
    }
}


fun main() {
    //val answer = Solution4().solution(	10, 10, arrayOf(intArrayOf(10, 15, 2, 1, 2), intArrayOf(20, 20, 3, 3, 4)))
    val answer = Solution4().solution(	0, 0, arrayOf(intArrayOf(0, 0, 2, 1, 2), intArrayOf(4, 5, 3, 1, 2), intArrayOf(4, 11, 4, 0, 2), intArrayOf(10, 4, 0, 4, 2)))
    println(answer)
}