// https://www.acmicpc.net/problem/2310

class Solution30 {
    fun dfs(graph: Array<MutableList<Int>>, roomType: MutableList<String>, roomCost: MutableList<Int>): Boolean {
        val check = MutableList(roomType.size) { false }
        val q = ArrayDeque<Int>()
        q.add(0)
        check[0] = true
        var total = 0
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            for (room in graph[now]) {
                val type = roomType[room]
                val cost = roomCost[room]
                if (type == "L") {
                    if (total < cost) total = cost
                } else if (type == "T") {
                    if (total - cost < 0 ) continue
                    total -= cost
                }
                if (!check[room]) {
                    check[room] = true
                    q.add(room)
                }
            }
        }
        return check.filter { it }.size == check.size
    }

    fun solution() {
        while (true) {
            val n = readln().toInt()
            if (n == 0) break
            val graph = Array(n) { mutableListOf<Int>() }
            val roomType = MutableList(n) { "" }
            val roomCost = MutableList(n) { 0 }
            repeat(n) {
                val input = readln().split(" ")
                roomType[it] = input[0]
                roomCost[it] = input[1].toInt()
                val rooms = input.filterIndexed { index, s -> index > 1 && index < input.lastIndex }
                rooms.forEach { room ->
                    graph[it].add(room.toInt()-1)
                }
            }
            if (dfs(graph, roomType, roomCost)) {
                println("Yes")
            } else {
                println("No")
            }
        }
    }
}

fun main() {
    Solution30().solution()
}

/*
dfs를 돌려가면서 확인해보면 된다.
*/