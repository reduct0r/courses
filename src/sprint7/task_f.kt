package sprint7

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, k) = reader.readLine().split(" ").map { it.toInt() }
    print(findCombinations(n - 1, k))
}

fun findCombinations(n: Int, k: Int): Long {
    val dp = MutableList<Long>(n + 1) { 0 }
    dp[0] = 1
    val mod = 1000000007
    var currentSum = 0L

    for (i in 1..n) {
        currentSum = (currentSum + dp[i - 1]) % mod
        if (i > k) {
            currentSum = (currentSum - dp[i - k - 1] + mod) % mod
        }

        dp[i] = currentSum
    }
    return dp[n]
}
