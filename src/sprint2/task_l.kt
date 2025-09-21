package sprint2

import kotlin.math.pow

fun findLastFibNumbers(n: Int, k: Int): Int {
    var first = 1
    var second = 1
    var res = 0

    if (n == 0 || n == 1) {
        return 1
    }

    for (i in 2..n) {
        res = (first + second) % (10.toDouble().pow(k.toDouble()).toInt())
        second = first
        first = res
    }

    return res
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, k) = reader.readLine().trim().split(" ").map { it.toInt() }
    print(findLastFibNumbers(n, k))
}