package t_algo.sprint1

import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine()?.trim()?.toInt() ?: return
    val st = StringTokenizer(br.readLine())
    val isOne = BooleanArray(n + 1)
    var rightPtr = n
    val result = StringBuilder()

    result.append("1")

    for (step in 1..n) {
        val pos = st.nextToken().toInt()
        isOne[pos] = true

        while (rightPtr > 0 && isOne[rightPtr]) {
            rightPtr--
        }
        val ans = step - (n - rightPtr) + 1

        result.append(" ").append(ans)
    }
    println(result.toString())
}

