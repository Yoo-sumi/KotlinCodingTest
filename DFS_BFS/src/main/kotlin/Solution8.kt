// https://school.programmers.co.kr/learn/courses/30/lessons/60063
import kotlin.math.abs

data class Robot(var x1: Int, var y1: Int, var x2: Int, var y2: Int, var count: Int)
class Solution8 {
    var n = 0
    var m = 0
    lateinit var board: Array<IntArray>
    val dx = listOf(-1, 1, 0, 0)
    val dy = listOf(0, 0, -1, 1)
    val mapping = hashMapOf<Set<List<Int>>, Int>()
    fun solution(board: Array<IntArray>): Int {
        var answer = 1e9.toInt()
        this.board = board
        n = board.size
        m = board[0].size

        val visited = MutableList(n) { MutableList(m) {false} }
        val robot = Robot(0,0,0,1, 0)
        visited[0][0] = true
        visited[0][1] = true
        mapping[setOf(listOf(0,0), listOf(0,1))] = 1
        val q = ArrayDeque<Robot>()
        q.add(robot)
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            if ((now.x1 == n-1 && now.y1 == m-1) || (now.x2 == n-1 && now.y2 == m-1)) {
                answer = Math.min(answer, now.count)
                continue
            }
            repeat(4) {
                val nx1 = now.x1 + dx[it]
                val ny1 = now.y1 + dy[it]

                val nx2 = now.x2 + dx[it]
                val ny2 = now.y2 + dy[it]

                if (nx1 < 0 || nx1 >= n || ny1 < 0 || ny1 >= n ||
                    nx2 < 0 || nx2 >= n || ny2 < 0 || ny2 >= n) return@repeat

                if (board[nx1][ny1] == 1 || board[nx2][ny2] == 1) return@repeat

                if (setOf(listOf(nx1,ny1), listOf(nx2,ny2)) !in mapping.keys) {
                    q.add(Robot(nx1,ny1,nx2,ny2,now.count + 1))
                    mapping[setOf(listOf(nx1,ny1), listOf(nx2,ny2))] = 1
                }
            }
            var li: List<Int>
            // rotate 왼쪽 오른쪽
            if (abs(now.x1 - now.x2) == 1) {
                li = listOf(2, 3)
            } else {
                li = listOf(0, 1)
            }
            for (i in li) {
                val rnx1 = now.x1 + dx[i]
                val rny1 = now.y1 + dy[i]

                val rnx2 = now.x2 + dx[i]
                val rny2 = now.y2 + dy[i]

                if (rnx1 < 0 || rnx1 >= n || rny1 < 0 || rny1 >= n ||
                    rnx2 < 0 || rnx2 >= n || rny2 < 0 || rny2 >= n) continue
                if (board[rnx1][rny1] == 1 || board[rnx2][rny2] == 1) continue

                var temp = setOf(listOf(now.x1, now.y1), listOf(rnx1, rny1))
                if (temp !in mapping.keys) {
                    q.add(Robot(now.x1, now.y1, rnx1, rny1, now.count + 1))
                    mapping[temp] = 1
                }
                temp = setOf(listOf(now.x2, now.y2), listOf(rnx2, rny2))
                if (temp !in mapping.keys) {
                    q.add(Robot(now.x2, now.y2, rnx2, rny2, now.count + 1))
                    mapping[temp] = 1
                }
            }
        }
        return answer
    }
}


fun main() {
    val result = Solution8().solution(arrayOf(intArrayOf(0, 0, 0, 1, 1), intArrayOf(0, 0, 0, 1, 0), intArrayOf(0, 1, 0, 1, 1), intArrayOf(1, 1, 0, 0, 1), intArrayOf(0, 0, 0, 0, 0)))
    println(result)
}

// 회전하는 부분만 잘 구현하면 다른 최단 경로 찾는 것과 똑같다.