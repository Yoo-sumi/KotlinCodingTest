class Solution1 {

    var hashMap = mutableMapOf<String, Int>()

    private fun combination(order: String, others: String, count: Int) {
        if (count == 0) {
            hashMap[order] = (hashMap[order] ?: 0) + 1
            return
        }
        repeat(others.length) {
            combination(order + others[it], others.substring(it + 1), count - 1)
        }
    }

    fun solution(orders: Array<String>, course: IntArray): List<String> {
        var answer = mutableListOf<String>()

        val sorted = orders.map {
            it.toList().sorted().joinToString(separator = "")
        }
        course.forEach { count ->
            sorted.forEach {
                combination("", it, count)
            }
        }
        val grouping = hashMap.toList().groupBy { it.first.length }
        grouping.forEach {
            val sorted = it.value.filter { it.second > 1 }.sortedBy { -it.second }
            sorted.forEachIndexed { index, pair ->
                if (index != 0 && sorted[index - 1].second != pair.second) return@forEach
                answer.add(pair.first)
            }
        }
        return answer.sorted()
    }
}

fun main() {
    val answer = Solution1().solution(arrayOf("XYZ", "XWY", "WXA"), intArrayOf(2, 3, 4))
    println(answer)
}