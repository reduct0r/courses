package sprint1

fun main() {
    val reader = System.`in`.bufferedReader()
    val s1 = reader.readLine().trim()
    val s2 = reader.readLine().trim()

    print(findLetter(s1, s2))
}

fun findLetter(s: String, t: String): String {
    val arrCounter = Array(26) {0}
    val arrCounterT = Array(26) {0}

    for (i in s.indices) {
        arrCounter[s[i].code - 97]++
    }

    for (i in t.indices) {
        arrCounterT[t[i].code - 97]++
    }

    for (i in 0..25) {
        if (arrCounterT[i] - arrCounter[i] == 1) {
            return (i + 97).toChar().toString()
        }
    }

    return ""
}
