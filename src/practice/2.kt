package practice


fun main() {
    val reader = System.`in`.bufferedReader()
    val s = reader.readLine() ?: return
    val n = s.length

    val t = "tbank"
    val st = "study"
    val lenT = 5
    val lenS = 5

    if (n < 10) {
        return
    }

    val costT = IntArray(n - lenT + 1)
    val costS = IntArray(n - lenS + 1)

    for (i in 0..n - 5) {
        var diffT = 0
        var diffS = 0
        for (j in 0 until 5) {
            if (s[i + j] != t[j]) diffT++
            if (s[i + j] != st[j]) diffS++
        }
        costT[i] = diffT
        costS[i] = diffS
    }

    fun getMinCost(c1: IntArray, c2: IntArray): Int {
        val size1 = c1.size
        val size2 = c2.size
        val sufMin2 = IntArray(size2)
        sufMin2[size2 - 1] = c2[size2 - 1]
        for (i in size2 - 2 downTo 0) {
            sufMin2[i] = minOf(c2[i], sufMin2[i + 1])
        }

        var minTotal = 10
        for (i in 0 until size1) {
            val nextStart = i + 5
            if (nextStart < size2) {
                minTotal = minOf(minTotal, c1[i] + sufMin2[nextStart])
            }
        }
        return minTotal
    }

    val res1 = getMinCost(costT, costS)
    val res2 = getMinCost(costS, costT)

    println(minOf(res1, res2))
}
