package sprint1

fun main() {
    val reader = System.`in`.bufferedReader()
    val num = reader.readLine().trim().toInt()

    print(toBinary(num))

}

fun toBinary(n: Int): String {
    var temp = n
    val builder = StringBuilder()

    if (n == 0) return "0"

    while (temp != 0) {
        builder.append(temp % 2)
        temp /= 2
    }
    return builder.reverse().toString()
}