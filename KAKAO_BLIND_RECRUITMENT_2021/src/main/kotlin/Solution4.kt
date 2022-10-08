// 광고 삽입
class Solution4 {
    fun decodeConvert(time: Int): String {
        var mm = time / 60
        var ss = time - (mm * 60)
        var hh = mm / 60
        mm -= (hh * 60)
        return String.format("%02d:%02d:%02d", hh,mm,ss)
    }
    fun convert(time: String): Int {
        var (hh, mm, ss) = time.split(":").map { it.toInt() }
        var result = ss
        result += mm * 60
        result += hh * 60 * 60
        return result
    }
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        val total = convert(play_time)
        var times = (0..total).map { 0 }.toMutableList()
        logs.forEach {
            val li = it.split("-")
            var start = convert(li[0])
            var end = convert(li[1])
            times[start] += 1
            times[end] -= 1
        }
        for (i in (1..total)) {
            times[i] += times[i-1]
        }
        val adv_time = convert(adv_time)
        var currSum = times.filterIndexed { index, i -> index in (0 until  adv_time) }.sum().toLong()
        var maxSum = currSum
        var maxIdx = 0
        for (i in (adv_time..total)) {
            currSum = currSum + times[i] - times[i - adv_time]
            if (currSum > maxSum) {
                maxSum = currSum
                maxIdx = i - adv_time + 1
            }
        }
        return decodeConvert(maxIdx)
    }
}
/*class Solution4 {
    fun decodeConvert(time: Int): String {
        var mm = time / 60
        var ss = time - (mm * 60)
        var hh = mm / 60
        mm -= (hh * 60)
        return String.format("%02d:%02d:%02d", hh,mm,ss)
    }
    fun convert(time: String): Int {
        var (hh, mm, ss) = time.split(":").map { it.toInt() }
        var result = ss
        result += mm * 60
        result += hh * 60 * 60
        return result
    }
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        var answer = 0
        var resultTime = 0
        val total = convert(play_time)
        val adv_time = convert(adv_time)
        if (adv_time >= total) return "00:00:00"
        val li = logs.map { it.split("-") }
        val newLog = li.map { listOf(convert(it[0]), convert(it[1])) }.sortedBy { -it[0] }.toMutableList()
        var index = 0
        while (index < newLog.size) {
            val time = newLog[index][0]
            val end = time + adv_time
            var addTime = 0
            newLog.forEach {
                val startTime = it[0]
                val endTime = it[1]
                if ((endTime in 0 until time) || (startTime in (end + 1)..total)) return@forEach
                if (startTime >= time && endTime <= end) {
                    addTime += (endTime - startTime)
                } else if (startTime < time && endTime <= end) {
                    addTime += (endTime - time)
                } else if (startTime >= time && endTime > end) {
                    addTime += (end - startTime)
                } else {
                    addTime += (end - time)
                }
            }
            if (addTime >= answer) {
                resultTime = index
                answer = Math.max(answer, addTime)
            }
            index++
        }
        return decodeConvert(newLog[resultTime][0])
    }
}*/

fun main() {
    val answer = Solution4().solution("02:03:55", "00:14:15", arrayOf("01:20:15-01:45:14", "00:25:50-00:48:29", "00:40:31-01:00:00", "01:37:44-02:02:30", "01:30:59-01:53:29"))
    println(answer)
}