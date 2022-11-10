// https://www.acmicpc.net/problem/1917

class Solution29 {
    var graph = MutableList(6) { mutableListOf<Int>() }
    val boxes = listOf(
        listOf(
            listOf(1,0,0,0),
            listOf(1,1,1,1),
            listOf(1,0,0,0)
        ),
        listOf(
            listOf(0,1,0,0),
            listOf(1,1,1,1),
            listOf(1,0,0,0)
        ),
        listOf(
            listOf(0,0,1,0),
            listOf(1,1,1,1),
            listOf(1,0,0,0)
        ),
        listOf(
            listOf(0,0,0,1),
            listOf(1,1,1,1),
            listOf(1,0,0,0)
        ),
        listOf(
            listOf(0,0,1,0),
            listOf(1,1,1,1),
            listOf(0,1,0,0)
        ),
        listOf(
            listOf(0,0,1,1),
            listOf(0,1,1,0),
            listOf(1,1,0,0)
        ),
        listOf(
            listOf(0,0,1,1),
            listOf(1,1,1,0),
            listOf(1,0,0,0)
        ),
        listOf(
            listOf(1,1,0,0),
            listOf(0,1,1,1),
            listOf(0,1,0,0)
        ),
        listOf(
            listOf(0,1,0,0),
            listOf(1,1,1,1),
            listOf(0,1,0,0)
        ),
        listOf(
            listOf(0,1,0,0),
            listOf(1,1,1,0),
            listOf(0,0,1,1)
        ),
        listOf(
            listOf(0,0,1,1,1),
            listOf(1,1,1,0,0)
        )
    )
    fun mirror(dir: Int, graph: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
        val result = MutableList(graph.size) { MutableList(graph[0].size) { 0 } }
        // 좌우 반전
        if (dir == 0) {
            for (i in 0 until graph[0].size) {
                for (j in 0 until graph.size) {
                    result[j][i] = graph[j][graph[0].size-1-i]
                }
            }
        }
        // 상하 반전
        if (dir == 1) {
            for (i in 0 until graph.size) {
                for (j in 0 until graph[0].size) {
                    result[i][j] = graph[graph.size-1-i][j]
                }
            }
        }
        return result
    }
    fun rotate(graph: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
        val result = MutableList(graph[0].size) { MutableList(graph.size) { 0 } }
        for (i in 0 until graph[0].size) {
            for (j in 0 until graph.size) {
                result[i][j] = graph[graph.size-1-j][i]
            }
        }
        return result
    }
    fun check(x: Int, y: Int, box: MutableList<MutableList<Int>>): Boolean {
        for (i in 0 until box.size) {
            for (j in 0 until box[0].size) {
                val nx = x+i
                val ny = y+j
                if (nx < 0 || nx >= graph.size || ny < 0 || ny >= graph[0].size) return false
                if (box[i][j]!=graph[nx][ny]) return false
            }
        }
        return true
    }
    fun copyBox(box: List<List<Int>>): MutableList<MutableList<Int>> {
        val result = MutableList(box.size) { MutableList(box[0].size) { 0 } }
        for (i in 0 until box.size) {
            for (j in 0 until box[0].size) {
                result[i][j] = box[i][j]
            }
        }
        return result
    }
    fun solution() {
        repeat(3) {
            graph = MutableList(6) { mutableListOf() }
            repeat(6) {
                graph[it] = readln().split(" ").map { it.toInt() }.toMutableList()
            }
            for (box in boxes) {
                var cube = copyBox(box)
                for (i in 0 until 2) {
                    for (j in 0 until 4) {
                        for (x in 0 until 6) {
                            for (y in 0 until 6) {
                                if (check(x,y, cube)) {
                                    println("yes")
                                    return@repeat
                                }
                            }
                        }
                        cube = rotate(cube)
                    }
                    cube = mirror(i, cube)
                }
            }
            println("no")
        }
    }
}

fun main() {
    Solution29().solution()

}

/*
정육면체 전개도 종류가 11가지이다.
6x6 배열의 모든 칸에 대해 정육면체 전개도를 하나씩 대보면서 검사합니다.
검사할 때는 매 전개도마다 반전 2회, 회전 4회를 적용해서 총 8번 검사합니다.
https://velog.io/@embeddedjune/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%EC%A4%80-%EC%8B%9C%EB%AE%AC%EB%A0%88%EC%9D%B4%EC%85%98-1917-%EC%A0%95%EC%9C%A1%EB%A9%B4%EC%B2%B4-%EC%A0%84%EA%B0%9C%EB%8F%84
*/