package sprint7

fun main() {
    val reader = System.`in`.bufferedReader()
    val m = reader.readLine().toInt()
    val n = reader.readLine().toInt()
    val cereals = mutableListOf<Pair<Int, Int>>()

    repeat(n){
        val (cost, weight) = reader.readLine().split(" ").map { it.toInt() }
        cereals.add(Pair(cost, weight))
    }

    print(findMaxEnergy(m, cereals))
}

fun findMaxEnergy(m: Int, cereals: MutableList<Pair<Int, Int>>): Int {
    val dp = MutableList(cereals.size + 1) { MutableList(m + 1) { 0 } }

    for (i in 1..cereals.size) {
        val weight = cereals[i - 1].second
        val cost = cereals[i - 1].first
        for (j in 0..m){
            dp[i][j] = dp[i - 1][j] // не берем текущий предмет
            if (weight <= j) {
                dp[i][j] = maxOf(cost + dp[i - 1][j - weight], dp[i - 1][j])
            }
        }
    }

    return dp[cereals.size][m]
}
