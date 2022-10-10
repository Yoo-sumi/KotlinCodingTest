// https://www.acmicpc.net/status?user_id=hugbee&problem_id=18352&from_mine=1
class Solution1 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val graph = hashMapOf<Int, MutableList<Int>>()
        var (n, m, k, x) = readLine()!!.split(" ").map { it.toInt() }
        x -= 1
        val distance = Array(n) { -1 }
        repeat(m) {
            val (a, b) = readLine()!!.split(" ").map { it.toInt() - 1 }
            if (graph[a] == null) {
                graph[a] = mutableListOf(b)
            } else {
                graph[a]!!.add(b)
            }
        }
        val q = ArrayDeque<Int>()
        q.add(x)
        distance[x] = 0
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            graph[now] ?: continue
            graph[now]!!.forEach {
                if (distance[it] == -1) {
                    distance[it] = distance[now] + 1
                    q.add(it)
                }
            }
        }
        val answer = mutableListOf<Int>()
        distance.forEachIndexed { index, i ->
            if (i == k) {
                answer.add(index + 1)
            }
        }
        if (answer.isEmpty()) println(-1)
        answer.sort()
        answer.forEach {
            println(it)
        }
    }
}

fun main() {
    val result = Solution1().solution()
}

// bfs 문제이다.
// 처음에 큐를 MutableList를 사용했더니 시간초과가 떴다.
// 큐를 이용할때는 MutableList 대신 ArrayDequeue를 이용하자.