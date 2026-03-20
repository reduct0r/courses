package t_algo.sprint3

import java.util.*
import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val nLine = br.readLine() ?: return
    val n = nLine.trim().toInt()

    if (n == 1) {
        println("0 0")
        println("0")
        return
    }

    val adj = Array(n) { mutableListOf<Int>() }
    val st = StringTokenizer(br.readLine())
    for (i in 1 until n) {
        val p = st.nextToken().toInt()
        adj[p].add(i)
        adj[i].add(p)
    }

    fun bfs(startNode: Int): Pair<IntArray, Int> {
        val dists = IntArray(n) { -1 }
        val queue: Deque<Int> = ArrayDeque()

        dists[startNode] = 0
        queue.add(startNode)

        var farthestNode = startNode

        while (queue.isNotEmpty()) {
            val u = queue.poll()
            if (dists[u] > dists[farthestNode]) {
                farthestNode = u
            }
            for (v in adj[u]) {
                if (dists[v] == -1) {
                    dists[v] = dists[u] + 1
                    queue.add(v)
                }
            }
        }
        return Pair(dists, farthestNode)
    }

    val (depths, nodeA) = bfs(0)
    val height = depths[nodeA]

    val (distFromA, nodeB) = bfs(nodeA)
    val diameter = distFromA[nodeB]

    println("$height $diameter")

    val sb = StringBuilder()
    for (i in 0 until n) {
        sb.append(depths[i]).append(if (i == n - 1) "" else " ")
    }
    println(sb)
}
