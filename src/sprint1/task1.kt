package sprint1

fun main() {
    val list: List<Int> = listOf(52, 25, 48, 74, 878, 4)
    println(findEl(74, list))
}

fun findEl(x: Int, arr: List<Int>): Int {
    for (i in arr.indices) {
        if (arr[i] == x) {
            return i
        }
    }
    return -1
}