package sprint8

fun main() {
    val reader = System.`in`.bufferedReader()
    val text = reader.readLine()
    val pattern = reader.readLine()
    val replace = reader.readLine()

    print(replacePattern(text, pattern, replace))
}

fun replacePattern(text: String, ptn: String, t: String): String {
    val str = "$ptn#$text"
    val pi = IntArray(ptn.length)
    var k = 0
    val find = mutableListOf<Int>()
    val sb = StringBuilder()

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
            find.add(i - 2 * ptn.length)
        }
    }

    var lastIndex = 0
    for (f in find) {
        sb.append(text.substring(lastIndex, f))
        sb.append(t)
        lastIndex = f + ptn.length
    }
    if (lastIndex < text.length) {
        sb.append(text.substring(lastIndex))
    }

    return sb.toString()
}
