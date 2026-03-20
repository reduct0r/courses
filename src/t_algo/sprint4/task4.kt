package t_algo.sprint4

import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader

fun countLessEqual(x: Long, n: Long): Long {
    var count = 0L
    for (i in 1..n) {
        count += n.coerceAtMost(x / i)
    }
    return count
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val line = reader.readLine() ?: return
    val st = StringTokenizer(line)

    val n = st.nextToken().toLong()
    val k = st.nextToken().toLong()

    var low = 1L
    var high = n * n
    var result = high

    while (low <= high) {
        val mid = low + (high - low) / 2
        if (countLessEqual(mid, n) >= k) {
            result = mid
            high = mid - 1
        } else {
            low = mid + 1
        }
    }

    println(result)
}
