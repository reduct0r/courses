package yandexStartContest

fun main() {
    val len = readln().toInt()
    val arr = readln().split(" ").map { it.toDouble() }
    val k = readln().toInt()

    var startSum = 0.0
    val res = mutableListOf<Double>()

    for (i in 0 until k) {
        startSum += arr[i]
    }

    res += startSum / k

    for (i in 0 until len - k) {
        startSum = startSum - arr[i] + arr[k + i]
        res += startSum / k
    }

    res.forEach { print("$it ") }
}