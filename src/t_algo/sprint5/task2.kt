package t_algo.sprint5

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val t = reader.readLine() ?: ""
    val qStr = reader.readLine() ?: "0"
    val q = qStr.toInt()

    val out = StringBuilder()

    repeat(q) {
        val s = reader.readLine() ?: ""
        if (s.isEmpty()) {
            out.append("0\n")
        } else {
            val indices = kmpSearch(t, s)
            out.append(indices.size)
            for (idx in indices) {
                out.append(" ").append(idx)
            }
            out.append("\n")
        }
    }
    print(out)
}

fun kmpSearch(text: String, pattern: String): List<Int> {
    val m = pattern.length
    val n = text.length
    val pi = IntArray(m)

    var j = 0
    for (i in 1 until m) {
        while (j > 0 && pattern[i] != pattern[j]) {
            j = pi[j - 1]
        }
        if (pattern[i] == pattern[j]) {
            j++
        }
        pi[i] = j
    }

    val result = mutableListOf<Int>()
    j = 0
    for (i in 0 until n) {
        while (j > 0 && text[i] != pattern[j]) {
            j = pi[j - 1]
        }
        if (text[i] == pattern[j]) {
            j++
        }
        if (j == m) {
            result.add(i - m + 1)
            j = pi[j - 1]
        }
    }
    return result
}