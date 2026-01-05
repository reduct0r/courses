package sprint7

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val a = reader.readLine().split(" ").map { it.toInt() }
    val m = reader.readLine().toInt()
    val b = reader.readLine().split(" ").map { it.toInt() }

    val res = findMaxLCS(a, b)

    val ans = buildString {
        append(res.first.size)
        appendLine()
        append(res.second.reversed().joinToString(" "))
        appendLine()
        append(res.third.reversed().joinToString(" "))
    }

    print(ans)
}

fun findMaxLCS(a: List<Int>, b: List<Int>
): Triple<MutableList<Int>, MutableList<Int>, MutableList<Int>> {
    val dp = MutableList(a.size + 1){ MutableList(b.size + 1) { 0 } }

    for (i in 1..a.size) {
        for (j in 1..b.size) {
            if (a[i - 1] == b[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1
            } else {
                dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    return backtrackLCS(dp, a, b)
}

fun backtrackLCS(dp: MutableList<MutableList<Int>>, a: List<Int>, b: List<Int>
): Triple<MutableList<Int>, MutableList<Int>, MutableList<Int>> {
    val lcsPath = mutableListOf<Int>()
    val indicesA = mutableListOf<Int>()
    val indicesB = mutableListOf<Int>()


    var i = a.size
    var j = b.size

    while (i > 0 && j > 0) {
        if (a[i - 1] == b[j - 1]) {
            lcsPath.add(a[i - 1])
            indicesA.add(i)
            indicesB.add(j)
            i--
            j--
        } else {
            if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--
            } else {
                j--
            }
        }
    }
    return Triple(lcsPath, indicesA, indicesB)
}
