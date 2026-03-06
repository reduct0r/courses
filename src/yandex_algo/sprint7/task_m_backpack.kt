package sprint7_m

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val cereals = mutableListOf<Pair<Int, Int>>()

    repeat(n){
        val (weight, cost) = reader.readLine().split(" ").map { it.toInt() }
        cereals.add(Pair(cost, weight))
    }

    val res = findMaxEnergy(m, cereals)
    val ans = buildString {
        append(res.second.size)
        appendLine()
        append(res.second.joinToString(" "))
    }

    print(ans)
}

fun findMaxEnergy(m: Int, cereals: MutableList<Pair<Int, Int>>): Pair<Int, List<Int>> {
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

    return Pair(dp[cereals.size][m], backtrackEnergy(dp, cereals))
}

fun backtrackEnergy(dp: MutableList<MutableList<Int>>, cereals: MutableList<Pair<Int, Int>>): List<Int> {
    val packIndices = mutableListOf<Int>()

    var i = dp.size - 1
    var j = dp[0].size - 1

    while (i > 0 && j > 0) {
        if (dp[i][j] == dp[i - 1][j]) {
            i--
        } else {
            packIndices.add(i)
            j -= cereals[i - 1].second
            i--
        }
    }

    return packIndices
}
