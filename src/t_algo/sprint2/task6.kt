package t_algo.sprint2

import java.io.BufferedReader

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()

    solveAnswers(reader, n)
}

fun solveAnswers(reader: BufferedReader, n: Int) {
    val sb = StringBuilder()
    val deque = java.util.ArrayDeque<Int>()
    val idToEntryTime = IntArray(100001)

    var entryPos = 0
    var passedQue = 0

    repeat(n) {
        val line = reader.readLine() ?: ""
        val st = java.util.StringTokenizer(line)
        val type = st.nextToken()[0]

        when (type) {
            '1' -> {
                val id = st.nextToken().toInt()
                entryPos++
                idToEntryTime[id] = entryPos
                deque.add(id)
            }
            '2' -> {
                deque.removeFirst()
                passedQue++
            }
            '3' -> {
                deque.removeLast()
                entryPos--
            }
            '4' -> {
                val q = st.nextToken().toInt()
                sb.appendLine(idToEntryTime[q] - passedQue - 1)
            }
            '5' -> {
                sb.appendLine(deque.first())
            }
        }
    }

    println(sb.toString())
}
