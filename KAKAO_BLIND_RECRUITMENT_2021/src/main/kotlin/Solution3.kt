// 합승 택시 요금
class Solution3 {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val INF = 200 * 100000 * 2
        val distance = (0 until n).map { (0 until n).map { INF }.toMutableList() }
        for (i in (0 until n)) {
            distance[i][i] = 0
        }
        fares.forEach {
            val (a, b, c) = it
            distance[a-1][b-1] = c
            distance[b-1][a-1] = c
        }
        for (k in (0 until n)) {
            for (i in (0 until n)) {
                for (j in (0 until n)) {
                    if (distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j]
                    }
                }
            }
        }
        val s = s-1
        val a = a-1
        val b = b-1
        var answer = INF
        for (i in (0 until n)) {
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

// Math.min()으로 계속 비교하는것보다 if 문으로 값을 비교해서 갱신해주는게 더 빠르다.
// 호율성을 높이고 싶다면 max(), min() 보다는 if 문으로 걸러주면 된다.