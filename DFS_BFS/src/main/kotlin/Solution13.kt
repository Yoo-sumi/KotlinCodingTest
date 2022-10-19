// https://www.acmicpc.net/problem/2606

class Solution13(val n: Int) {
    val graph = Array(n+1) { mutableListOf<Int>() }
    val flag = Array(n+1) { false }
    fun solution() {
        repeat(readln().toInt()) {
            val (a,b) = readln().split(" ").map { it.toInt() }
            graph[a].add(b)
            graph[b].add(a)
        }
        flag[1] = true
        val q = ArrayDeque<Int>()
        q.add(1)
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            graph[now].forEach {
                if (!flag[it]) {
                    flag[it] = true
                    q.add(it)
                }
            }
        }
        println(flag.count { it } - 1)
    }
}

fun main() {
    Solution13(readln().toInt()).solution()
}