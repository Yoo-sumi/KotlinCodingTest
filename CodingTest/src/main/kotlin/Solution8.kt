// https://www.acmicpc.net/problem/11967

class Solution8(val n: Int, val m: Int) {
    val graph = Array(n) { Array(n) { 0 } }
    val graphTemp = Array(n) { Array(n) { 0 } }
    val possible = Array(n) { Array(n) { 0 } }
    val mapping = hashMapOf<Point, MutableList<Point>>()
    var temp = mutableListOf<Point>()
    val dx = listOf(-1,1,0,0)
    val dy = listOf(0,0,1,-1)
    fun solution() {
        repeat(m) {
            val (x, y, a, b) = readln().trim().split(" ").map { it.toInt() - 1 }
            if (mapping[Point(x, y)] == null) {
                mapping[Point(x, y)] = mutableListOf(Point(a, b))
            } else {
                mapping[Point(x, y)]?.add(Point(a, b))
            }
        }
        graph[0][0] = 1
        graphTemp[0][0] = 1
        possible[0][0] = 1
        possible[0][1] = 1
        possible[1][0] = 1
        var result = 0
        val q = ArrayDeque<Point>()
        q.add(Point(0, 0))
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            val li = mapping[now]
            li?.forEach {
                graph[it.x][it.y] = 1
                temp.add(it)
            }
            val loop = mutableListOf<Int>()
            for (i in 0 until temp.size) {
                val it = temp[i]
                if (possible[it.x][it.y] == 1 && graphTemp[it.x][it.y] == 0) {
                    graphTemp[it.x][it.y] = 1
                    graph[it.x][it.y] = 1
                    q.add(Point(it.x, it.y))
                    for (j in 0..3) {
                        val nx = it.x + dx[j]
                        val ny = it.y + dy[j]
                        if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue
                        possible[nx][ny] = 1
                    }
                    loop.add(i)
                }
            }
            temp = temp.filterIndexed { index, point -> index !in loop } as MutableList<Point>
        }
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (graph[i][j] == 1) {
                    result++
                }
            }
        }
        println(result)
    }
}

data class Point(
    var x: Int,
    var y: Int
)

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    Solution8(n, m).solution()
}

// 처음에는 새로 불을 켜는 방 기준으로 상하좌우를 확인해 불이 켜져 있는 방이면 큐에 넣는 로직이었는데
// 이런 로직은 아래의 경우 예외가 발생한다.
// 1 0 0 0
// 0 0 0 0
// 1 0 0 0
// 1 0 0 0
// 이 경우에 (0, 2) 기준으로 했을때 (0, 1)이 연결되어 있지 않는데, (0, 3) 때문에 가능한 경로로 판단이 되어 큐에 넣어지게 된다.
// 이동 가능한 경로를 표시하기 위해 큐에 넣어줄때 상하좌우 방향을 possible[nx][ny] = 1로 체크해주었다.