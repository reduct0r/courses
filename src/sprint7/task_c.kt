package sprint7

class MutablePair<T, R>(var first: T, var second: R)

fun main() {
    val reader = System.`in`.bufferedReader()
    val m = reader.readLine().toInt()
    val n = reader.readLine().toInt()
    val goldHeaps = mutableListOf<MutablePair<Long, Long>>()

    repeat(n) {
        val (price, weight) = reader.readLine().split(" ").map { it.toLong() }
        goldHeaps.add(MutablePair(price, weight))
    }

    print(maxProfit(m, goldHeaps))
}

fun maxProfit(m: Int, goldHeaps: MutableList<MutablePair<Long, Long>>): Long {
    val sortedHeaps = goldHeaps.sortedWith(compareByDescending { it.first })
    var currentWeight = 0L
    var profit = 0L
    var heapNum = 0

    while (heapNum < sortedHeaps.size && currentWeight < m) {

        val probe = if (sortedHeaps[heapNum].second <  m - currentWeight) { sortedHeaps[heapNum].second } else { m - currentWeight }

        if (sortedHeaps[heapNum].second > 0) {
            currentWeight += sortedHeaps[heapNum].second
            profit += sortedHeaps[heapNum].first * probe
        }
        heapNum++
    }

    return profit
}
