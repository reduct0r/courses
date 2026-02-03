package practice_1

import java.util.StringTokenizer

fun main() {
    val reader = System.`in`.bufferedReader()
    val st = StringTokenizer(reader.readLine())

    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val MOD = 1_000_000_007L

    if (k > 2 * n - 1) {
        println(0)
        return
    }

    val whiteCells = mutableListOf<Int>()
    val blackCells = mutableListOf<Int>()

    for (i in 1..n) {
        if (i % 2 == 1) {
            whiteCells.add(i)
            if (i < n) whiteCells.add(i)
        } else {
            blackCells.add(i)
            if (i < n) blackCells.add(i)
        }
    }

    whiteCells.sort()
    blackCells.sort()

    fun solveDP(cells: List<Int>, kCount: Int): LongArray {
        val size = cells.size
        val dp = Array(size + 1) { LongArray(kCount + 1) }

        for (i in 0..size) dp[i][0] = 1L

        for (i in 1..size) {
            val rowLen = cells[i - 1]
            for (j in 1..kCount) {
                dp[i][j] = dp[i - 1][j]
                if (rowLen > j - 1) {
                    val ways = (dp[i - 1][j - 1] * (rowLen - (j - 1))) % MOD
                    dp[i][j] = (dp[i][j] + ways) % MOD
                }
            }
        }
        return dp[size]
    }

    val dpWhite = solveDP(whiteCells, k)
    val dpBlack = solveDP(blackCells, k)

    var ans = 0L
    for (i in 0..k) {
        val ways = (dpWhite[i] * dpBlack[k - i]) % MOD
        ans = (ans + ways) % MOD
    }

    println(ans)
}