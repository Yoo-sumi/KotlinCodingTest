// 합승 택시 요금
class Solution3ex {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val INF = 100000 * n * 2
        var result = INF
        val graph = (0 until n).map { (0 until  n).map { INF }.toMutableList() }
        fares.forEach {
            val (a, b, c) = it
            graph[a - 1][b - 1] = c
            graph[b - 1][a - 1] = c
        }
        for (i in 0 until n) {
            graph[i][i] = 0
        }
        for (k in (0 until n)) {
            for (i in (0 until n)) {
                for (j in (0 until n)) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j]
                    }
                }
            }
        }
        (0 until  n).forEach {
            result = Math.min(result, graph[s - 1][it] + graph[it][a - 1] + graph[it][b - 1])
        }
        return result
    }
}

fun main() {
    //val answer = Solution3().solution(7, 3, 4, 1, arrayOf(intArrayOf(5, 7, 9), intArrayOf(4, 6, 4), intArrayOf(3, 6, 1), intArrayOf(3, 2, 3), intArrayOf(2, 1, 6)))
    val answer = Solution3ex().solution(6, 4, 6, 2, arrayOf(intArrayOf(4, 1, 10), intArrayOf(3, 5, 24), intArrayOf(5, 6, 2), intArrayOf(3, 1, 41), intArrayOf(5, 1, 24), intArrayOf(4, 6, 50), intArrayOf(2, 4, 66), intArrayOf(2, 3, 22), intArrayOf(1, 6, 25)))
    println(answer)
}