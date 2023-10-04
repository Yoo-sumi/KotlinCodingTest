// 프로세스
// https://school.programmers.co.kr/learn/courses/30/lessons/42587?language=kotlin
class Process {
    fun solution(priorities: IntArray, location: Int): Int {
        val answer = arrayListOf<Int>()
        val q = ArrayDeque<Pair<Int, Int>>()
        priorities.forEachIndexed { index, it ->
            q.addLast(Pair(it, index))
        }
        while(q.isNotEmpty()) {
            val focus = q.removeFirst()
            var flag = false
            if (q.isEmpty()) {
                answer.add(focus.second)
                break
            }
            for (i in 0 until q.size) {
                if (focus.first < q[i].first) {
                    flag = true
                    break
                }
            }
            if (flag) {
                q.addLast(focus)
            } else {
                answer.add(focus.second)
            }
        }
        for (i in 0 until answer.size) {
            if (answer[i] == location) {
                return i + 1
            }
        }

        return -1
    }
}

fun main(args: Array<String>) {
    val priorities = intArrayOf(1, 1, 9, 1, 1, 1)
    val location = 0
    val solution = Process().solution(priorities, location)
    print(solution)
}

//- 특정 프로세스가 몇 번째로 실행되는지?
//
//1) 큐에서 하나 꺼냄
//2) 큐에 대기중인 프로세스 중 우선순위가 더 높은 프로세스가 있다면 방금 꺼낸 프로세스 다시 넣기
//3) 없다면 실행(다시 넣지 않음)
//
//0 1 2 3
//2 1 3 2
//
//1 3 2 2
//3 2 2 1 > C(2)
//2 2 1 > D(3)
//2 1 > A(0)
//1 > B(1)
//
//answer = [2 3 0 1]
//location = 2
//answer[2] = 0
//answer[location] + 1
//
//1) 맨 앞에꺼 뽑고(index)
//2) index < index + 1 인 경우가 있다면 큐 맨뒤에 넣기
//3) 없다면 실행