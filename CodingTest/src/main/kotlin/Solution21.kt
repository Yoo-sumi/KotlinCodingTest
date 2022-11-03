// https://www.acmicpc.net/status?user_id=hugbee&problem_id=18808&from_mine=1

class Solution21(val n: Int, val m: Int, val k: Int) {
    val graph = Array(n) { Array(m) { 0 } }
    val stickers = Array(k) { mutableListOf<MutableList<Int>>() }

    fun rotate(sticker: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
        val n = sticker.size
        val m = sticker[0].size

        val temp = MutableList(m) { MutableList(n) { 0 } }

        for (i in 0 until n) {
            for (j in 0 until m) {
                temp[j][n-i-1] = sticker[i][j]
            }
        }
        return temp
    }

    fun isPossible(x: Int, y: Int, sticker: MutableList<MutableList<Int>>): Boolean {
        for (i in 0 until sticker.size) {
            for (j in 0 until sticker[0].size) {
                if (sticker[i][j] == 0) continue
                val nx = x + i
                val ny = y + j
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) return false
                if (graph[nx][ny] == 1 && sticker[i][j] == 1) return false
            }
        }
        for (i in 0 until sticker.size) {
            for (j in 0 until sticker[0].size) {
                val nx = x + i
                val ny = y + j
                if (graph[nx][ny] == 0) {
                    graph[nx][ny] = sticker[i][j]
                }
            }
        }
        return true
    }


    fun solution() {
        repeat(k) { kk ->
            val (nn, mm) = readln().trim().split(" ").map { it.toInt() }
            repeat(nn) {
                stickers[kk].add(readln().split(" ").map { it.toInt() } as MutableList<Int>)
            }
        }
        repeat(k) { i ->
            var tempSticker = stickers[i].map { it }.toMutableList()
            for (h in 0 until 4) {
                for (x in 0 until n) {
                    for (y in 0 until m) {
                        if (isPossible(x, y, tempSticker)) {
                            return@repeat
                        }
                    }
                }
                tempSticker = rotate(tempSticker)
            }
        }
        var result = 0
        graph.forEach {array->
            array.forEach { if (it == 1) result++ }
        }
        println(result)
    }
}

fun main() {
    val (n,m,k) = readln().trim().split(" ").map { it.toInt() }
    Solution21(n,m,k).solution()
}

/*
문제설명이 너무 애마했다
회전하는 타이밍을 모든 좌표를 다 돈 후에 스티커를 붙일 공간을 찾지 못했을 때로 해야한다.
*/