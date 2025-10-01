package sprint3

const val MAX_VALUE = 1001

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val greed = reader.readLine().trim().split(" ").map { it.toInt() }
    val m = reader.readLine().toInt()
    val cookies = reader.readLine().trim().split(" ").map { it.toInt() }

    print(countKids(n, m, greed, cookies))

}

fun countKids(n: Int, m: Int, greed: List<Int>, cookies: List<Int>): Int {
    val countGreed = MutableList(MAX_VALUE) { 0 }
    val countCookies = MutableList(MAX_VALUE) { 0 }

    for (el in greed) {
        countGreed[el]++
    }

    for (el in cookies) {
        countCookies[el]++
    }

    var pointer = 1
    var cookiePtr = 1
    var counter = 0

    while (pointer < MAX_VALUE) {
        if (countGreed[pointer] != 0) {
            if (countCookies[cookiePtr] > 0 && cookiePtr >= pointer) {
                countCookies[cookiePtr]--
                countGreed[pointer]--
                counter++
            } else {
                cookiePtr++
                if (cookiePtr >= MAX_VALUE) {
                    break
                }
            }
        } else {
            pointer++
        }
    }

    return counter
}