package sprint7

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val prices = reader.readLine().split(" ").map { it.toInt() }.toMutableList()

    print(calculateMaxProfit(n, prices))
}

fun calculateMaxProfit(n: Int, prices: MutableList<Int>): Int {
    var profit = 0
    for (i in 1 until n) {
        if (prices[i] > prices[i - 1]) {
            profit += prices[i] - prices[i - 1]
        }
    }
    return profit
}