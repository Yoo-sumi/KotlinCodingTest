// 광고 삽입
class Solution4ex {
    fun deconvert(time: Int): String {
        var time = time
        val hh = time / (60 * 60)
        val temp = time - (hh * 60 * 60)
        val mm = temp / 60
        val ss = temp - (mm * 60)
        return "%02d:%02d:%02d".format(hh, mm, ss)
    }
    fun convert(time: String): Int {
        val a = time.split(":").map { it.toInt() }
        return a[0] * 60 * 60 + a[1] * 60 + a[2]
    }
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        val total = convert(play_time)
        val graph = (0..total).map { 0 }.toMutableList()
        val advertise = convert(adv_time)
        if (total == advertise) return deconvert(0)
        logs.forEach {
            val li = it.split("-")
            var start = convert(li[0])
            var end = convert(li[1])
            graph[start] += 1
            graph[end] -= 1
        }
        for (i in 1 .. total) {
            graph[i] += graph[i-1]
        }
        var maxIndex = 0
        var value = 0L
        for (j in 0 until advertise) {
            value += graph[j].toLong()
        }
        var maxValue = value
        for (i in  advertise ..  total) {
            value -= graph[i - advertise]
            value += graph[i]
            if (value > maxValue) {
                maxValue = value
                maxIndex = i - advertise + 1
            }
        }
        return deconvert(maxIndex)
    }
}

fun main() {
    val answer = Solution4ex().solution("99:59:59", "25:00:00", arrayOf("69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"))
    println(answer)
}