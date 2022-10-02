//두 큐 합 같게 만들기
class Solution2 {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var answer = 0
        var sum1 = queue1.sum().toULong()
        var sum2 = queue2.sum().toULong()
        if (((sum1 + sum2) % 2.toULong()) == 1.toULong()) return -1
        val deque1 = ArrayDeque<ULong>()
        val deque2 = ArrayDeque<ULong>()
        queue1.forEach { deque1.add(it.toULong()) }
        queue2.forEach { deque2.add(it.toULong()) }
        repeat(3 * queue1.size) {
            if (sum1 > sum2) {
                deque2.addLast(deque1.removeFirst())
                sum1 -= deque2.last()
                sum2 += deque2.last()
            } else if (sum1 < sum2){
                deque1.addLast(deque2.removeFirst())
                sum1 += deque1.last()
                sum2 -= deque1.last()
            } else {
                return answer
            }
            answer++
        }
        return -1
    }
}

fun main() {
    val answer = Solution2().solution(intArrayOf(3, 2, 7, 2), intArrayOf(4, 6, 5, 1))
    println(answer)
}