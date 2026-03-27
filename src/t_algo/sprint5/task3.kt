package t_algo.sprint5

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val a = reader.readLine() ?: ""
    val b = reader.readLine() ?: ""

    val n = a.length
    val m = b.length

    val p = 67L
    val mod = 1_000_000_000_000_037L

    val pows = LongArray(n + 1)
    pows[0] = 1
    for (i in 1..n) pows[i] = multiply(pows[i - 1], p, mod)

    val hA = LongArray(n + 1)
    for (i in 0 until n) {
        hA[i + 1] = (multiply(hA[i], p, mod) + charToLong(a[i])) % mod
    }

    val bb = b + b
    val hBB = LongArray(bb.length + 1)
    for (i in 0 until bb.length) {
        hBB[i + 1] = (multiply(hBB[i], p, mod) + charToLong(bb[i])) % mod
    }

    val shiftHashes = mutableSetOf<Long>()
    for (i in 0 until m) {
        val currentHash = (hBB[i + m] - multiply(hBB[i], pows[m], mod) + mod) % mod
        shiftHashes.add(currentHash)
    }

    var count = 0
    for (i in 0..n - m) {
        val subHash = (hA[i + m] - multiply(hA[i], pows[m], mod) + mod) % mod
        if (shiftHashes.contains(subHash)) {
            count++
        }
    }

    println(count)
}

fun charToLong(c: Char): Long {
    return when (c) {
        in 'a'..'z' -> (c - 'a' + 1).toLong()
        in 'A'..'Z' -> (c - 'A' + 27).toLong()
        else -> (c - '0' + 53).toLong()
    }
}

fun multiply(a: Long, b: Long, m: Long): Long {
    var res: Long = 0
    var x = a % m
    var y = b % m
    while (y > 0) {
        if (y % 2 == 1L) res = (res + x) % m
        x = (x * 2) % m
        y /= 2
    }
    return res
}
