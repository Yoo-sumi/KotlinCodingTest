// https://www.acmicpc.net/problem/1800

class Solution9(val n: Int, val p: Int, val k: Int) {
    val graph = Array(n+1) { mutableListOf<Pair<Int, Int>>() }
    var flag = false
    fun dijkstra(x: Int): Boolean {
        val q = ArrayDeque<Pair<Int, Int>>()
        q.add(Pair(1, 0))
        val dist = Array(n+1) { 1e9.toInt() }
        dist[1] = 0
        while (q.isNotEmpty()) {
            val (now, cost) = q.removeFirst()
            graph[now].forEach {
                var temp = 1
                if (it.second > x) {
                    temp = 1
                } else {
                    temp = 0
                }
                if (temp + cost < dist[it.first]) {
                    dist[it.first] = temp + cost
                    q.add(Pair(it.first, dist[it.first]))
                }
            }
        }
        if (dist[n] == 1e9.toInt()) {
            flag = true
        }
        return dist[n] <= k
    }
    fun solution() {
        repeat(p) {
            val (a, b, c) = readln().trim().split(" ").map { it.toInt() }
            graph[a].add(Pair(b, c))
            graph[b].add(Pair(a, c))
        }
        var left = 0
        var right = 1000001 * n
        var result = 0
        while (left <= right) {
            val mid = (left + right) / 2
            if (dijkstra(mid)) {
                right = mid - 1
                result = mid
            } else {
                left = mid + 1
            }
        }
        if (flag) {
            println(-1)
        } else {
            println(result)
        }
    }
}

fun main() {
    val (n, p, k) = readln().trim().split(" ").map { it.toInt() }
    Solution9(n, p, k).solution()
}

/*
다익스트라와 이분탐색을 같이 활용해야 풀리는 문제다.
이분탐색의 기준은 비용이고, 기준 비용보다 큰 경로의 경우 1, 작은 경우 0 으로 가중치를 준다.
dist[n]이 k개 이하면 가능한 경우이기 때문에 true를, 불가능하다면 flase를 리턴해서 Mid 값을 갱신해준다.
*/