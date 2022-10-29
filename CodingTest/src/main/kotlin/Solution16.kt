class Solution16(val n: Int) {
    val graph = Array(n) { Array(n) { 0 } }

    fun check(x: Int, y: Int) {
        for (i in 0 until n) {
            if (graph[y][i] == graph[x][y] && i!=x) {
                graph[x][i] = 1
                graph[i][x] = 1
            }
        }
    }

    fun solution() {
        repeat(readln().toInt()) {
            val (r, a, b) = readln().split(" ")
            val numA = a.toInt() - 1
            val numB = b.toInt() - 1
            var relation = 0

            when (r) {
                "E" -> relation = -1
                "F" -> relation = 1
            }
            graph[numA][numB] = relation
            graph[numB][numA] = relation
        }
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (i == j || graph[i][j] == 0) continue
                check(i, j)
            }
        }
        val flag = Array(n) { false }
        var result = 0
        for (i in 0 until n) {
            if (flag[i]) continue
            flag[i] = true
            for (j in 0 until n) {
                if (graph[i][j] == 1) {
                    flag[j] = true
                }
            }
            result++
        }
        println(result)
    }
}

fun main() {
    Solution16(readln().toInt()).solution()
}