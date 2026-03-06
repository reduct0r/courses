package t_algo.sprint2

import java.io.BufferedReader
import java.util.StringTokenizer

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()

    print(checkGoblinNum(reader, n))
}

fun checkGoblinNum(reader: BufferedReader, n: Int): String {
    val startDeque = ArrayDeque<Int>()
    val endDeque = ArrayDeque<Int>()
    val sb = StringBuilder()

    repeat(n) {
        val str = reader.readLine()
        val st = StringTokenizer(str)
        val type = st.nextToken()
        when (type) {
            "*" -> {
                endDeque.addFirst(st.nextToken().toInt())
            }

            "+" -> {
                endDeque.add(st.nextToken().toInt())
            }

            "-" -> {
                sb.appendLine(startDeque.removeFirst())
            }
        }
        if (endDeque.size > startDeque.size) {
            startDeque.add(endDeque.removeFirst())
        }
    }

    return sb.toString()
}
