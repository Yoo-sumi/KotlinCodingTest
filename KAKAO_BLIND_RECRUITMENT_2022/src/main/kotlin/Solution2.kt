data class Group(val cur: Int, var sheep: Int, var wolf: Int)
class Solution2 {
    val visited: HashMap<Group, Int> = hashMapOf()
    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        var answer: Int = 0
        val graph = mutableMapOf<Int, MutableList<Int>>()
        val root = mutableMapOf<Int, Int>()
        edges.forEach {
            val (p, c) = it
            if (graph[p] == null) {
                graph[p] = mutableListOf(c)
            } else {
                graph[p]!!.add(c)
            }
            root[c] = p
        }
        println(graph)
        println(root)
        val q = mutableListOf<Group>(Group(0,1,0))
        visited[Group(0,1,0)] = 1
        while (q.isNotEmpty()) {
            q.forEach { println(it.toString()) }
            println()
            val now = q.removeFirst()
            answer = Math.max(answer, now.sheep)
            if (now.cur != 0) {
                val parent = root[now.cur]
                val temp = Group(parent!!, now.sheep, now.wolf)
                if (temp !in visited.keys) {
                    q.add(temp)
                    visited[temp] = 1
                }
            }
            val child = graph[now.cur] ?: continue
            child.forEach {
                val count = info[it]
                var temp: Group
                if (count == 0) {
                    temp = Group(it, now.sheep + 1, now.wolf)
                } else {
                    temp = Group(it, now.sheep, now.wolf + 1)
                }
                if (temp !in visited.keys && temp.sheep > temp.wolf) {
                    q.add(temp)
                    visited[temp] = 1
                }
            }
        }
        return answer
    }
}

fun main() {
    val result = Solution2().solution(intArrayOf(0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1), arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(1, 4), intArrayOf(0, 8), intArrayOf(8, 7), intArrayOf(9, 10), intArrayOf(9, 11), intArrayOf(4, 3), intArrayOf(6, 5), intArrayOf(4, 6), intArrayOf(8, 9)))
    println(result)
}