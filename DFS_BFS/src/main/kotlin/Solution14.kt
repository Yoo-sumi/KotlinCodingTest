// https://www.acmicpc.net/problem/16953

class Solution14 {
    fun solution() {
        val (a,b) = readln().split(" ").map { it.toInt() }

        val q = ArrayDeque<Pair<Long, Int>>()
        q.add(Pair(a.toLong(), 1))

        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            if (now.first == b.toLong()) {
                println(now.second)
                return
            }
            if (now.first*2 <= b) {
                q.add(Pair(now.first*2, now.second+1))
            }
            if (now.first*10+1 <= b) {
                q.add(Pair(now.first*10+1, now.second+1))
            }
        }
        println(-1)
    }
}

fun main() {
    Solution14().solution()
}