class Solution25(val n: Int) {
    var map = Array(n) { Array(n) { 1e9.toInt() } }

    fun solution() {
        val m = readln().trim().toInt()
        repeat(m) {
            val (a, b) = readln().trim().split(" ").map { it.toInt() - 1 }
            map[a][b] = 1
        }
        for (i in 0 until n) {
            map[i][i] = 0
        }

        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (map[i][k] + map[k][j] < map[i][j]) {
                        map[i][j] = map[i][k] + map[k][j]
                    }
                }
            }
        }

        for (i in 0 until n) {
            var count = 0
            for (j in 0 until n) {
                if (map[i][j] == 1e9.toInt() && map[j][i] == 1e9.toInt()) {
                    count++
                }
            }
            println(count)
        }
    }
}

fun main() {
    Solution25(readln().trim().toInt()).solution()
}