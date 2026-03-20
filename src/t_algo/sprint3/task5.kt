package t_algo.sprint3

import java.util.*
import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val nStr = br.readLine() ?: return
    val n = nStr.trim().toInt()

    val a = LongArray(n)
    val st = StringTokenizer(br.readLine())
    for (i in 0 until n) {
        a[i] = st.nextToken().toLong()
    }

    for (i in (n / 2 - 1) downTo 0) {
        siftDown(a, i, n)
    }

    for (size in n - 1 downTo 1) {
        swap(a, 0, size)
        siftDown(a, 0, size)
    }

    val sb = StringBuilder()
    for (i in 0 until n) {
        sb.append(a[i]).append(if (i == n - 1) "" else " ")
    }
    println(sb)
}

fun siftDown(a: LongArray, index: Int, size: Int) {
    var i = index
    while (true) {
        val left = 2 * i + 1
        val right = 2 * i + 2
        var largest = i

        if (left < size && a[left] > a[largest]) {
            largest = left
        }
        if (right < size && a[right] > a[largest]) {
            largest = right
        }

        if (largest == i) break
        swap(a, i, largest)
        i = largest
    }
}

fun swap(a: LongArray, i: Int, j: Int) {
    val temp = a[i]
    a[i] = a[j]
    a[j] = temp
}
