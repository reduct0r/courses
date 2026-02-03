package practice

import java.util.StringTokenizer

fun main() {
    val reader = System.`in`.bufferedReader()

    val nLine = reader.readLine() ?: return
    val n = nLine.trim().toInt()

    val aLine = reader.readLine() ?: return
    val st = StringTokenizer(aLine)

    val a = IntArray(n)
    val counts = mutableMapOf<Int, Int>()

    var minVal = Int.MAX_VALUE
    var maxVal = Int.MIN_VALUE

    for (i in 0 until n) {
        a[i] = st.nextToken().toInt()
        counts[a[i]] = counts.getOrDefault(a[i], 0) + 1
        if (a[i] < minVal) minVal = a[i]
        if (a[i] > maxVal) maxVal = a[i]
    }

    val result = IntArray(n)
    for (i in 0 until n) {
        val current = a[i]
        val count = counts[current] ?: 1

        var steps = n - count
        if (count == 1 && current != minVal && current != maxVal) {
            steps += 1
        } else if (count == 1 && n > 3) {
            steps = n - 1
        }

        result[i] = steps
    }

    println(result.joinToString(" "))
}