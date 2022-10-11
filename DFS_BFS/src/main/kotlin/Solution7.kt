// https://www.acmicpc.net/problem/16234
import kotlin.math.abs

class Solution7(val n: Int, val l: Int, val r: Int) {
    val graph = MutableList(n) { MutableList(n) {0} }
    var moveTemp = MutableList(n) { MutableList(n) { 0 } }
    val dx = listOf(-1, 1, 0, 0)
    val dy = listOf(0, 0, -1, 1)
    var store = hashMapOf<Int, MutableList<List<Int>>>()

    fun move() {
        store.values.forEach {
            val li = it
            var total = 0
            li.forEach { point ->
                val (x, y) = point
                total += graph[x][y]
            }
            total /= li.size
            li.forEach { point ->
                val (x, y) = point
                graph[x][y] = total
            }
        }
    }

    fun open(x: Int, y: Int, num: Int) {
        val q = ArrayDeque<List<Int>>()
        q.add(listOf(x, y))
        moveTemp[x][y] = num
        while (q.isNotEmpty()) {
            val (now_x, now_y) = q.removeFirst()
            repeat(4) {
                val nx = now_x + dx[it]
                val ny = now_y + dy[it]
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) return@repeat
                val va = abs(graph[now_x][now_y] - graph[nx][ny])
                if (va in l..r && moveTemp[nx][ny] == 0) {
                    moveTemp[nx][ny] = num
                    q.add(listOf(nx, ny))
                    store[num]!!.add(listOf(nx, ny))
                }
            }
        }
    }

    fun solution() {
        var result = 0
        repeat(n) {
            graph[it] = readln().split(" ").map { it.toInt() }.toMutableList()
        }
        while (true) {
            var count = 1
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (moveTemp[i][j] == 0) {
                        store[count] = mutableListOf(listOf(i, j))
                        open(i, j, count)
                        count++
                    }
                }
            }

            if (count == n*n + 1) break
            move()
            result++
            moveTemp = MutableList(n) { MutableList(n) { 0 } }
            store = hashMapOf()
        }
        println(result)
    }

}

fun main() {
    val (n, l, r) = readln().split(" ").map { it.toInt() }
    Solution7(n, l, r).solution()
}

// 연합 문을 열때 처음에 문제 이해를 잘못해서 구현을 망쳤다.
// bfs로 쭉쭉 들어가서 문을 열 수 있을 떄까지 탐색하고 다음으로 넘어가야 한다.