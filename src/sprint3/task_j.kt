package sprint3

import kotlin.collections.joinToString

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().trim().toInt()
    val arr: MutableList<Int> = reader.readLine().trim().split(" ").map { it.toInt() } as MutableList<Int>

    sortBubble(arr)
}

fun sortBubble(arr: MutableList<Int>) {
    var swapFlag = true
    var iter = 0
    while (swapFlag) {
        swapFlag = false
        for (i in 0 until arr.size - 1) {
            if (arr[i + 1] < arr[i]) {
                arr[i] = arr[i + 1].also { arr[i + 1] = arr[i] }
                swapFlag = true
                iter++
            }
        }
        if (iter == 0) {
            println(arr.joinToString(" "))
            return
        }
        if (!swapFlag) return
        println(arr.joinToString(" "))

    }
}