package t_algo.sprint4

import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader

fun canSplit(limit: Long, k: Int, a: LongArray): Boolean {
    var count = 1
    var currentSum = 0L

    for (x in a) {
        if (x > limit) return false
        if (currentSum + x > limit) {
            count++
            currentSum = x
        } else {
            currentSum += x
        }
    }
    return count <= k
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val line1 = reader.readLine() ?: return
    val st0 = StringTokenizer(line1)
    val n = st0.nextToken().toInt()
    val k = st0.nextToken().toInt()

    val a = LongArray(n)
    var maxVal = 0L
    var totalSum = 0L

    val st1 = StringTokenizer(reader.readLine())
    for (i in 0 until n) {
        a[i] = st1.nextToken().toLong()
        if (a[i] > maxVal) maxVal = a[i]
        totalSum += a[i]
    }

    var low = maxVal
    var high = totalSum
    var result = totalSum

    while (low <= high) {
        val mid = low + (high - low) / 2
        if (canSplit(mid, k, a)) {
            result = mid
            high = mid - 1
        } else {
            low = mid + 1
        }
    }

    println(result)
}
