// https://www.acmicpc.net/problem/16639

class Solution2(val n: Int) {
    val numbers = mutableListOf<Int?>()
    val oper = mutableListOf<Char>()
    var answer = -(Math.pow(2.0, 31.0).toInt())

    fun changeOper(a: Int, b: Int, char: Char): Int {
        var result = 0
        when (char) {
            '+' -> result =  a + b
            '-' -> result =  a - b
            '*' -> result =  a * b
        }
        return result
    }

    fun dfs(numList: MutableList<Int?>, operList: MutableList<Char>, count: Int) {
        if (count == 1) {
            val value = numList.filterNotNull()
            val op = operList.filter { it != ' ' }
            val re = changeOper(value[0], value[1], op[0])
            if (answer < re) {
                answer = re
            }
            return
        }
        for (i in (0 until numList.size)) {
            if (numList[i] == null) continue
            for (j in (i+1) until numList.size) {
                if (numList[j] == null) continue
                val op = operList[i]
                val before = numList[i]!!
                val before2 = numList[j]!!
                numList[j] = changeOper(before, before2,op)
                numList[i] = null
                operList[i] = ' '
                dfs(numList, operList, count - 1)
                numList[i] = before
                numList[j] = before2
                operList[i] = op
                break
            }
        }
    }

    fun solution() {
        val li = readln().toList()
        if (n == 1) {
            println(li[0])
            return
        }
        for (i in li.indices) {
            if (i % 2 == 0) {
                numbers.add(li[i].digitToInt())
            } else {
                oper.add(li[i])
            }
        }
        dfs(numbers, oper, oper.size)
        println(answer)
    }
}

fun main() {
    Solution2(readln().toInt()).solution()
}