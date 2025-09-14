package sprint1

fun main() {
    val reader = System.`in`.bufferedReader()
    val l = reader.readLine().toInt()
    val str = reader.readLine().trim()

    print(task(str))
}

fun task(str: String): String {
    var max = Int.MIN_VALUE
    var start = 0
    var end = 0
    var current = 0

    for (i in str.indices) {
        if (str[i] != ' ') {
            current++
            if (current > max) {
                max = current
                end = i
                start = i - current + 1
            }
        } else {
            current = 0
        }
    }
    return "${str.substring(start, end + 1)}\n${max}"
}