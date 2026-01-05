package sprint7

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val ratings = reader.readLine().split(" ").map { it.toInt() }.toIntArray()

    val res = findLIS(ratings)
    val ans = buildString {
        append(res.first)
        appendLine()
        append(res.second.joinToString(" "))
    }

    print(ans)
}

fun findLIS(ratings: IntArray): Pair<Int, MutableList<Int>> {
    when {
        ratings.isEmpty() -> return Pair(0, emptyList<Int>().toMutableList())
        ratings.size == 1 -> return Pair(1, MutableList(1) { 1 })
    }
    val dp = IntArray(ratings.size + 1) { 1 }
    var maxLen = 1

    for (i in 1 until ratings.size) {
        for (j in 0 until i) {
            if (ratings[j] < ratings[i]) {
                dp[i] = maxOf(dp[i], dp[j] + 1)
            }
        }
        maxLen = maxOf(maxLen, dp[i])
    }

    return Pair(maxLen, backtrackLIS(dp, ratings, maxLen))
}

fun backtrackLIS(dp: IntArray, ratings: IntArray, maxLen: Int): MutableList<Int> {
    if (maxLen == 1) return MutableList(1) { 1 }
    val path = mutableListOf<Int>()
    var localMax = maxLen
    for (i in ratings.size downTo 0) {
        if (dp[i] == localMax) {
            path.add(i + 1)
            localMax--
        }
        if (localMax == 0) {
            break
        }
    }

    return path.asReversed()
}
