// 다리를 지나는 트럭
// https://school.programmers.co.kr/learn/courses/30/lessons/42583?language=kotlin
class BridgeTruck {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 0
        // 다리 길이만큼 큐에 0 push
        val q = ArrayDeque<Int>()
        for (i in 0 until bridge_length) {
            q.add(0)
        }
        val truckList = truck_weights.toMutableList()
        while (truckList.isNotEmpty()) {
            q.removeFirst()
            if (truckList.first() + q.sum() > weight) {
                q.addLast(0)
            } else {
                q.addLast(truckList.removeFirst())
            }
            answer++
        }
        answer += q.size
        return answer
    }
}

fun main(args: Array<String>) {
    val bridge_length = 2
    val weight = 10
    val truck_weights = intArrayOf(7,4,5,6)
    val solution = BridgeTruck().solution(bridge_length, weight, truck_weights)
    print(solution)
}

//- 모든 트럭이 다리를 건너려면 최소 몇 조가 걸리는지?
//- 최대 개수 = bridge_length
//- 최대 무게 = weight
//
//bridge_length = 2
//weight = 10
//
//[7 4 5 6]
//
//1) bridge_length 만큼 스택 0으로 채우기
//2) 스택 맨 앞은 pop, 트럭 하나 꺼내서 스택 맨 뒤에 push
//1. 꺼내기 전에 트럭 합계 비교 > 무게 초과되면 0 push
//2. 안되면 트럭 꺼내서 push
//3) 트럭 대기 배열이 비었으면 큐가 empty 될때까지 += count
//1. count += 큐 사이즈
//[0 0]
//[0 7]1
//[7 0]2
//[0 4]3
//[4 5]4
//[5 0]5
//[0 6]6
//[6]7
//[]8
//
//= 8초