
class Solution24(val n: Int, val m: Int) {
    val graph = Array(n) { Array(n) { 1 } }
    fun solution() {
        val dis = Array(n) { 0 }
        repeat(m) {
            val li = readln().trim().split(" ").map { it.toInt() }
            val list = mutableListOf<Int>()
            for (i in 0 until li.size) {
                list += MutableList(li[i]) { i }
            }
            var (x, y) = listOf(n, 0)
            if (li[0] == n) {
                x = 0
                y = 1
            }
            for (i in li[0] until li[0]+li[1]) {
                graph[x-i][y] += 0
            }
            if (li[0] == n) {
                x = 0
                y = 1
            }
            for (i in li[0]+li[1] until li[0]+li[1]+li[2]) {
                graph[x][y+(i-n)] += list[i]
                dis[i-n] += list[i]
            }
        }
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (i != 0 && j != 0) graph[i][j] += dis[j-1]
                print("${graph[i][j]} ")
            }
        }
    }
}

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    Solution24(n, m).solution()
}