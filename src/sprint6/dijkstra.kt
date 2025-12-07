package sprint6_my

class Graph(
    val numOfVertex: Int
) {
    val matrix: Array<Int?> = arrayOfNulls(numOfVertex * numOfVertex)
    var visited: BooleanArray = BooleanArray(numOfVertex)
    var shortestDistToVertex: LongArray = LongArray(numOfVertex) { Long.MAX_VALUE / 2 }
    private val distMatrix: IntArray = IntArray(numOfVertex * numOfVertex) { -1 }

    fun addEdge(v: Int, u: Int, weight: Int) {
        val path = matrix[v * numOfVertex + u]
        if (path == null) {
            matrix[v * numOfVertex + u] = weight
            matrix[u * numOfVertex + v] = weight
        } else {
            if (path > weight) {
                matrix[v * numOfVertex + u] = weight
                matrix[u * numOfVertex + v] = weight
            }
        }
    }

    fun setVisited(v: Int) {
        visited[v] = true
    }

    fun getMinNotVisitedVertex(): Int? {
        var currentMin = Long.MAX_VALUE / 2
        var minVertex: Int? = null
        for (i in 0 until numOfVertex) {
            if (!visited[i] && shortestDistToVertex[i] < currentMin) {
                currentMin = shortestDistToVertex[i]
                minVertex = i
            }
        }
        return minVertex
    }

    fun getShortestDist(v: Int): Long {
        return shortestDistToVertex[v]
    }

    fun reset() {
        visited = BooleanArray(numOfVertex)
        shortestDistToVertex = LongArray(numOfVertex) { Long.MAX_VALUE / 2 }
    }

    fun saveDist(v: Int){
        for (j in 0 until numOfVertex) {
            distMatrix[v * numOfVertex + j] = if (shortestDistToVertex[j] == Long.MAX_VALUE / 2) -1 else shortestDistToVertex[j].toInt()
        }
    }

    fun pathsToStr(): String{
        val builder = StringBuilder()
        for (i in 0 until numOfVertex) {
            for (j in 0 until numOfVertex) {
                builder.append(distMatrix[i * numOfVertex + j])
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
        graph.saveDist(i)
    }

    print(graph.pathsToStr())
}

fun dijkstra(graph: Graph, start: Int) {
    graph.shortestDistToVertex[start] = 0L

    while (true) {
        val v = graph.getMinNotVisitedVertex()
        if (v == null || graph.getShortestDist(v) == Long.MAX_VALUE / 2) {
            break
        }

        graph.setVisited(v)

        for (u in 0 until graph.numOfVertex) {
            val w = graph.matrix[v * graph.numOfVertex + u] ?: continue
            val newDist = graph.getShortestDist(v) + w.toLong()
            if (newDist < graph.getShortestDist(u)) {
                graph.shortestDistToVertex[u] = newDist
            }
        }
    }
}