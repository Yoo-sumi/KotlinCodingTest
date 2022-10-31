// https://www.acmicpc.net/problem/1135

class Solution18(val n: Int) {
    val node = Array(n) { mutableListOf<Int>() }
    var child = Array(n) { 0 }
    fun dfs(x: Int) {
        val childNode = mutableListOf<Int>()
        if (node[x].isEmpty()) {
            child[x] = 0
        } else {
            for (c in node[x]) {
                dfs(c)
                childNode.add(child[c])
            }
            childNode.sortDescending()
            var count = 0
            for (i in childNode.indices) {
                count = Math.max(childNode[i] + i + 1, count)
            }
            child[x] = count
        }
    }
    fun solution() {
        val li = readln().split(" ").map { it.toInt() }
        for (i in 1 until li.size) {
            node[li[i]].add(i)
        }
        dfs(0)
        println(child[0])
   }
}

fun main() {
    Solution18(readln().toInt()).solution()
}

/*
1. 자식 노드들의 자식이 많을수록 전화를 빨리 걸어야 한다.
2. 자식 노드들의 자식들을 배열에 넣어 자식이 많은순으로 정렬한다.
3. 제일 높은값이 정답이다.
for문을 돌면서 인덱스만큼 더해주는 이유는 자식이 3개가 있다면 차례대로 1분, 2분, 3분이 걸리기 때문이다.
즉, 한번에 한사람한테 전화를 걸 수 밖에 없기 때문에.
*/