package sprint6_k


class Graph (
    val vertices: MutableList<Int>,
    val dist: MutableList<Int>,
    val previous: MutableList<Int?>,
    val visited: MutableList<Boolean>,
    val adj: List<MutableList<Pair<Int, Int>>>
) {
    fun outgoingEdges(u: Int): List<Pair<Int, Int>> {
        return adj[u]
    }

    fun getMinDistNotVisited(): Int? {
        var currentMinimum = Int.MAX_VALUE
        var currentMinimumVertex: Int? = null

        for (v in vertices) {
            if (!visited[v] && dist[v] < currentMinimum) {
                currentMinimum = dist[v]
                currentMinimumVertex = v
            }
        }
        return currentMinimumVertex
    }
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }

    val adj = List(n) { mutableListOf<Pair<Int, Int>>() }
    repeat(m) {
        val (u, v, l) = reader.readLine().split(" ").map { it.toInt() }
        adj[u - 1].add(Pair(l, v - 1))
        adj[v - 1].add(Pair(l, u - 1))
    }

    val distMatrix = Array(n) { Array(n) { -1 } }

    for (i in 0 until n) {
        val vertices = MutableList(n) { it }
        val dist = MutableList(n) { Int.MAX_VALUE }
        val previous = MutableList<Int?>(n) { null }
        val visited = MutableList(n) { false }
        val graph = Graph(vertices, dist, previous, visited, adj)
        dijkstra(graph, i)
        for (j in 0 until n) {
            distMatrix[i][j] = if (graph.dist[j] == Int.MAX_VALUE) -1 else graph.dist[j]
        }
        distMatrix[i][i] = 0
    }

    for (row in distMatrix) {
        println(row.joinToString(" "))
    }
}


fun dijkstra(graph: Graph, s: Int) {
    for (v in graph.vertices) {
        graph.dist[v] = Int.MAX_VALUE
        graph.previous[v] = null
        graph.visited[v] = false
    }

    graph.dist[s] = 0

    while (true) {
        val u = graph.getMinDistNotVisited()

        if (u == null || graph.dist[u] == Int.MAX_VALUE) {
            break
        }
        graph.visited[u] = true

        val neighbours = graph.outgoingEdges(u)

        for ((weight, v) in neighbours) {
            if (graph.dist[v] > graph.dist[u] + weight) {
                graph.dist[v] = graph.dist[u] + weight
                graph.previous[v] = u
            }
        }
    }
}

