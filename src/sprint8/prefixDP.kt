package sprint8

fun main() {
    val reader = System.`in`.bufferedReader()
    val str = reader.readLine()
    print(prefixFunction(str).joinToString(" "))
}

fun prefixFunction(str: String): IntArray {
    val n = str.length
    val pi = IntArray(n)

    for (i in 1 until n) {
        var k = pi[i - 1]

        while (k > 0 && str[i] != str[k]) {
            k = pi[k - 1]
        }

        if (str[i] == str[k]) {
            k++
        }

        pi[i] = k
    }

    return pi
}
