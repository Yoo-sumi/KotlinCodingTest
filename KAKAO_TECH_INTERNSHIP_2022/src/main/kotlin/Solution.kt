class Solution {
    fun solution(survey: Array<String>, choices: IntArray): String {
        var answer = ""
        var character = listOf("RT", "CF", "JM", "AN")
        var scoreMap = mutableMapOf<String, Int>()
        survey.forEachIndexed { index, s ->
            val choice = choices[index]
            if (choice in 1..3) {
                scoreMap[s[0].toString()] = (scoreMap[s[0].toString()] ?: 0) + (4 - choice)
            } else if (choice in 5..7) {
                scoreMap[s[1].toString()] = (scoreMap[s[1].toString()] ?: 0) + (choice - 4)
            }
        }
        character.forEach {
            val (a, b) = it.toList().sorted()
            val scoreA = scoreMap[a.toString()] ?: 0
            val scoreB = scoreMap[b.toString()] ?: 0
            if (scoreA >= scoreB) {
                answer += a
            } else if (scoreA < scoreB) {
                answer += b
            }
        }
        return answer
    }
}

fun main() {
    val answer = Solution().solution(arrayOf("AN", "CF", "MJ", "RT", "NA"), intArrayOf(5, 3, 2, 7, 5))
    println(answer)
}