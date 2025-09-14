package sprint1

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val m = reader.readLine().toInt()
    val arr = IntArray(n * m)

    var ptr = 0
    for (i in 0 until n) {
        val line = reader.readLine().split(" ").map { it.toInt() }
        for (j in 0 until m) {
            arr[ptr++] = line[j]
        }
    }

    val row = reader.readLine().toInt()
    val column = reader.readLine().toInt()
    val ans = mutableListOf<Int>()

    if (column - 1 >= 0) {ans.add(arr[row * m + column - 1])} else Unit
    if (column + 1 < m) {ans.add(arr[row * m + column + 1])} else Unit
    if (row - 1 >= 0) {ans.add(arr[(row - 1) * m + column])} else Unit
    if (row + 1 < n) {ans.add(arr[(row + 1) * m + column])} else Unit

    ans.sort()
    ans.forEach { print("$it ") }
}