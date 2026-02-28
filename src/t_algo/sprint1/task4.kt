package t_algo.sprint1

import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.sqrt

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val line = br.readLine() ?: return
    val st = StringTokenizer(line)

    if (!st.hasMoreTokens()) return
    val c = st.nextToken().toDouble()

    fun f(x: Double): Double = x * x + sqrt(x + 1.0)

    var low = 0.0
    var high = 100000.0

    repeat(100) {
        val mid = (low + high) / 2.0
        if (f(mid) < c) {
            low = mid
        } else {
            high = mid
        }
    }

    println("%.10f".format(low))
}
