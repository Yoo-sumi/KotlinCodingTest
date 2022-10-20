// https://www.acmicpc.net/problem/17780

class Solution1(val n: Int, val k: Int) {
    val graph = Array(n) { Array(0) { 0 } }
    val horeses = hashMapOf<Int, Horse>()
    val temp = Array(n) { Array(n) { mutableListOf<Int>() } }
    val dx = listOf(0,0,-1,1)
    val dy = listOf(1,-1,0,0)
    fun reverseDirecetion(direction: Int): Int {
        if (direction in listOf(0, 2)) {
            return direction + 1
        }
        return direction - 1
    }
    fun solution() {
        repeat(n) {
            graph[it] = readln().split(" ").toList().map { it.toInt() }.toTypedArray()
        }
        repeat(k) {
            val (x, y, d) = readln().split(" ").map { it.toInt() - 1 }
            horeses[it+1] = Horse(d, x, y)
            temp[x][y].add(it + 1)
        }
        var count = 1
        var flag = true
        while (flag) {
            for (it in 1..k) {
                val horse = horeses[it]!!
                if (temp[horse.x][horse.y][0] != it) continue
                var nx = horse.x + dx[horse.direction]
                var ny = horse.y + dy[horse.direction]
                // 파란색 + 범위 나갔을 때
                if ((nx < 0 || nx >= n || ny < 0 || ny >= n) || graph[nx][ny] == 2) {
                    horse.direction = reverseDirecetion(horse.direction)
                    val nnx = horse.x + dx[horse.direction]
                    val nny = horse.y + dy[horse.direction]
                    if ((nnx < 0 || nnx >= n || nny < 0 || nny >= n) || graph[nnx][nny] == 2) {
                        horse.direction = reverseDirecetion(horse.direction)
                        continue
                    }
                    nx = nnx
                    ny = nny
                }
                if (graph[nx][ny] == 0) {
                    // 흰색
                    temp[nx][ny] += temp[horse.x][horse.y]
                    if (temp[nx][ny].size >= 4) {
                        println(count)
                        flag = false
                        break
                    }
                    temp[horse.x][horse.y] = mutableListOf()
                } else if (graph[nx][ny] == 1){
                    // 빨간색
                    val before = temp[horse.x][horse.y].reversed()
                    temp[horse.x][horse.y] = mutableListOf()
                    temp[nx][ny] += before
                    if (temp[nx][ny].size >= 4) {
                        println(count)
                        flag = false
                        break
                    }
                }
                temp[nx][ny].forEach {
                    horeses[it]?.x = nx
                    horeses[it]?.y = ny
                }
            }
            count++
            if (count>1000) {
                println(-1)
                break
            }
        }
    }
}
data class Horse(
    var direction: Int,
    var x: Int,
    var y: Int
)
fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    Solution1(n, k).solution()
}