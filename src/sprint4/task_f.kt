package sprint4

fun modPow(base: Long, exp: Long, mod: Long): Long {
    var result = 1L
    var b = base % mod
    var e = exp
    while (e > 0) {
        if (e % 2 == 1L) {
            result = (result * b) % mod
        }
        b = (b * b) % mod
        e /= 2
    }
    return result
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val a = reader.readLine().toInt()
    val m = reader.readLine().toLong()
    val s = reader.readLine().trim()
    val t = reader.readLine().toInt()
    val prefixHashList = mutableListOf<Long>()
    prefixHashList.add(0)

    for (i in s.indices) {
        prefixHashList.add(polynomialHash(a, m, s.substring(0..i)))
    }

    repeat(t) {
        val (first, second) = reader.readLine().split(" ")
        val l = first.toInt()
        val r = second.toInt()
        if (l == 1) {
            println(prefixHashList[r])
        } else if (first != second) {
            val len = (r - l + 1).toLong()
            val pow = modPow(a.toLong(), len, m)
            println((prefixHashList[r] - (prefixHashList[l - 1] * pow % m) + m) % m)
        } else {
            println(s[l - 1].code.toLong() % m)
        }
    }

}