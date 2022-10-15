// https://blog.naver.com/hishjm/222654943737

import java.util.PriorityQueue
import kotlin.math.abs

class Solution1(val n: Int) {
    val parent = MutableList(n) { it }

    fun findParent(num: Int): Int {
        if (parent[num] != num) {
            parent[num] = findParent(parent[num])
        }
        return parent[num]
    }

    fun union(a: Int, b: Int) {
        val parentA = findParent(a)
        val parentB = findParent(b)
        if (parentA == parentB) return
        if (parentA < parentB) {
            parent[parentB] = parentA
        } else {
            parent[parentA] = parentB
        }
    }

    fun solution(): Int {
        val x = mutableListOf<Planet>()
        val y = mutableListOf<Planet>()
        val z = mutableListOf<Planet>()

        repeat(n) {
            val (a, b, c) = readln().split(" ").map { it.toInt() }
            x.add(Planet(a, it))
            y.add(Planet(b, it))
            z.add(Planet(c, it))
        }

        x.sortBy { it.point }
        y.sortBy { it.point }
        z.sortBy { it.point }

        val edges = PriorityQueue<Edge>(Comparator { o1, o2 -> o1.cost - o2.cost })

        for (i in 0 until n - 1) {
            edges.add(Edge(abs(x[i].point - x[i+1].point), x[i].index, x[i+1].index))
            edges.add(Edge(abs(y[i].point - y[i+1].point), y[i].index, y[i+1].index))
            edges.add(Edge(abs(z[i].point - z[i+1].point), z[i].index, z[i+1].index))
        }

        var result = 0
        while (edges.isNotEmpty()) {
            val it = edges.poll()
            if (findParent(it.a) != findParent(it.b)) {
                union(it.a, it.b)
                result += it.cost
            }
        }
        return result
    }
}
data class Planet(
    val point: Int,
    val index: Int
)
data class Edge(
    val cost: Int,
    val a: Int,
    val b: Int
)

fun main() {
    val result = Solution1(readln().trim().toInt()).solution()
    println(result)
}

// 크루스칼 알고리즘을 이용하면 된다.
// edges는 어차피 가장 짧은 거리만 보면 되기 때문에 각각 모두 정렬한다음에 한바퀴만 돌면 된다.(차이 구하기)