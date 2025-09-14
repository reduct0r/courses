package sprint1

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().trim().toInt()

    print(isPowFour(n))
}

fun isPowFour(n: Int): String {
    var x = 1
    while (x < n) {
        x *= 4
    }

    return if (x == n) {
        "True"
    } else {
        "False"
    }
}