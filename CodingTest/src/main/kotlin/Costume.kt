// https://school.programmers.co.kr/learn/courses/30/lessons/42578?language=kotlin
import java.util.ArrayList

class Costume {
    fun solution(clothes: Array<Array<String>>): Int {
        // 카테고리별로 분류[카테고리:의상]
        val category = hashMapOf<String, ArrayList<String>>()
        clothes.forEach {
            if (category.containsKey(it.get(1))) {
                category[it.get(1)]?.add(it.get(0))
            } else {
                category[it.get(1)] = arrayListOf(it.get(0))
            }
        }
        // 조합 경우의 수 구하기
        var answer = 1
        category.forEach { key, value ->
            answer *= (value.size + 1)
        }

        return answer - 1
    }
}

fun main(args: Array<String>) {
    val clothes = arrayOf(arrayOf("yellow_hat", "headgear"),
        arrayOf("blue_sunglasses", "eyewear"),
        arrayOf("green_turban", "headgear"))
    val solution = Costume().solution(clothes)
    print(solution)
}

//- 각 종류별로 최대 1가지 의상만 착용
//- 하루에 최소 한개의 의상 착용
//
//조합의 수
//
//[["yellow_hat", "headgear"], ["blue_sunglasses", "eyewear"], ["green_turban", "headgear"]]
//
//headgear : yellow_hat, green_turban = > 2 + 1 = 3
//blue_sunglasses : eyewear => 1 + 1 = 3
//
//3 * 2 = 6 - 1
//
//1. 카테고리별로 의상 분리
//2. 각 카테고리별 의상 개수
//3. 모든 카테고리별 (의상개수 + 1) 곱하기 - 1
//- 카테고리별 +1은 착용의상 0인 경우를 포함하기 위해
//- 마지막 -1은 착용한 의상이 0 인 경우를 제외하기 위해.