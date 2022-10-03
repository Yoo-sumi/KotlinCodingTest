class Solution3 {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val INF = 200 * 100000 * 2
        val distance = (0 .. n).map { (0 .. n).map { INF }.toMutableList() }
        for (i in (1 .. n)) {
            distance[i][i] = 0
        }
        fares.forEach {
            val (a, b, c) = it
            distance[a][b] = c
            distance[b][a] = c
        }
        for (k in (1 .. n)) {
            for (i in (1 .. n)) {
                for (j in (1 .. n)) {
                    if (distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j]
                    }
                }
            }
        }
        var answer = INF
        for (i in (1 .. n)) {
            answer = Math.min(distance[s][i] + distance[i][a] + distance[i][b], answer)
        }
        return answer
    }
}

fun main() {
    //val answer = Solution3().solution(7, 3, 4, 1, arrayOf(intArrayOf(5, 7, 9), intArrayOf(4, 6, 4), intArrayOf(3, 6, 1), intArrayOf(3, 2, 3), intArrayOf(2, 1, 6)))
    val answer = Solution3().solution(6, 4, 6, 2, arrayOf(intArrayOf(4, 1, 10), intArrayOf(3, 5, 24), intArrayOf(5, 6, 2), intArrayOf(3, 1, 41), intArrayOf(5, 1, 24), intArrayOf(4, 6, 50), intArrayOf(2, 4, 66), intArrayOf(2, 3, 22), intArrayOf(1, 6, 25)))
    println(answer)
}