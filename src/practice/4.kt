package practice


fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val adj = Array(n + 1) { mutableListOf<Int>() }
    for (i in 1..m) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        adj[a].add(b)
        adj[b].add(a)
    }

    val order = (1..n).toMutableList()
    order.sortByDescending { adj[it].size }

    var girth = Int.MAX_VALUE

    for (start in order) {
        if (girth <= 3) break
        if (adj[start].size < 2) continue

        val dist = IntArray(n + 1) { -1 }
        val parent = IntArray(n + 1) { -1 }
        dist[start] = 0

        val q = ArrayDeque<Int>()
        q.add(start)

        while (q.isNotEmpty()) {
            val u = q.removeFirst()

            for (v in adj[u]) {
                if (v == parent[u]) continue

                if (dist[v] == -1) {
                    val newDist = dist[u] + 1
                    if (2 * newDist + 1 >= girth) continue

                    dist[v] = newDist
                    parent[v] = u
                    q.add(v)
                } else {
                    val cycleLen = dist[u] + dist[v] + 1
                    if (cycleLen < girth) {
                        girth = cycleLen
                    }
                }
            }
        }
    }

    println(if (girth == Int.MAX_VALUE) -1 else girth)
}