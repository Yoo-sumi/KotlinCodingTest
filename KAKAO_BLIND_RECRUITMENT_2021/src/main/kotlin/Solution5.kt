// 카드 짝 맞추기
class Solution5 {
    var board = arrayOf(intArrayOf())
    val dx = listOf(-1, 1, 0, 0)
    val dy = listOf(0, 0, -1, 1)

    class Point(val r: Int, val c: Int, val cnt: Int)

    fun bfs(start: Point, end: Point): Int {
        val queue = mutableListOf(start)
        val visited = (0..3).map {(0..3).map { false }.toMutableList()}.toMutableList()
        while (queue.isNotEmpty()) {
            val now = queue.removeFirst()
            if (now.r == end.r && now.c == end.c) return now.cnt
            for (i in (0..3)) {
                var nx = now.r + dx[i]
                var ny = now.c + dy[i]
                if (nx < 0 || nx > 3 || ny < 0 || ny > 3) continue
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true
                    queue.add(Point(nx, ny, now.cnt + 1))
                }
                for (j in 0..1) {
                    if (board[nx][ny] != 0) break
                    if (nx + dx[i] < 0 || nx + dx[i] > 3 ||
                        ny + dy[i] < 0 || ny + dy[i] > 3) {
                        break
                    }
                    nx += dx[i]
                    ny += dy[i]
                }
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true
                    queue.add(Point(nx, ny, now.cnt + 1))
                }
            }
        }
        return 0
    }

    fun permutate(point: Point): Int {
        var ret = 1e9.toInt()
        for (num in 1..6) {
            val card = mutableListOf<Point>()
            for (i in 0..3) {
                for (j in 0..3) {
                    if (board[i][j] == num) {
                        card.add(Point(i, j, 0))
                    }
                }
            }
            if (card.isEmpty()) continue
            val one = bfs(point, card[0]) + bfs(card[0], card[1]) + 2
            val two = bfs(point, card[1]) + bfs(card[1], card[0]) + 2

            card.forEach {
                board[it.r][it.c] = 0
            }

            ret = Math.min(ret, one + permutate(card[1]))
            ret = Math.min(ret, two + permutate(card[0]))

            card.forEach {
                board[it.r][it.c] = num
            }
        }
        if (ret == 1e9.toInt()) return 0
        return ret
    }

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        this.board = board
        return permutate(Point(r, c, 0))
    }
}

fun main() {
    val result = Solution5().solution(arrayOf(intArrayOf(1, 0, 0, 3), intArrayOf(2, 0, 0, 0), intArrayOf(0, 0, 0, 2), intArrayOf(
        3, 0, 1, 0)), 1, 0)
    println(result)
}

/*
먼저, 1~6번까지 순차적으로 탐색을 한다. 어차피 4*4 이기 때문에 완전 탐색을 해도 문제 없다.
시작 지점에서 1번 카드부터 뒤집는다고 하자. 이때 1번 카드는 2개가 있기 때문에 두 가지 경우가 생긴다.
각 경우마다 시작 지점에서 1번째 카드까지의 키보드 조작 횟수와 1번째 카드 지점에서 2번째 카드 지점까지의 조작 횟수를 더한다.
조작횟수는 bfs를 이용해서 구한다. 방향키는 단순히 4가지 방향으로 한칸씩만 이동해주면서 큐에 넣으면 되는데, ctrl키 경우는 다음 카드를 만날때까지 이동하고,
멈춘 지점을 큐에 또 넣어준다.
앞서 말한 순차, 역순 두 가지 경우를 결과의 최솟값으로 비교해준다.
결과적으로 남아있는 값을 반환해주면 된다.
*/