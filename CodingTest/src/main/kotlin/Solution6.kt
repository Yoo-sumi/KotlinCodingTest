// https://www.acmicpc.net/problem/4256

class Solution6(val t: Int) {
    lateinit var preOrder: List<Int>
    lateinit var inOrder: List<Int>
    var cnt = 0
    fun postOrder(left: Int, right: Int) {
        if (left > right) return
        var nxInd = 0
        for (i in left..right) {
            if (preOrder[cnt] == inOrder[i]) {
                nxInd = i
                cnt++
                break
            }
        }
        postOrder(left, nxInd - 1)
        postOrder(nxInd+1, right)
        print("${inOrder[nxInd]} ")
    }
    fun solution() {
        repeat(t) {
            cnt = 0
            val n = readln().trim().toInt()
            preOrder = readln().trim().split(" ").map { it.toInt() }
            inOrder = readln().trim().split(" ").map { it.toInt() }
            postOrder(0, n-1)
            println()
        }
    }
}

fun main() {
    Solution6(readln().toInt()).solution()
}