// https://www.acmicpc.net/problem/3665

class Solution2(val t: Int) {
    fun solution() {
        repeat(t) {
            val n = readln().toInt()
            val graph = Array(n + 1) { Array(n + 1) { false } }
            val degree = Array(n + 1) { 0 }
            val li = readln().split(" ").map { it.toInt() }
            for (i in li.indices) {
                for (j in i + 1 until li.size) {
                    degree[li[j]] += 1
                    graph[li[i]][li[j]] = true
                    graph[li[j]][li[i]] = false
                }
            }
            val m = readln().toInt()

            repeat(m) {
                val (a, b) = readln().split(" ").map { it.toInt() }
                if (graph[a][b]) {
                    graph[a][b] = false
                    graph[b][a] = true
                    degree[a] += 1
                    degree[b] -= 1
                } else {
                    graph[a][b] = true
                    graph[b][a] = false
                    degree[a] -= 1
                    degree[b] += 1
                }
            }
            val q = ArrayDeque<Int>()
            for (i in 1 until degree.size) {
                if (degree[i] == 0) {
                    q.add(i)
                }
            }
            val result = mutableListOf<Int>()
            var si = false
            while (q.isNotEmpty()) {
                if (q.size > 1) {
                    si = true
                }
                val now = q.removeFirst()
                result += now

                graph[now].forEachIndexed { index, b ->
                    if (index == 0) return@forEachIndexed
                    if (b) {
                        degree[index] -= 1
                        if (degree[index] == 0) {
                            q.add(index)
                        }
                    }
                }
            }

            if (result.size == n) {
                result.map { print("$it ") }
                println()
            } else if (si) {
                println("?")
            } else {
                println("IMPOSSIBLE")
            }
        }
    }
}

fun main() {
    Solution2(readln().toInt()).solution()
}

// 위상 정렬 알고리즘을 떠올리면 된다.
// 차수가 0인 노드를 큐에 넣고 탐색한다.