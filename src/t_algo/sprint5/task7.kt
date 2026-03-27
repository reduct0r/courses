package t_algo.sprint5

import java.util.*
import java.io.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val n = reader.readLine()?.trim()?.toInt() ?: 0
    val a = LongArray(n)
    val st1 = StringTokenizer(reader.readLine() ?: "")
    for (i in 0 until n) a[i] = st1.nextToken().toLong()

    val m = reader.readLine()?.trim()?.toInt() ?: 0
    val b = LongArray(m)
    val st2 = StringTokenizer(reader.readLine() ?: "")
    for (i in 0 until m) b[i] = st2.nextToken().toLong()

    val random = Random(42)
    val weights = LongArray(100001)
    for (i in 0..100000) {
        weights[i] = random.nextLong()
    }

    val hashesB = mutableSetOf<Long>()
    for (i in 0 until m) {
        var currentHash = 0L
        for (j in i until m) {
            currentHash += weights[b[j].toInt()]
            hashesB.add(currentHash)
        }
    }

    var maxLen = 0
    for (i in 0 until n) {
        var currentHash = 0L
        for (j in i until n) {
            currentHash += weights[a[j].toInt()]
            val len = j - i + 1
            if (len > maxLen && hashesB.contains(currentHash)) {
                maxLen = len
            }
        }
    }

    println(maxLen)
}
