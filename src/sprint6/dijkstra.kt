package sprint6_my

import kotlin.collections.get
import kotlin.collections.set
import kotlin.text.compareTo

class Graph(
    val numOfVertex: Int
) {
    private val matrix = MutableList<Int?>(numOfVertex * numOfVertex) { null }
    var visited = MutableList(numOfVertex) { false }
    var shortestDistToVertex = MutableList(numOfVertex) { Int.MAX_VALUE }
    private val distMatrix = MutableList(numOfVertex * numOfVertex) { Int.MAX_VALUE }

    fun addEdge(v: Int, u: Int, weight: Int) {
        if (matrix[v * numOfVertex + u] == null) {
            matrix[v * numOfVertex + u] = weight
            matrix[u * numOfVertex + v] = weight
        } else {
            throw IllegalArgumentException()
        }
    }

    fun getOutWithWeight(v: Int): MutableList<Pair<Int, Int?>> {
        val list = mutableListOf<Pair<Int, Int?>>()
        for (i in 0 until numOfVertex) {
            list.add(Pair(i, matrix[v * numOfVertex + i]))
        }
        return list
    }

    fun setVisited(v: Int) {
        visited[v] = true
    }

    fun getNumberOfEdges(): Int {
        return matrix.count { it != null }
    }

    fun getMinNotVisitedVertex(): Int? {
        var currentMin = Int.MAX_VALUE
        var minVertex: Int? = null
        for (i in 0 until numOfVertex) {
            if (!visited[i] && shortestDistToVertex[i] < currentMin) {
                currentMin = shortestDistToVertex[i]
                minVertex = i
            }
        }
        return minVertex
    }

    fun getShortestDist(v: Int): Int {
        return shortestDistToVertex[v]
    }

    fun matrixToSB(nullPath: String = "0"): StringBuilder {
        val builder = StringBuilder()
        for (i in 0 until numOfVertex) {
            for (j in 0 until numOfVertex) {
                val value = matrix[i * numOfVertex + j]
                builder.append(value?.toString() ?: nullPath)
                builder.append(" ")
            }
            builder.appendLine()
        }
        return builder
    }

    fun reset() {
        visited = MutableList(numOfVertex) { false }
        shortestDistToVertex = MutableList(numOfVertex) { Int.MAX_VALUE }
    }

    fun saveDist(v: Int){
        for (j in 0 until numOfVertex) {
            distMatrix[v * numOfVertex + j] = if (shortestDistToVertex[j] >= Int.MAX_VALUE) -1 else shortestDistToVertex[j]
        }
    }

    fun pathsToStr(): String{
        val builder = StringBuilder()
        for (i in 0 until numOfVertex) {
            for (j in 0 until numOfVertex) {
                builder.append(distMatrix[i + j])
                builder.append(" ")
            }
            builder.appendLine()
        }
        return builder.toString()
    }

}

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Graph(n)

    repeat(m) {
        val (v, u, w) = reader.readLine().split(" ").map { it.toInt() }
        graph.addEdge(v - 1, u - 1, w)
    }

    for (i in 0 until n) {
        graph.reset()
        dijkstra(graph, i)
    }

    print(graph.pathsToStr())
}

fun dijkstra(graph: Graph, start: Int) {
    graph.shortestDistToVertex[start] = 0

    while (true) {
        val v = graph.getMinNotVisitedVertex()
        if (v == null || graph.getShortestDist(v) == Int.MAX_VALUE) {
            break
        }

        graph.setVisited(v)
        val out = graph.getOutWithWeight(v)

        for ((u, w) in out) {
            if (w != null) {
                val newDist = graph.getShortestDist(v) + w
                if (newDist < graph.getShortestDist(u)) {
                    graph.shortestDistToVertex[u] = newDist
                }
            }
        }
    }
    graph.saveDist(start)
}

