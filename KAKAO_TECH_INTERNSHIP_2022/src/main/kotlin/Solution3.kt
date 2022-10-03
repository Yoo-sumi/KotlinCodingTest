// 등산코스 정하기
import java.util.*

class Solution3 {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        var answer = intArrayOf(50000, 10000000)
        val conn = (0 .. n).map { mutableListOf<List<Int>>() }
        paths.forEach {
            val (a, b, c)  = it
            if (a in summits && b in summits) return@forEach
            conn[a].add(listOf(b, c))
            conn[b].add(listOf(a, c))
        }
        val pq = PriorityQueue<List<Int>> { p1, p2 ->
            when {
                p1[0] > p2[0] -> 1
                p1[0] < p2[0] -> -1
                else -> 0
            }
        }
        gates.map { pq.add(listOf(0, it)) }
        val MAX = 10000001
        val min_dis = (0..n).map { MAX }.toMutableList()

        while (pq.isNotEmpty()) {
            val (intensity, node) = pq.poll()
            if (min_dis[node] <= intensity) continue
            min_dis[node] = intensity
            if (node in summits) continue
            for ((newNode, newCost) in conn[node]) {
                val newIntensity = Math.max(newCost, intensity)
                if (min_dis[newNode] <= newIntensity) continue
                pq.add(listOf(newIntensity, newNode))
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
/*
class Solution3 {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        var answer = intArrayOf(50000, 10000000)
        var routes = hashMapOf<Int, MutableList<List<Int>>>()
        val summits = summits.toHashSet()
        val gates = gates.toHashSet()
        paths.forEach {
            val (a, b, c) = it
            if (a in summits && b in summits) return@forEach
            if (routes[a] == null) {
                routes[a] = mutableListOf(listOf(b, c))
            } else {
                routes[a]?.add(listOf(b, c))
            }
            if (routes[b] == null) {
                routes[b] = mutableListOf(listOf(a, c))
            } else {
                routes[b]?.add(listOf(a, c))
            }
        }
        routes.values.forEach {
            it.sortBy { it[1] }
        }
        val q = mutableListOf<Pair<List<Int>, Int>>()
        gates.sorted()
        gates.forEach {
            q.add(Pair(mutableListOf(it), 0))
        }
        summits.sorted()
        while (q.isNotEmpty()) {
            val (li, count) = q.removeFirst()
            val num = li.last()
            if (num in summits) {
                if (answer[1] > count) {
                    answer = intArrayOf(num, count)
                } else if (answer[1] == count && answer[0] > num) {
                    answer[0] = num
                }
                continue
            }
            val course = routes[num] ?: continue
            for (i in (0..course.lastIndex)) {
                val (node, cost) = course[i]
                if (node !in li && node !in gates) {
                    val new = li + node
                    if (cost > count) q.add(Pair(new, cost))
                    else q.add(Pair(new, count))
                }
            }
        }
        return answer
    }
}*/

fun main() {
    //val answer = Solution3().solution(	7, arrayOf(intArrayOf(1, 2, 5), intArrayOf(1, 4, 1), intArrayOf(2, 3, 1), intArrayOf(2, 6, 7), intArrayOf(4, 5, 1), intArrayOf(5, 6, 1), intArrayOf(6, 7, 1)), intArrayOf(3, 7), intArrayOf(1, 5))
    val answer = Solution3().solution(	6, arrayOf(intArrayOf(1, 2, 3), intArrayOf(2, 3, 5), intArrayOf(2, 4, 2), intArrayOf(2, 5, 4), intArrayOf(3, 4, 4), intArrayOf(4, 5, 3), intArrayOf(4, 6, 1), intArrayOf(5, 6, 1)), intArrayOf(1, 3), intArrayOf(5))
    //val answer = Solution3().solution(	7, arrayOf(intArrayOf(1, 4, 4), intArrayOf(1, 6, 1), intArrayOf(1, 7, 3), intArrayOf(2, 5, 2), intArrayOf(3, 7, 4), intArrayOf(5, 6, 6)), intArrayOf(1), intArrayOf(2, 3, 4))

    println("${answer[0]}, ${answer[1]}")
}