package t_algo.sprint1

import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val firstLine = br.readLine() ?: return
    val st1 = StringTokenizer(firstLine)
    val n = st1.nextToken().toInt()
    val k = st1.nextToken().toInt()
    val arr = LongArray(n)
    val st2 = StringTokenizer(br.readLine())
    for (i in 0 until n) {
        arr[i] = st2.nextToken().toLong()
    }

    val st3 = StringTokenizer(br.readLine())
    val result = StringBuilder()

    repeat(k) {
        val target = st3.nextToken().toLong()

        var low = 0
        var high = n - 1
        var found = false

        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[mid] == target -> {
                    found = true
                    break
                }
                arr[mid] < target -> low = mid + 1
                else -> high = mid - 1
            }
        }

        if (found) result.append("YES\n") else result.append("NO\n")
    }

    print(result)
}
