package sprint1

// https://contest.yandex.ru/contest/22450/run-report/141856429/
import java.util.StringTokenizer
import kotlin.math.min

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val tokenizer = StringTokenizer(reader.readLine())
    val maxHouseNum = 1_000_000_001
    val arr = IntArray(n) {
        val value = tokenizer.nextToken().toInt()
        if (value == 0) 0 else maxHouseNum
    }

    print(distanceToEmpty(arr).joinToString(" "))
}

fun distanceToEmpty(arr: IntArray): IntArray {
    val answer = arr.copyOf()

    for (i in 1 until arr.size) {
        answer[i] = min(answer[i], answer[i - 1] + 1)
    }

    for (i in arr.size - 2 downTo 0) {
        answer[i] = min(answer[i], answer[i + 1] + 1)
    }

    return answer
}
