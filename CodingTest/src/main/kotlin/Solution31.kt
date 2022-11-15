// https://www.acmicpc.net/problem/2212
class Solution31 {
    fun solution() {
        val n = readln().trim().toInt()
        val k = readln().trim().toInt()
        val li = readln().trim().split(" ").map { it.toInt() }.toMutableList()
        li.sort()
        val dis = MutableList(n-1) { 0 }
        for (i in 0 until n-1) {
            dis[i] = li[i+1] - li[i]
        }
        dis.sort()
        var result = 0
        for (i in 0 until n-k) {
            result += dis[i]
        }
        println(result)
    }
}

fun main() {
    Solution31().solution()
}

/*
한 줄로 일단 정렬한다.
서로의 사이 거리를 뽑아낸다.
또 정렬한다.
n-k만큼 더해준다.
*/