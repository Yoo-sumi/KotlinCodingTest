class Solution6(val n: Int) {
    var graph = MutableList(n){ MutableList(n) { "" } }
    val dx = listOf(-1, 1, 0, 0)
    val dy = listOf(0, 0, -1, 1)
    val teacher = mutableListOf<List<Int>>()
    var countt = 0

    fun isOk(x: Int, y: Int, index: Int): Boolean {
        var x = x
        var y = y
        while (true) {
            if (graph[x][y] == "S") {
                return false
            } else if (graph[x][y] == "O") {
                return true
            }
            val nx = x + dx[index]
            val ny = y + dy[index]
            if (nx < 0 || nx >= n || ny <0 || ny >= n) {
                return true
            }
            x = nx
            y = ny
        }
        return false
    }
    fun wall(count: Int): Boolean {
        println(countt++)
        var result = false
        if (count ==  0) {
            var all = 0
            for (j in 0..teacher.lastIndex) {
                val (t_x, t_y) = teacher[j]
                var direction = 0
                for (i in 0..3) {
                    if (!isOk(t_x, t_y, i)) {
                        break
                    }
                    direction++
                }
                if (direction != 4) {
                    break
                }
                all++
            }
            if (all == teacher.size) return true
            return false
        }
        for (i in 0 until n) {
            for (j in 0 until  n) {
                if (graph[i][j] == "X") {
                    graph[i][j] = "O"
                    result = wall(count - 1)
                    if (result) {
                        return true
                    }
                    graph[i][j] = "X"

                }
            }
        }
        return result
    }
    fun solution() {
        repeat(n) {
            graph[it] = readln().split(" ").toMutableList()
        }
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (graph[i][j] == "T") {
                    teacher.add(listOf(i, j))
                }
            }
        }
        if (wall(3)) {
            println("YES")
        } else {
            println("NO")
        }
    }
}

fun main() {
    Solution6(readln().toInt()).solution()
}

// 모든 벽의 조합 중 하나라도 true면 바로 true를 반환하면 되는걸 주의한다.
// 장애물을 놓을 수 있는 모든 경우의 수를 구한다.
// 선생님들의 4방향을 체크한다. 지정한 방향대로 쭉쭉 체크한다.