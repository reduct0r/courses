package sprint3

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, k) = reader.readLine().trim().split(" ").map { it.toInt() }
    val prices = reader.readLine().trim().split(" ").map { it.toInt() }
    val sortedPrices = qsort(prices)

    print(countMaxHouses(k, sortedPrices))
}

fun countMaxHouses(k: Int, prices: List<Int>): Int {
    var counter = 0
    var budget = k

    for (el in prices) {
        if (budget - el >= 0) {
            counter++
            budget -= el
        } else {
            break
        }
    }

    return counter
}

fun partition(arr: List<Int>, pivotValue: Int): Triple<List<Int>, List<Int>, List<Int>> {
    val left = mutableListOf<Int>()
    val right = mutableListOf<Int>()
    val center = mutableListOf<Int>()

    for (i in arr.indices) {
        if (arr[i] < pivotValue) {
            left.add(arr[i])
        } else if (arr[i] == pivotValue) {
            center.add(arr[i])
        } else {
            right.add(arr[i])
        }
    }

    return Triple(left, center, right)
}

fun qsort(arr: List<Int>): List<Int>{
    if (arr.size <= 1){
        return arr
    } else {
        val pivotValue = arr.random()
        val (left, center, right) = partition(arr, pivotValue)
        return qsort(left) + center + qsort(right)
    }
}
