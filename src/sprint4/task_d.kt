package sprint4

fun main(){
    val reader = System.`in`.bufferedReader()
    val a = reader.readLine().toInt()
    val m = reader.readLine().toLong()
    val s = reader.readLine().trim()

    print(polynomialHash(a, m, s))
}

fun polynomialHash(a: Int, m: Long, s: String): Long {
    var hashSum = 0L

    for (c in s) {
        hashSum = (hashSum * a + c.code.toLong()) % m
    }

    return hashSum
}
