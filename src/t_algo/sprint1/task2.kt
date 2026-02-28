package t_algo.sprint1

import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val line1 = br.readLine() ?: return
    val st1 = StringTokenizer(line1)
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
        result.append(findClosest(arr, target)).append("\n")
    }

    print(result)
}

fun findClosest(arr: LongArray, x: Long): Long {
    var low = 0
    var high = arr.size - 1
    var idx = arr.size

    while (low <= high) {
        val mid = low + (high - low) / 2
        if (arr[mid] >= x) {
            idx = mid
            high = mid - 1
        } else {
            low = mid + 1
        }
    }

    if (idx == arr.size) return arr[arr.size - 1]
    if (idx == 0) return arr[0]

    val rightVal = arr[idx]
    val leftVal = arr[idx - 1]

    return if (abs(rightVal - x) < abs(leftVal - x)) {
        rightVal
    } else {
        leftVal
    }
}
