package sprint1

fun main() {
    val reader = System.`in`.bufferedReader()
    val a = reader.readLine().toString()
    val b = reader.readLine().toString()

    print(sumBin(a, b))
}

fun sumBin(a: String, b: String): String {
    var ptrA = a.length - 1
    var ptrB = b.length - 1
    val builder = StringBuilder()
    var buf = 0

    while (ptrA >= 0 || ptrB >= 0 || buf > 0) {
        val _a = if (ptrA >= 0) a[ptrA].digitToIntOrNull()!! else 0
        val _b = if (ptrB >= 0) b[ptrB].digitToIntOrNull()!! else 0

        val sum = _a + _b + buf
        builder.append((sum % 2).toString())
        buf = sum / 2

        ptrA--
        ptrB--
    }
    return builder.reverse().toString()
}