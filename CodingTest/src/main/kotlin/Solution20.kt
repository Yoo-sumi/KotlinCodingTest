// https://www.acmicpc.net/problem/18430

class Solution20(val n: Int, val m: Int) {
    val graph = Array(n) { listOf<Int>() }
    val check = Array(n) { Array(m) { false } }
    val blocks = listOf(
        listOf(listOf(0,0), listOf(0,1), listOf(1,1)),
        listOf(listOf(0,1), listOf(1,1), listOf(1,0)),
        listOf(listOf(1,1), listOf(1,0), listOf(0,0)),
        listOf(listOf(1,0), listOf(0,0), listOf(0,1)))
    var result = 0

    fun rightUp(x: Int, y: Int): Int {
        return graph[x][y-1] + graph[x+1][y] + 2* graph[x][y]
    }

    fun rightDown(x: Int, y: Int): Int {
        return graph[x-1][y] + graph[x][y-1] + 2* graph[x][y]
    }

    fun leftUp(x: Int, y: Int): Int {
        return graph[x][y+1] + graph[x+1][y] + 2* graph[x][y]
    }

    fun leftDown(x: Int, y: Int): Int {
        return graph[x][y+1] + graph[x-1][y] + 2* graph[x][y]
    }

    fun backTracking(xx: Int, yy: Int, sum: Int) {
        var x = xx
        var y = yy
        if (y == m) {
            y = 0
            x++
        }
        if (x == n) {
            result = Math.max(result, sum)
            return
        }
        if (!check[x][y]) {
            if (y-1 >= 0 && x+1 < n && !check[x+1][y] && !check[x][y-1]) {
                check[x+1][y] = true
                check[x][y-1] = true
                check[x][y] = true
                backTracking(x, y+1, sum + rightUp(x,y))
                check[x+1][y] = false
                check[x][y-1] = false
                check[x][y] = false
            }
            if (y-1 >=0 && x-1 >= 0 && !check[x-1][y] && !check[x][y-1]) {
                check[x-1][y] = true
                check[x][y-1] = true
                check[x][y] = true
                backTracking(x, y+1, sum + rightDown(x,y))
                check[x-1][y] = false
                check[x][y-1] = false
                check[x][y] = false
            }
            if (x+1 < n && y+1 < m && !check[x][y+1] && !check[x+1][y]) {
                check[x][y+1] = true
                check[x+1][y]  = true
                check[x][y] = true
                backTracking(x, y+1, sum + leftUp(x,y))
                check[x][y+1] = false
                check[x+1][y]  = false
                check[x][y] = false
            }
            if (y+1 < m && x-1 >= 0 && !check[x][y+1] && !check[x-1][y]) {
                check[x][y+1] = true
                check[x-1][y] = true
                check[x][y] = true
                backTracking(x, y+1, sum + leftDown(x,y))
                check[x][y+1] = false
                check[x-1][y] = false
                check[x][y] = false
            }
        }
        backTracking(x,y+1,sum)
    }

    fun solution() {
        repeat(n) {
            graph[it] = readln().trim().split(" ").map { it.toInt() }
        }
        backTracking(0, 0, 0)
        println(result)
    }
}

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    Solution20(n, m).solution()
}