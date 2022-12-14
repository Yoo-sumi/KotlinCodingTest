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

// 피연산자라면 결과에 push
// '(' -> 스택에 push
// ')' -> '('가 나올때까지 결과에 push
// 연산자 -> 현재 연산자보다 우선순위가 작을때까지 결과에 push하고 현재 연산자를 스택에 push
// 식을 한바퀴 돌고 스택에 연산자가 남아있다면 스택이 빌때까지 하나씩 꺼내서 결과에 push