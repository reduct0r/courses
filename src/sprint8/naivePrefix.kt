package sprint8

fun main() {
    val reader = System.`in`.bufferedReader()
    val str = reader.readLine()
    print(findNaivePrefix(str).joinToString(" "))
}

fun findNaivePrefix(str: String): IntArray {
    val res = IntArray(str.length) { 0 }
    for (i in str.indices) {
        val sub = str.take(i)
        for (j in i - 1 downTo 1) {
            val pref = sub.take(j)
            val suf = sub.substring(i - j, i)
            if (pref == suf) {
                res[i] = j
                break
            }
        }
    }
    return res
}
