// https://www.acmicpc.net/problem/2036

class Solution32(val n: Int) {
    fun solution() {
        val plus = mutableListOf<Long>()
        val minus = mutableListOf<Long>()
        var one = mutableListOf<Long>()
        var zero = mutableListOf<Long>()
        repeat(n) {
            val v = readln().toLong()
            if (v < 0) {
                minus.add(v)
            } else if (v > 1) {
                plus.add(v)
            } else if (v == 0L) {
                zero.add(v)
            } else {
                one.add(v)
            }
        }
        plus.sortDescending()
        minus.sort()
        var result = 0L
        for (i in 0 until plus.size step 2) {
            if (i+1 > plus.lastIndex) break
            result += (plus[i] * plus[i+1])
        }
        if (plus.size % 2 == 1) {
            result += plus[plus.lastIndex]
        }
        for (i in 0 until minus.size step 2) {
            if (i+1 > minus.lastIndex) break
            result += (minus[i] * minus[i+1])
        }
        if (zero.size==0 && minus.size % 2 == 1) {
            result += minus[minus.lastIndex]
        }
        result += one.size.toLong()
        println(result)
    }
}

fun main() {
    Solution32(readln().toInt()).solution()
}

/*
여기서 생각해야 될 포인트는
1. 양수 양수는 곱하고
2. 양수와 1은 곱하지 않고 각각 더한다. 1*2 < 1+2
3. 1이 여러개일 수 있다.
4. 0이 있다면 음수와 곱해준다. -1*0 > -1 + 0
*/