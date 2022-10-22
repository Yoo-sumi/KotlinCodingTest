// https://www.acmicpc.net/problem/2003

class Solution5(val n: Int, val m: Int) {
    fun solution() {
        var left = 0
        var right = 0
        var sum = 0
        var count = 0
        val li = readln().split(" ").map { it.toInt() }
        while (true) {
            if (sum >= m) {
                sum -= li[left++]
            } else if (right == n) {
                break
            } else {
                sum += li[right++]
            }

            if (sum == m) count++
        }
        println(count)
    }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    Solution5(n, m).solution()
}