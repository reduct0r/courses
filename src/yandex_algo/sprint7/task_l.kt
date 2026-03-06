package sprint7_l

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val cereals = reader.readLine().split(" ").map { it.toShort() }.toShortArray()


    print(findMaxEnergy(m, cereals))
}

fun findMaxEnergy(m: Int, cereals: ShortArray): Short {
    val dp = MutableList(cereals.size + 1) { ShortArray(m + 1) { 0 } }

    for (i in 1..cereals.size) {
        val weight = cereals[i - 1]
        for (j in 0..m){
            dp[i][j] = dp[i - 1][j] // не берем текущий предмет
            if (weight <= j) {
                dp[i][j] = maxOf((weight + dp[i - 1][j - weight]).toShort(), dp[i - 1][j])
            }
        }
    }

    return dp[cereals.size][m]
}