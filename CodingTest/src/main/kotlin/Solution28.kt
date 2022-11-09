// https://www.acmicpc.net/problem/8980

class Solution28(val n: Int, val c: Int) {
    val boxes = mutableListOf<Box>()
    val truck = Array(n+1) { c }
    fun solution() {
        repeat(readln().toInt()) {
            val (a, b, w) = readln().split(" ").map { it.toInt() }
            boxes.add((Box(a,b,w)))
        }
        boxes.sortWith(compareBy({it.b}, {it.a}))
        var result = 0
        for (i in 0 until boxes.size) {
            var min = c
            val box = boxes[i]
            for (j in box.a until box.b) {
                min = Math.min(truck[j], min)
            }
            min = Math.min(min, box.w)
            result += min
            for (j in box.a until box.b) {
                truck[j] -= min
            }
        }
        println(result)
    }
}
data class Box(
    val a: Int,
    val b: Int,
    val w: Int
)
fun main() {
    val (n, c) = readln().split(" ").map { it.toInt() }
    Solution28(n, c).solution()
}

/*
1. 도착지점, 출발지점 순으로 오름차순 정렬을 한다.
2. truck에는 [c,c,c,c] 최대 용량으로 초기화 한다.
3. 정렬된 boxes 리스트를 하나씩 돌면서 출발지점부터 도착지점까지 실을 수 있는 박스의 개수를 빼주고, 그만큼 result에 더한다.
*/