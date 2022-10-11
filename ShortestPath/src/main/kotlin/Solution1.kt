// https://www.acmicpc.net/problem/11404

package main.kotlin

class Solution1(val n: Int, val m: Int) {
    companion object {
        const val INF = 1e9.toInt()
    }
    private val graph = Array(n) { Array(n) { INF } }
    fun solution(): Array<Array<Int>> {
        repeat(m) {
            var (a, b, v) = readln().split(" ").map { it.toInt() }
            a -= 1
            b -= 1
            graph[a][b] = Math.min(graph[a][b], v)
        }
        for (i in 0 until n) {
            graph[i][i] = 0
        }
        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j])
                }
            }
        }
        return graph
    }
}

fun main() {
    val n = readln().trim().toInt()
    val m = readln().trim().toInt()
    val result = Solution1(n, m).solution()
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (result[i][j] == Solution1.INF) {
                print("0" )
            } else {
                print("${result[i][j]} ")
            }
        }
        println()
    }
}

// 기본적인 플로이드 와샬 알고리즘이다.
// i에서 j까지의 최단 경로를 구할 수 있다.