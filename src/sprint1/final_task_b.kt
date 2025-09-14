package sprint1

// https://contest.yandex.ru/contest/22450/run-report/141856508/
const val fieldLen = 4
const val numOfDigits = 10
const val numOfPlayers = 2
const val emptyEl = '.'

fun main() {
    val reader = System.`in`.bufferedReader()
    val k = reader.readLine().toInt()
    val arrCounter = IntArray(numOfDigits)

    repeat(fieldLen) {
        reader.readLine().forEach {
            if (it != emptyEl) {
                it.digitToIntOrNull()?.let { digit ->
                    arrCounter[digit]++
                }
            }
        }
    }

    print(countWinPoints(k, arrCounter))
}

fun countWinPoints(k: Int, arrCounter: IntArray): Int {
    val maxButtons = numOfPlayers * k
    return arrCounter.count {
        it in 1..maxButtons
    }
}
