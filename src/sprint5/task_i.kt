package sprint5

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    print(numberOfBST(n))
}

fun numberOfBST(n: Int): Long {
    val memo = LongArray(n + 1) { -1L }
    memo[0] = 1L  // Базовый случай: C_0 = 1

    fun catalanRecursive(k: Int): Long {
        if (memo[k] != -1L) return memo[k]

        var result = 0L
        for (i in 0 until k) {
            result += catalanRecursive(i) * catalanRecursive(k - i - 1)
        }
        memo[k] = result
        return result
    }

    return catalanRecursive(n)
}


