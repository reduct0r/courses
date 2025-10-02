package sprint3

fun merge(arr: IntArray, left: Int, mid: Int, right: Int): IntArray {
    var leftPtr = left
    var rightPtr = mid
    var ptr = 0
    val resArr = IntArray(right - left)

    while (leftPtr < mid && rightPtr < right) {
        if (arr[leftPtr] <= arr[rightPtr]) {
            resArr[ptr++] = arr[leftPtr++]
        } else {
            resArr[ptr++] = arr[rightPtr++]
        }
    }

    while (leftPtr < mid) {
        resArr[ptr++] = arr[leftPtr++]
    }

    while (rightPtr < right) {
        resArr[ptr++] = arr[rightPtr++]
    }

    return resArr
}


fun merge_sort(arr: IntArray, left: Int, right: Int) {
    if (right - left <= 1) return
    val mid = (left + right) / 2
    merge_sort(arr, left, mid)
    merge_sort(arr, mid, right)

    val merged = merge(arr, left, mid, right)

    for (i in 0 until merged.size) {
        arr[left + i] = merged[i]
    }
}

fun test() {
    val a = intArrayOf(1, 4, 9, 2, 10, 11)
    val b: IntArray = merge(a, 0, 3, 6)
    val expected = intArrayOf(1, 2, 4, 9, 10, 11)
    assert(b.contentEquals(expected))
    val c = intArrayOf(1, 4, 2, 10, 1, 2)
    merge_sort(c, 0, 6)
    val expected2 = intArrayOf(1, 1, 2, 2, 4, 10)
    assert(c.contentEquals(expected2))
}

fun main() {
    test()
}