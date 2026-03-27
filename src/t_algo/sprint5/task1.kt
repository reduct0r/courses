package t_algo.sprint5

import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val s = reader.readLine() ?: ""
    val m = reader.readLine()?.toInt() ?: 0

    val n = s.length
    val p = 31L
    val m1 = 1_000_000_007L
    val m2 = 1_000_000_009L

    val h1 = LongArray(n + 1)
    val h2 = LongArray(n + 1)
    val pow1 = LongArray(n + 1)
    val pow2 = LongArray(n + 1)

    pow1[0] = 1; pow2[0] = 1
    for (i in 0 until n) {
        h1[i + 1] = (h1[i] * p + (s[i] - 'a' + 1)) % m1
        h2[i + 1] = (h2[i] * p + (s[i] - 'a' + 1)) % m2
        pow1[i + 1] = (pow1[i] * p) % m1
        pow2[i + 1] = (pow2[i] * p) % m2
    }

    val out = StringBuilder()
    repeat(m) {
        val st = StringTokenizer(reader.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        val d = st.nextToken().toInt()

        if (b - a != d - c) {
            out.append("No\n")
        } else {
            val hash1_1 = (h1[b] - h1[a - 1] * pow1[b - a + 1] % m1 + m1) % m1
            val hash1_2 = (h2[b] - h2[a - 1] * pow2[b - a + 1] % m2 + m2) % m2

            val hash2_1 = (h1[d] - h1[c - 1] * pow1[d - c + 1] % m1 + m1) % m1
            val hash2_2 = (h2[d] - h2[c - 1] * pow2[d - c + 1] % m2 + m2) % m2

            if (hash1_1 == hash2_1 && hash1_2 == hash2_2) {
                out.append("Yes\n")
            } else {
                out.append("No\n")
            }
        }
    }
    print(out)
}
