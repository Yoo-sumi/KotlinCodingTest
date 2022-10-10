// https://school.programmers.co.kr/learn/courses/30/lessons/60058?language=kotlin
class Solution4 {
    fun divide(p: String): List<String> {
        var score = 0
        var u = ""
        var v = ""
        for (i in p.indices) {
            if (p[i] == '(') {
                score++
            } else {
                score--
            }
            u += p[i]
            if (score == 0) {
                v = p.filterIndexed { index, c ->
                    index > i
                }
                break
            }
        }
        return listOf(u, v)
    }

    fun isRight(p: String): Boolean {
        val q = ArrayDeque<Char>()
        p.forEach {
            if (it == '(') {
                q.add(it)
            } else {
                if (q.isEmpty()) return false
                if (q.last() == '(') {
                    q.removeLast()
                } else {
                    return false
                }
            }
        }
        if (q.isNotEmpty()) return false
        return true
    }

    fun solution(p: String): String {
        var answer = ""
        if (p.isEmpty() || isRight(p)) return p
        var (u, v) = divide(p)
        if (isRight(u)) {
            u += solution(v)
            answer += u
        } else {
            answer += "("
            answer += solution(v)
            answer += ")"
            u = u.substring(1, u.lastIndex)
            var temp = ""
            u.forEach {
                if (it == '(') {
                    temp += ')'
                } else {
                    temp += '('
                }
            }
            answer += temp
        }
        return answer
    }
}

fun main() {
    val result = Solution4().solution(	")(())(")
    println(result)
}

// 문자열을 분리하는 함수와
// 올바른 괄호 문자열인지 판단하는 함수를 구현한다.
// 재귀함수 로직은 문제에 나와있는대로 차례로 구현하면 된다.