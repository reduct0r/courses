package t_algo.sprint4

import java.util.*
import java.io.*

class Task(val id: Int, val s: Int, val t: Int, val end: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val st0 = StringTokenizer(reader.readLine())
    val n = st0.nextToken().toInt()
    val c = st0.nextToken().toLong()

    val tasks = Array(n) { i ->
        val st = StringTokenizer(reader.readLine())
        val s = st.nextToken().toInt()
        val t = st.nextToken().toInt()
        Task(i + 1, s, t, s + t)
    }

    val sortedByStart = tasks.indices.sortedBy { tasks[it].s }
    val sortedByEnd = tasks.indices.sortedBy { tasks[it].end }

    val dp = IntArray(n)
    val parent = IntArray(n) { -1 }
    var bestEndIdx = 0
    var currentMaxDp = 0
    var currentMaxIdx = -1

    for (i in sortedByStart) {
        val startTime = tasks[i].s

        while (bestEndIdx < n && tasks[sortedByEnd[bestEndIdx]].end <= startTime) {
            val j = sortedByEnd[bestEndIdx]
            if (dp[j] > currentMaxDp) {
                currentMaxDp = dp[j]
                currentMaxIdx = j
            }
            bestEndIdx++
        }

        dp[i] = currentMaxDp + 1
        parent[i] = currentMaxIdx
    }

    val lastTask = sortedByStart.maxByOrNull { dp[it] } ?: 0
    val count = dp[lastTask]

    val path = mutableListOf<Int>()
    var curr: Int? = lastTask
    while (curr != -1 && curr != null) {
        path.add(tasks[curr].id)
        curr = parent[curr]
    }
    path.reverse()

    println(count.toLong() * c)
    println(count)
    println(path.joinToString(" "))
}
