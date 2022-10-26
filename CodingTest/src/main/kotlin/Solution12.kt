// https://www.acmicpc.net/problem/1935

class Solution12 {
    val num = mutableListOf<Double>()
    fun oper(op: Char, a: Double, b: Double): Double {
        val x = a
        val y = b
        return when(op) {
            '+' -> x+y
            '-' -> x-y
            '*' -> x*y
            else -> x/y
        }
    }
    fun solution() {
        val n = readln().toInt()
        val li = readln().toMutableList()
        repeat(n) {
            num.add(readln().toDouble())
        }
        val stack = mutableListOf<Double>()
        li.forEach {
            if (it in listOf('+', '-' ,'*', '/')) {
                val a = stack.removeLast()
                val b = stack.removeLast()
                stack.add(oper(it, b, a))
            } else {
                stack.add(num[it.code-65])
            }
        }
        println("${String.format("%.2f", stack[stack.lastIndex])}")
    }
}

fun main() {
    Solution12().solution()
}