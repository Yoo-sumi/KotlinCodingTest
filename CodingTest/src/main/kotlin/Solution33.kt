// https://www.acmicpc.net/problem/12904

class Solution33(val s: String, val t: String) {
    var result = 0
    fun dfs(str: List<String>) {
        if (str.isEmpty()) return
        if (str.size < s.length) return
        if (str.joinToString("") == s) {
            result = 1
            return
        }
        if (str[str.lastIndex] == "A") {
            dfs(str.filterIndexed { index, c -> index!=str.lastIndex }.toMutableList())
        }
        if (str[str.lastIndex] == "B") {
            dfs(str.reversed().filterIndexed { index, c -> index > 0 }.toMutableList())
        }
    }
    fun solution() {
        dfs(t.map { it.toString() })
        println(result)
    }
}

fun main() {
    val s = readln().trim()
    val t = readln().trim()
    Solution33(s,t).solution()
}

// 거꾸로 t -> s로 dfs 돌리면 된다.
// 더해주는 대신 빼준다.