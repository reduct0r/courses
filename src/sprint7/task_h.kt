package sprint7

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val plain = Array(n) { IntArray(m) }

    for (i in 0 until n) {
        val line = reader.readLine()
        for (j in 0 until m) {
            plain[i][j] = line[j].digitToInt()
        }
    }

    val (maxFlowers, path) = countMaxFlowers(plain, n, m)
    println(maxFlowers)
    print(path)
}

fun countMaxFlowers(originalGrid: Array<IntArray>, n: Int, m: Int): Pair<Int, String> {
    if (n == 0 || m == 0) return Pair(0, "")

    // Reverse the grid vertically
    val grid = Array(n) { IntArray(m) }
    for (i in 0 until n) {
        for (j in 0 until m) {
            grid[i][j] = originalGrid[n - 1 - i][j]
        }
    }

    val dp = Array(n) { IntArray(m) }
    for (i in 0 until n) {
        for (j in 0 until m) {
            dp[i][j] = grid[i][j]
        }
    }

    for (j in 1 until m) {
        dp[0][j] += dp[0][j - 1]
    }

    for (i in 1 until n) {
        dp[i][0] += dp[i - 1][0]
    }

    for (i in 1 until n) {
        for (j in 1 until m) {
            val up = dp[i - 1][j]
            val left = dp[i][j - 1]
            dp[i][j] += maxOf(up, left)
        }
    }

    val path = StringBuilder()
    var i = n - 1
    var j = m - 1
    while (i > 0 || j > 0) {
        if (j > 0 && dp[i][j] == dp[i][j - 1] + grid[i][j]) {
            path.append("R")
            j--
        } else if (i > 0) {
            path.append("U")
            i--
        } else {
            break
        }
    }

    val originalPath = path.reverse().toString()

    val formattedPath = originalPath.toCharArray().joinToString("")

    return Pair(dp[n - 1][m - 1], formattedPath)
}