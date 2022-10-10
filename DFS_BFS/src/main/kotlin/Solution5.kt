// https://www.acmicpc.net/problem/14888

import kotlin.math.abs

class Solution5(val n: Int) {
    var num = listOf<Int>()
    var minValue = 1e9.toInt()
    var maxValue = (-1e9).toInt()
    fun dfs(index:Int, value: Int, ex: MutableList<Int>) {
        var value = value
        if (index == num.size) {
            minValue = Math.min(minValue, value)
            maxValue = Math.max(maxValue, value)
            return
        }
        for (i in 0..3) {
            if (ex[i] > 0) {
                ex[i] -= 1
                val temp = value
                if (i == 0) {
                    value += num[index]
                } else if (i == 1) {
                    value -= num[index]
                } else if (i == 2) {
                    value *= num[index]
                } else {
                    value = abs(value) / num[index]
                    if (temp < 0) {
                       value *= -1
                    }
                }
                dfs(index+1, value, ex)
                value = temp
                ex[i] += 1
            }
        }
    }

    fun solution() {
        num = readln().split(" ").map { it.toInt() }
        val ex = readln().split(" ").map { it.toInt() }
        dfs(1, num[0], ex.map { it }.toMutableList())
        println(maxValue)
        println(minValue)
    }
}

fun main() {
    Solution5(readln().toInt()).solution()
}

// dfs를 이용한다.