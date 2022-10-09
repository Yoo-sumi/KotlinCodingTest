// 파괴되지 않은 건물
class Solution1 {
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        val n = board.size
        val m = board[0].size
        val newBoard = Array(n+1) { IntArray(m+1)}
        var answer: Int = 0
        skill.forEach {
            var (type, r1, c1, r2, c2, degree) = it
            if (type == 1) {
                degree *= -1

            }
            newBoard[r1][c1] += degree
            newBoard[r2+1][c2+1] += degree
            newBoard[r2+1][c1] -= degree
            newBoard[r1][c2+1] -= degree
        }
        for (i in (0 until n)) {
            for (j in 1 until m) {
                newBoard[i][j] += newBoard[i][j-1]
            }
        }
        for (i in (1 until m)) {
            for (j in 0 until n) {
                newBoard[i][j] += newBoard[i-1][j]
            }
        }
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (newBoard[i][j] + board[i][j] > 0) {
                    answer++
                }
            }
        }
        return answer
    }
}

private operator fun IntArray.component6(): Int {
    return this[5]
}

fun main() {
    val result = Solution1().solution(arrayOf(intArrayOf(5, 5, 5, 5, 5), intArrayOf(5, 5, 5, 5, 5), intArrayOf(5, 5, 5, 5, 5), intArrayOf(5, 5, 5, 5, 5)), arrayOf(
        intArrayOf(1, 0, 0, 3, 4, 4), intArrayOf(1, 2, 0, 2, 3, 2), intArrayOf(2, 1, 0, 3, 1, 2), intArrayOf(1, 0, 1, 3, 3, 1)))
    println(result)
}
/*
val newBoard = Array(n+1) { IntArray(m+1)}
map으로 배열 생성하는 것은 느린 작업이다. 이제부터 위를 사용하자.
이 문제는 광고 삽입과 비슷한 문제다. 광고삽입은 1차원 문제였지만, 이 문제는 2차원 문제라 더 어려웠다.
아이디어는 사진으로 첨부
 */