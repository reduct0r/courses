package t_algo.sprint4

import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader

fun canPlace(dist: Int, k: Int, coords: IntArray): Boolean {
    var count = 1
    var lastPos = coords[0]

    for (i in 1 until coords.size) {
        if (coords[i] - lastPos >= dist) {
            count++
            lastPos = coords[i]
            if (count >= k) return true
        }
    }
    return false
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val firstLine = reader.readLine() ?: return
    val st0 = StringTokenizer(firstLine)
    val n = st0.nextToken().toInt()
    val k = st0.nextToken().toInt()

    val coords = IntArray(n)
    val st1 = StringTokenizer(reader.readLine())
    for (i in 0 until n) {
        coords[i] = st1.nextToken().toInt()
    }

    var low = 0
    var high = coords[n - 1] - coords[0]
    var result = 0

    while (low <= high) {
        val mid = low + (high - low) / 2
        if (canPlace(mid, k, coords)) {
            result = mid
            low = mid + 1
        } else {
            high = mid - 1
        }
    }

    println(result)
}
