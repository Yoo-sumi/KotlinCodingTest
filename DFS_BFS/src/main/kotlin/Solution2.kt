// https://www.acmicpc.net/problem/14502
class Solution2(var n: Int, var m: Int) {
    lateinit var graph: MutableList<MutableList<Int>>
    var temp = MutableList(n) { MutableList(m) { 0 } }
    val virusList = mutableListOf<List<Int>>()
    val dx = listOf(-1, 1, 0, 0)
    val dy = listOf(0, 0, -1, 1)
    var answer = 0

    fun getScore(): Int {
        var hh = 0
        temp.forEach {
            it.forEach { rr ->
                if (rr == 0) hh++
            }
        }
        return hh
    }
    fun dfs(count: Int) {
        if (count == 0) {
            for (i in 0 until n) {
                for (j in 0 until m) {
                    temp[i][j] = graph[i][j]
                }
            }
            virusList.forEach {
                virus(it[0], it[1])
            }
            answer = Math.max(answer, getScore())
            return
        }
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (graph[i][j] == 0) {
                    graph[i][j] = 1
                    dfs(count - 1)
                    graph[i][j] = 0
                }
            }
        }
    }

    fun virus(r: Int, c: Int) {
        temp[r][c] = 2
        repeat(4) {
            val nx = r + dx[it]
            val ny = c + dy[it]
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) return@repeat
            if (temp[nx][ny] == 0) {
                virus(nx, ny)
            }
        }
    }

    fun solution() {
        graph = MutableList(n) { MutableList(m) { 0 } }
        repeat(n) {
            graph[it] = readLine()!!.split(" ").mapIndexed { index, p ->
                if (p.toInt() == 2) virusList.add(listOf(it, index))
                p.toInt()

            } as MutableList<Int>
        }
        dfs(3)
        println(answer)
    }
}
fun main() {
    val input = readLine()!!.split(" ").map { it.toInt() }
    Solution2(input[0], input[1]).solution()
}

// 리스트의 얕은 복사를 주의하자.
// virus 전염 dfs
// 벽 세우기 조합 dfs