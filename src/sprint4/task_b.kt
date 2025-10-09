package sprint4

const val MIN_LEN = 0

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val resStr = reader.readLine()

    if (resStr.isEmpty()) {
        print(0)
        return
    }

    val results = resStr.split(" ")
        .map { str ->
            val num = str.toInt()
            if (num == 0) -1 else 1
        }

    print(findMaxParity(results))
}

fun findMaxParity(results: List<Int>): Int {
    val map = mutableMapOf<Int, Int>()
    map[0] = -1
    var max = MIN_LEN
    var currentSum = 0

    for (i in results.indices) {
        currentSum += results[i]

        if (map.containsKey(currentSum)) {
            val len = i - map[currentSum]!!
            max = if (max > len) max else len
        } else {
           map[currentSum] = i
        }
    }

    return max
}
