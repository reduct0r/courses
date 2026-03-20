package t_algo.sprint4

import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val firstLine = reader.readLine() ?: return
    val st0 = StringTokenizer(firstLine)

    val n = st0.nextToken().toInt()
    val m = st0.nextToken().toInt()
    val k = st0.nextToken().toInt()

    val pref = Array(n + 1) { LongArray(m + 1) }

    for (i in 1..n) {
        val st = StringTokenizer(reader.readLine())
        for (j in 1..m) {
            val value = st.nextToken().toLong()
            pref[i][j] = value + pref[i - 1][j] + pref[i][j - 1] - pref[i - 1][j - 1]
        }
    }

    val output = StringBuilder()
    repeat(k) {
        val st = StringTokenizer(reader.readLine())
        val y1 = st.nextToken().toInt()
        val x1 = st.nextToken().toInt()
        val y2 = st.nextToken().toInt()
        val x2 = st.nextToken().toInt()

        val result = pref[y2][x2] - pref[y1 - 1][x2] - pref[y2][x1 - 1] + pref[y1 - 1][x1 - 1]
        output.append(result).append('\n')
    }

    print(output)
}
