package practice

fun main() {
    val reader = System.`in`.bufferedReader()
    val t = reader.readLine().toInt()

    for (i in 0 until t) {
        val s = reader.readLine().trim()
        val n = s.length
        if (n == 0) {
            println(0)
            continue
        }
        val ss = s + s
        var maxStreak = 0
        var curr = 0
        for (c in ss) {
            if (c == '1') {
                curr++
                if (curr > maxStreak) maxStreak = curr
            } else {
                curr = 0
            }
        }
        val maxLen = minOf(maxStreak, n)
        val ans = if (maxLen == n) {
            n * n
        } else {
            val temp = maxLen + 1
            temp * temp / 4
        }
        println(ans)
    }
}