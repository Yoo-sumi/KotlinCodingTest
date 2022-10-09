// 메뉴 리뉴얼
class Solution2ex {
    val result = hashMapOf<String, Int>()

    fun combination(now: MutableList<Char>, remain: MutableList<Char>, count: Int) {
        if (now.size == count) {
            val key = now.joinToString("")
            result[key] = (result[key] ?: 0) + 1
            return
        }
        remain.forEachIndexed { index, c ->
            val next = remain[index]
            now.add(next)
            combination(now , remain.filterIndexed { dd, c -> dd > index }.toMutableList(), count)
            now.removeLast()
        }
    }

    fun solution(orders: Array<String>, course: IntArray): List<String> {
        val answer = mutableListOf<String>()
        course.forEach { index ->
            orders.forEach {
                combination(mutableListOf(), it.toList().sorted().toMutableList(), index)
            }
        }
        val list = result.toList().groupBy { it.first.length }
        list.forEach {
            val sorted = it.value.filter { it.second > 1 }.sortedByDescending { it.second }
            sorted.forEachIndexed { index, pair ->
                if (index != 0 && sorted[index - 1].second != pair.second) return@forEach
                answer.add(pair.first)
            }
        }
        return answer.sorted()
    }
}
fun main() {
    val answer = Solution2ex().solution(arrayOf("XYZ", "XWY", "WXA"), intArrayOf(2, 3, 4))
    println(answer)
}