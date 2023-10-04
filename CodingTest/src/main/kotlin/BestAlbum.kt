// https://school.programmers.co.kr/learn/courses/30/lessons/42579
import java.util.ArrayList

class BestAlbum {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        val group = hashMapOf<String, ArrayList<Pair<Int, Int>>>()
        // 장르별로 분리(재생횟수, 인덱스=고유번호)
        genres.forEachIndexed { index, name ->
            if (!group.containsKey(name)) {
                group[name] = arrayListOf(Pair(plays[index], index))
            } else {
                group[name]?.add(Pair(plays[index], index))
            }
        }
        val sumList = arrayListOf<Pair<Int, String>>()
        // 장르별로 정렬
        // 1) 재생횟수(내림차순)
        // 2) 인덱스(오름차순)
        group.forEach { key, value ->
            value.sortWith(
                compareBy (
                    {-it.first},
                    {it.second}
                )
            )
            sumList.add(Pair(value.sumOf { it.first }, key))
        }
        // 장르별 재생횟수 합계(내림차순 정렬)
        sumList.sortWith(
            compareBy { -it.first }
        )
        // 장르별로 2개씩 선별
        // 1개일땐 1개만 선별
        val answer = arrayListOf<Int>()
        sumList.forEach {
            val key = it.second
            var limit = group[key]?.size ?: 0
            if (group[key]!!.size > 1) limit = 2
            for (i in 0 until limit) {
                answer.add(group[key]?.get(i)?.second ?: return@forEach)
            }
        }
        return answer.toIntArray()
    }
}

fun main(args: Array<String>) {
    val genres = arrayOf("classic", "classic", "classic", "classic", "pop")
    val plays = intArrayOf(50, 60, 100, 30, 8000)
    val solution = BestAlbum().solution(genres, plays)

    solution.forEach {
        print("$it ")
    }
}


//["classic", "pop", "classic", "classic", "pop"]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]
//
//classic : 500 150 800 => 1450
//
//pop: 600 2500 => 3100
//
//1. classic, pop 정렬 > 1) 재생횟수 DESC, 2) 고유번호 ASC
//2. 선별
//조건 1) 장르별 sum 비교
//조건 2) 각 장르에서 2개씩 선별
//- 곡의 개수가 1개이면 1개만