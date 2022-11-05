// https://www.acmicpc.net/status?user_id=hugbee&problem_id=1043&from_mine=1

class Solution23(val n: Int, val m: Int) {
    val parent = (0..n).map { it }.toMutableList()
    var result = 0

    fun findParent(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = findParent(parent[x])
        }
        return parent[x]
    }

    fun union(a: Int, b: Int) {
        val parentA = findParent(a)
        val parentB = findParent(b)
        if (parentA != parentB) {
            if (parentA > parentB) parent[parentA] = parentB
            else parent[parentB] = parentA
        }
    }

    fun solution() {
        val truth = readln().trim().split(" ").map { it.toInt() }
        val truthPeopleList = truth.filterIndexed { index, i -> index > 0 }
        val peopleList = mutableListOf<List<Int>>()
        repeat(m) {
            val li = readln().trim().split(" ").map { it.toInt() }
            peopleList.add(li.filterIndexed { index, i -> index > 0 })
            for (p in 1 until peopleList[it].size) {
                union(peopleList[it][p-1], peopleList[it][p])
            }
        }
        val temp = truthPeopleList.map { findParent(it) }
        for (people in peopleList) {
            var flag = true
            for (p in people) {
                if (findParent(p) in temp) {
                    flag = false
                    break
                }
            }
            if (flag) result++
        }
        println(result)
    }
}

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    Solution23(n, m).solution()
}

// union-find로 풀었다.
// 여기서 중요한 것은 findParnet()를 하면서 parent 관계가 새롭게 정리된다는 것이다.
// 처음에 그냥 parent[]로 부모를 가져와서 애를 먹었다.
// 처음 parent를 찾을때는 findParnet()로 찾아야 한다.
// 그리고 여기서 한번 더 처리해줘야 할 것은 진실을 알고있는 사람의 부모에 속하면 카운트를 하지 않는다는 것을 기준으로 해야한다.
// 단순히 진실을 알고있는 사람으로 판단하면 안된다.