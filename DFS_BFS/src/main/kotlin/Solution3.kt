import java.io.BufferedReader
import java.io.InputStreamReader

// https://www.acmicpc.net/problem/18405
class Solution3(val n: Int, val k: Int) {
    var graph = MutableList(n) { MutableList(n) { 0 } }
    val temp = MutableList(n) { MutableList(n) { 0 } }
    val dx = listOf(-1, 1, 0, 0)
    val dy = listOf(0, 0, -1, 1)

    fun virus(num: Int, x: Int, y: Int) {
        repeat(4) {
            val nx = x + dx[it]
            val ny = y + dy[it]
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) return@repeat
            if (graph[nx][ny] == 0) {
                graph[nx][ny] = num
            }
        }
    }

    fun solution() {
        repeat(n) {
            graph[it] = readln().split(" ").map { it.toInt() }.toMutableList()
        }
        val (s, xx, yy) = readln().split(" ").map { it.toInt() }
        repeat(s) {
            if (graph[xx-1][yy-1]!=0) {
                println(graph[xx - 1][yy - 1])
                return
            }
            val list = mutableListOf<List<Int>>()
            for (i in 0 until n) {
                for (j in 0 until n) {
                    temp[i][j] = graph[i][j]
                    if (graph[i][j] in 1..k) {
                        list.add(listOf(i, j, graph[i][j]))
                    }
                }
            }
            list.sortBy { it[2] }
            list.forEach {
                val (x, y, v) = it
                virus(v, x, y)
            }
        }
        println(graph[xx - 1][yy - 1])
    }
}

fun main() {
    val input = readln().split(" ").map { it.toInt() }
    Solution3(input[0], input[1]).solution()
}

// 한번 바이러스에 감염된 곳은 더 이상 값이 변하지 않기 때문에 구하고자 하는 위치의 값이 0이 아니게 되면
// 탐색을 중단하게 해서 시간을 단축시켰다.
test