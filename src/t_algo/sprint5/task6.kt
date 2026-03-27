package t_algo.sprint5

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val s = reader.readLine() ?: ""
    val n = s.length
    if (n == 0) {
        println(0)
        return
    }

    val d1 = IntArray(n)
    var l = 0
    var r = -1
    for (i in 0 until n) {
        var k = if (i > r) 1 else minOf(d1[l + r - i], r - i + 1)
        while (i + k < n && i - k >= 0 && s[i + k] == s[i - k]) k++
        d1[i] = k--
        if (i + k > r) {
            l = i - k
            r = i + k
        }
    }

    val d2 = IntArray(n)
    l = 0
    r = -1
    for (i in 0 until n) {
        var k = if (i > r) 0 else minOf(d2[l + r - i + 1], r - i + 1)
        while (i + k < n && i - k - 1 >= 0 && s[i + k] == s[i - k - 1]) k++
        d2[i] = k--
        if (i + k > r) {
            l = i - k - 1
            r = i + k
        }
    }

    var total: Long = 0
    for (i in 0 until n) {
        total += d1[i].toLong()
        total += d2[i].toLong()
    }
    println(total)
}
