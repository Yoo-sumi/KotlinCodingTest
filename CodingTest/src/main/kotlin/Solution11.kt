// https://www.acmicpc.net/problem/1918

class Solution11 {
    fun getScore(c: Char): Int {
        return when(c) {
            '+', '-' -> 1
            '*', '/' -> 2
            else -> 0
        }
    }
    fun solution() {
        val li = readln().toList()
        val stack = mutableListOf<Char>()
        val result = mutableListOf<Char>()
        for (i in li.indices) {
            if (li[i] in 'A'..'Z') {
                result.add(li[i])
            }
            else {
                if (li[i] == '(') stack.add(li[i])
                else if (li[i] == ')') {
                    while (stack.isNotEmpty() && stack[stack.lastIndex] != '(') {
                        result.add(stack.removeLast())
                    }
                    if (stack.isNotEmpty()) {
                        stack.removeLast()
                    }
                } else {
                    while (stack.isNotEmpty() && getScore(stack[stack.lastIndex]) >= getScore(li[i])) {
                        result += stack.removeLast()
                    }
                    stack.add(li[i])
                }
            }
        }
        while (stack.isNotEmpty()) {
            result.add(stack.removeLast())
        }
        result.forEach {
            print(it)
        }
    }
}

fun main() {
    Solution11().solution()
}