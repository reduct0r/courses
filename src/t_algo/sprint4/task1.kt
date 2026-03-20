package t_algo.sprint4

import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val n = reader.readLine()?.trim()?.toInt() ?: return

    val sumPrefix = LongArray(n + 1)
    val xorPrefix = IntArray(n + 1)

    val st = StringTokenizer(reader.readLine())
    for (i in 1..n) {
        val value = st.nextToken().toInt()
        sumPrefix[i] = sumPrefix[i - 1] + value
        xorPrefix[i] = xorPrefix[i - 1] xor value
    }

    val m = reader.readLine()?.trim()?.toInt() ?: return
    val output = StringBuilder()

    repeat(m) {
        val query = StringTokenizer(reader.readLine())
        val type = query.nextToken().toInt()
        val l = query.nextToken().toInt()
        val r = query.nextToken().toInt()

        if (type == 1) {
            output.append(sumPrefix[r] - sumPrefix[l - 1]).append('\n')
        } else {
            output.append(xorPrefix[r] xor xorPrefix[l - 1]).append('\n')
        }
    }

    print(output)
}
