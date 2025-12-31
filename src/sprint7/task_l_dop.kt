package sprint7

fun main() {
    val reader = System.`in`.bufferedReader()
    val m = reader.readLine().toInt()
    val n = reader.readLine().toInt()
    val packages = mutableListOf<Pair<Int, Int>>()

    repeat(n) {
        val (energy, m) = reader.readLine().split(" ").map { it.toInt() }
        packages.add(Pair(energy, m))
    }

    print(maxEnergyProfit(m, packages))
}

fun maxEnergyProfit(m: Int, heaps: MutableList<Pair<Int, Int>>): Int {
    val dp = MutableList(heaps.size + 1) { MutableList(m + 1) { 0 } }

    for (i in 1 .. heaps.size) {
        for (j in 0 .. m) {
            dp[i][j] = dp[i - 1][j]
            if (heaps[i - 1].second <= j) {
                dp[i][j] = maxOf(dp[i][j], dp[i - 1][j - heaps[i - 1].second] + heaps[i - 1].second * heaps[i - 1].first)
            }
        }
    }

    return dp[heaps.size][m]
}
