// https://www.acmicpc.net/problem/18500

class Solution15(val r: Int, val c: Int) {
    val graph = Array(r) { mutableListOf<Char>() }
    var temp = Array(r) { Array(c) { 0 } }
    val dx = listOf(-1,1,0,0)
    val dy = listOf(0,0,-1,1)

    fun bfs(x: Int, y: Int) {
        temp[x][y] = 1
        val q = ArrayDeque<Points>()
        q.add(Points(x, y))
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            repeat(4) {
                val nx = now.x + dx[it]
                val ny = now.y + dy[it]
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) return@repeat
                if (temp[nx][ny] == 0 && graph[nx][ny] == 'x') {
                    q.add(Points(nx, ny))
                    temp[nx][ny] = 1
                }
            }
        }
    }

    fun removeMinerals(stick: Int, direction: Boolean) {
        if (direction) {
            for (i in 0 until c) {
                if (graph[stick][i] == 'x') {
                    graph[stick][i] = '.'
                    break
                }
            }
        } else {
            for (i in c-1 downTo 0) {
                if (graph[stick][i] == 'x') {
                    graph[stick][i] = '.'
                    break
                }
            }
        }
    }

    fun solution() {
        repeat(r) {
            graph[it] = readln().toMutableList()
        }
        val n = readln().toInt()
        val sticks = readln().split(" ").map { r - it.toInt() }

        var type = 1
        sticks.forEach { stick ->
            removeMinerals(stick, type++ % 2 == 1)
            temp = Array(r) { Array(c) { 0 } }
            for (i in 0 until c) {
                if (temp[r-1][i] == 0 && graph[r-1][i] == 'x') {
                    bfs(r-1, i)
                }
            }
            val store = mutableListOf<Points>()
            for (i in 0 until r) {
                for (j in 0 until c) {
                    if (temp[i][j] != 1 && graph[i][j] == 'x') {
                        store.add(Points(i, j))
                    }
                }
            }
            if (store.isNotEmpty()) {
                var flag = true
                var cc = 0
                while (flag) {
                    for (s in 0 until store.size) {
                        if (store[s].x + cc == r-1) {
                            flag = false
                            break
                        }
                        if (temp[store[s].x + cc + 1][store[s].y] == 1) {
                            flag = false
                            break
                        }
                    }
                    if (flag) {
                        cc++
                    }
                }
                store.sortBy { -it.x }
                store.forEach {
                    graph[it.x][it.y] = '.'
                    graph[it.x + cc][it.y] = 'x'
                }
            }
        }
        graph.forEach {
            it.forEach {
                print("$it")
            }
            println()
        }
    }
}

data class Points(
    val x: Int,
    val y: Int
)
fun main() {
    val (r, c) = readln().split(" ").map { it.toInt() }
    Solution15(r, c).solution()
}

// 맨 아래에 있는 미네랄 기준으로 bfs를 돌면서 체크를 해준다.
// 체크가 안되어 있는 미네랄은 공중에 떠있다는 뜻이다.
// 공중에 떠있는 미네랄의 좌표를 store에 보관을 해주고
// 모양을 유지해서 내려야 되니까 한칸씩 내려보고 다른 미네랄이 닿거나 땅에 닿을때까지의 거리를 구하고
// for문을 돌면서 미네랄을 구한 거리만큼 내려준다.