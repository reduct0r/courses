package sprint3

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val arr = reader.readLine().trim().split(" ").map { it.toInt() }
    val price = reader.readLine().toInt()
    print((find(price, 0, n - 1, arr) + 1).toString() + " " + (find(price * 2, 0, n - 1, arr) + 1).toString())
}

fun find(price: Int, left: Int, right: Int, arr: List<Int>): Int {
    if (left > right) {
        return if (left < arr.size && arr[left] >= price) left else -2
    }

    val mid = (left + right) / 2

    if (arr[mid] >= price) {
        return find(price, left, mid - 1, arr)
    } else {
        return find(price, mid + 1, right, arr)
    }
}