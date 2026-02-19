package sprint8

fun main() {
    val reader = System.`in`.bufferedReader()
    val str = reader.readLine()
    val ptn = reader.readLine()
    print(search(ptn, str).joinToString(" "))
}

fun search(ptn: String, text: String): MutableList<Int> {
    val res = mutableListOf<Int>()
    val str = "$ptn#$text"
    val pi = IntArray(ptn.length)
    var k = 0

    for (i in 1 until str.length) {

        while (k > 0 && str[i] != str[k]) {
            k = pi[k - 1]
        }

        if (str[i] == str[k]) {
            k++
        }

        if (i < ptn.length) {
            pi[i] = k
        }

        if (k == ptn.length) {
            res.add(i - 2 * ptn.length)
        }
    }

    return res
}
