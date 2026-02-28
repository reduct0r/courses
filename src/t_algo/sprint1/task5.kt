package t_algo.sprint1

import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val line = br.readLine() ?: return
    val st = StringTokenizer(line)

    val a = st.nextToken().toDouble()
    val b = st.nextToken().toDouble()
    val c = st.nextToken().toDouble()
    val d = st.nextToken().toDouble()

    fun f(x: Double): Double = a * x * x * x + b * x * x + c * x + d

    var low = -2000.0
    var high = 2000.0

    val isIncreasing = a > 0

    repeat(100) {
        val mid = (low + high) / 2.0
        val value = f(mid)

        if (isIncreasing) {
            if (value < 0) low = mid else high = mid
        } else {
            if (value > 0) low = mid else high = mid
        }
    }

    println("%.10f".format(low))
}

