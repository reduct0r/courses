package t_algo.sprint5

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val p = reader.readLine() ?: ""
    val t = reader.readLine() ?: ""

    val n = p.length
    val m = t.length

    if (n > m) {
        println(0)
        println()
        return
    }

    val z1 = getZ("$p#$t")
    val z2 = getZ(p.reversed() + "#" + t.reversed())

    val result = StringBuilder()
    var count = 0

    for (i in 0..m - n) {
        val pref = z1[n + 1 + i]
        val suff = z2[n + 1 + (m - i - n)]

        if (pref + suff >= n - 1) {
            count++
            result.append(i + 1).append(" ")
        }
    }

    println(count)
    println(result.toString().trim())
}

fun getZ(s: String): IntArray {
    val n = s.length
    val z = IntArray(n)
    var l = 0
    var r = 0
    for (i in 1 until n) {
        if (i <= r) {
            z[i] = minOf(r - i + 1, z[i - l])
        }
        while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
            z[i]++
        }
        if (i + z[i] - 1 > r) {
            l = i
            r = i + z[i] - 1
        }
    }
    return z
}
