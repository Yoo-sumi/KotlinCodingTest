// https://www.acmicpc.net/problem/2632

class Solution4(val k: Int) {
    fun solution() {
        val case1 = Array(k) { 0 }
        val case2 = Array(k) { 0 }
        var result = 0


        val (m, n) = readln().split(" ").map { it.toInt() }
        val left = mutableListOf<Int>()
        repeat(m) {
            left.add(readln().toInt())
        }
        val temp1 = left.sum()
        var flag1 = true
        var flag2 = true
        if (temp1 < k) {
            case1[temp1]++
        } else if (temp1 == k) {
            flag1 = false
            result++
        }
        left += left.map { it }
        left.removeLast()
        val right = mutableListOf<Int>()
        repeat(n) {
            right.add(readln().toInt())
        }
        val temp2 = right.sum()
        if (temp2 < k) {
            case2[temp2]++
        } else if (temp2 == k) {
            flag2 = false
            result++
        }
        right += right.map { it }
        right.removeLast()
        val prefixLeft = Array(left.size + 1) { 0 }
        for (i in 0 until left.size) {
            prefixLeft[i+1] = prefixLeft[i] + left[i]
        }
        val prefixRight = Array(right.size + 1) { 0 }
        for (i in 0 until right.size) {
            prefixRight[i+1] = prefixRight[i] + right[i]
        }
        for (i in 0 until  m ) {
            for (j in i+1 .. i+m) {
                val value = prefixLeft[j] - prefixLeft[i]
                if (value < k && temp1!=value) {
                    case1[value]++
                }
                if (value == k) {
                    if (!flag1 && value == temp1) continue
                    result++
                }
            }
        }
        for (i in 0 until  n) {
            for (j in i+1..i+n) {
                val value = prefixRight[j] - prefixRight[i]
                if (value < k && temp2!=value) {
                    case2[value]++
                }
                if (value == k) {
                    if (!flag2 && value == temp2) continue
                    result++
                }
            }
        }
        for (i in 1 until k) {
            result += case1[i] * case2[k-i]
        }
        println(result)
    }
}

fun main() {
    Solution4(readln().toInt()).solution()
}