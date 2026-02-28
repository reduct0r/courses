package t_algo.sprint1

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val line = br.readLine() ?: return
    val n = line.trim().toInt()

    val a = IntArray(n)
    for (i in 0 until n) {
        a[i] = i + 1
    }

    for (i in 2 until n) {
        val mid = i / 2
        val temp = a[i]
        a[i] = a[mid]
        a[mid] = temp
    }

    val sb = StringBuilder()
    for (i in 0 until n) {
        sb.append(a[i])
        if (i < n - 1) sb.append(" ")
    }
    println(sb)
}