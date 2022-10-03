// 등산코스 정하기
import java.util.*

class Solution3 {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val answer = intArrayOf(50000, 10000000)
        val conn = (0..n).map { mutableListOf<List<Int>>() }
        paths.forEach {
            val (a, b, c) = it
            if (a in summits && b in summits) return@forEach
            conn[a].add(listOf(b, c))
            conn[b].add(listOf(a, c))
        }
        val min_dis = (0..n).map { 10000001 }.toMutableList()
        val pq = PriorityQueue<List<Int>>(Comparator { o1, o2 ->
            when {
                o1[0] > o2[0] -> 3
                o1[0] < o2[0] -> 1
                else -> 2
            }
        })
        gates.map { pq.add(listOf(0, it))}
        while (pq.isNotEmpty()) {
            val (intensity, node) = pq.poll()
            if (min_dis[node] <= intensity) continue
            min_dis[node] = intensity
            if (node in summits) continue
            for ((next_node, next_cost) in conn[node]) {
                val next_intensity = Math.max(next_cost, intensity)
                if (next_intensity >= min_dis[next_node]) continue
                pq.add(listOf(next_intensity, next_node))
            }
        }
        summits.forEach {
            if (min_dis[it] < answer[1]) {
                answer[0] = it
                answer[1] = min_dis[it]
            } else if (min_dis[it] == answer[1]) {
                answer[0] = Math.min(it, answer[0])
            }
        }
        return answer
    }
}

fun main() {
    //val answer = Solution3().solution(	7, arrayOf(intArrayOf(1, 2, 5), intArrayOf(1, 4, 1), intArrayOf(2, 3, 1), intArrayOf(2, 6, 7), intArrayOf(4, 5, 1), intArrayOf(5, 6, 1), intArrayOf(6, 7, 1)), intArrayOf(3, 7), intArrayOf(1, 5))
    val answer = Solution3().solution(	6, arrayOf(intArrayOf(1, 2, 3), intArrayOf(2, 3, 5), intArrayOf(2, 4, 2), intArrayOf(2, 5, 4), intArrayOf(3, 4, 4), intArrayOf(4, 5, 3), intArrayOf(4, 6, 1), intArrayOf(5, 6, 1)), intArrayOf(1, 3), intArrayOf(5))
    //val answer = Solution3().solution(	7, arrayOf(intArrayOf(1, 4, 4), intArrayOf(1, 6, 1), intArrayOf(1, 7, 3), intArrayOf(2, 5, 2), intArrayOf(3, 7, 4), intArrayOf(5, 6, 6)), intArrayOf(1), intArrayOf(2, 3, 4))

    println("${answer[0]}, ${answer[1]}")
}