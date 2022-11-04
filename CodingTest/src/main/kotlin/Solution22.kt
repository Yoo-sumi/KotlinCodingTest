// https://www.acmicpc.net/problem/2138

class Solution22(val n: Int) {
    var answer = 1e9.toInt()
    lateinit var list: List<Int>
    lateinit var light: MutableList<Int>
    lateinit var result: List<Int>

    fun turnOnOff(index: Int) {
        if (index-1 >= 0) {
            if (light[index-1] == 0) light[index-1] = 1
            else light[index-1] = 0
        }
        if (index+1 < n) {
            if (light[index+1] == 0) light[index+1] = 1
            else light[index+1] = 0
        }
        if (light[index] == 0) light[index] = 1
        else light[index] = 0
    }

    fun dfs(index: Int, count: Int) {
        if (index==n) {
            var flag = true
            for (i in 0 until n) {
                if (light[i] != result[i]) {
                    flag = false
                    return
                }
            }
            answer = Math.min(answer, count)
            return
        }

        if (light[index-1] == result[index-1]) {
            dfs(index+1, count)
        } else {
            turnOnOff(index)
            dfs(index+1, count+1)
            turnOnOff(index)
        }

    }

    fun solution() {
        val li = readln().map { it.digitToInt() }.toMutableList()
        list = li.map { it }
        light = li.map { it } as MutableList<Int>
        result = readln().map { it.digitToInt() }.toList()
        dfs(1, 0)
        turnOnOff(0)
        dfs(1, 1)
        turnOnOff(0)
        if (answer == 1e9.toInt()) {
            println(-1)
        } else {
            println(answer)
        }
    }
}

fun main() {
    Solution22(readln().toInt()).solution()
}

/*
처음에 재귀로 풀었더니 시간초과가 났다.
이 문제는 2가지 케이스로 나누어 풀어야한다.
1. 0번째 전구를 키고 시작하는 경우
2. 0번째 전구를 키지않고 시작하는 경우
3.  i번째 전구의 상태에 마지막으로 영향을 미치는 스위치는 i+1번째 스위치이다.
이것을 이용하여 만약 i-1 번째 전구가 목표로하는 전구의 상태가 아니면 누르는 식으로 재귀 함수를 진행 시킨다.
*/