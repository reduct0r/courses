package t_algo.sprint2

import java.util.ArrayDeque
import java.util.StringTokenizer

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val arr = mutableListOf<Int>()
    val st = StringTokenizer(reader.readLine())

    repeat(n) {
        arr.add(st.nextToken().toInt())
    }

    print(findSeqMaxSum(arr))
}

fun findSeqMaxSum(arr: MutableList<Int>): Long {
    var res = -1L
    val (left, right) = findLessBounds(arr)

    val prefix = LongArray(arr.size + 1)
    prefix[0] = 0L

    for (i in 0 until arr.size) {
        prefix[i + 1] = prefix[i] + arr[i]
    }


    for (i in arr.indices) {
        val sum = prefix[right[i]] - prefix[left[i] + 1]
        res = maxOf(sum * arr[i], res)
    }

    return res
}

fun findLessBounds(arr: MutableList<Int>): Pair<IntArray, IntArray> {
    val n = arr.size
    val stack = ArrayDeque<Int>()
    val leftBounds = IntArray(n)
    val rightBounds = IntArray(n)

    for (i in arr.indices) {
        while (stack.isNotEmpty() && arr[stack.last] >= arr[i]) {
            stack.removeLast()
        }
        leftBounds[i] = if (stack.isEmpty()) -1 else stack.last
        stack.addLast(i)
    }

    stack.clear()

    for (i in n - 1 downTo 0) {
        while (stack.isNotEmpty() && arr[stack.last] >= arr[i]) {
            stack.removeLast()
        }
        rightBounds[i] = if (stack.isEmpty()) n else stack.last
        stack.addLast(i)
    }

    return leftBounds to rightBounds
}
