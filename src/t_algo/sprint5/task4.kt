package t_algo.sprint5

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val s = reader.readLine() ?: ""
    if (s.isEmpty()) return

    val n = s.length
    val ss = s + s

    var i = 0
    var j = 1
    var k = 0

    while (i < n && j < n && k < n) {
        val a = ss[i + k]
        val b = ss[j + k]

        if (a == b) {
            k++
        } else {
            if (a > b) {
                i += k + 1
            } else {
                j += k + 1
            }
            if (i == j) j++
            k = 0
        }
    }

    val start = if (i < n) i else j
    val result = StringBuilder()
    for (idx in 0 until n) {
        result.append(ss[start + idx])
    }

    print(result.toString())
}
