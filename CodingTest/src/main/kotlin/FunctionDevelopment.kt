// 기능개발
// https://school.programmers.co.kr/learn/courses/30/lessons/42586?language=kotlin
class FunctionDevelopment {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        // 며칠이 걸리는지 계산
        val remain = arrayListOf<Int>()
        progresses.forEachIndexed { index, it ->
            remain.add(Math.ceil((100.0 - it) / speeds[index]).toInt())
        }
        // i >= i+1 인 경우 count++
        // i < i+1 인 경우 count x for문 break
        val answer = arrayListOf<Int>()
        var index = 0
        while (index < remain.size) {
            println(index)
            var count = 0
            for (i in index until remain.size) {
                if (remain[index] < remain[i]) {
                    answer.add(count)
                    index = i
                    break
                } else {
                    count++
                    if (i == remain.lastIndex) {
                        answer.add(count)
                        index = remain.size
                        break
                    }
                    continue
                }
            }
        }
        return answer.toIntArray()
    }
}

fun main(args: Array<String>) {
    val progresses = intArrayOf(93, 30, 55)
    val speeds = intArrayOf(1, 30, 5)
    val solution = FunctionDevelopment().solution(progresses, speeds)
    solution.forEach {
        print("$it ")
    }
}

//- 진도가 100% > 서비스 반영
//- 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포
//- 각 배포마다 몇 개의 기능이 배포되는지?
//
//93 30 55
//1 30 5
//
//1. (100-93)/1 = 7
//2. (100-30)/30 = 2.333 > 3
//3. (100-55)/5 = 9
//
//[7, 3, 9]
//1) for문 돌다가 i > i+1 break
//2) 한턴마다 count >  2, 1
//
//95, 90, 99, 99, 80, 99
//1, 1, 1, 1, 1, 1
//
//5 10 1 1 20 1
//1, 3, 2
//
//90, 98, 97, 96, 98
//1 1 1 1 1
//
//10 2 3 4 2