package sprint6_fa

const val MIN_PATH_LEN = Long.MIN_VALUE / 2

class Graph(
    val numOfVertex: Int
) {
    val matrix: Array<Int?> = arrayOfNulls(numOfVertex * numOfVertex)
    val visited: BooleanArray = BooleanArray(numOfVertex)
    val longestDistToVertex: LongArray = LongArray(numOfVertex) { MIN_PATH_LEN }
    val maxSpanTreeWeights = mutableListOf<Long>()

    fun addEdge(v: Int, u: Int, weight: Int) {
        val path = matrix[v * numOfVertex + u]
        if (path == null) {
            matrix[v * numOfVertex + u] = weight
            matrix[u * numOfVertex + v] = weight
        } else if (path < weight) {
            matrix[v * numOfVertex + u] = weight
            matrix[u * numOfVertex + v] = weight
        }
    }

    fun getMaxNotVisitedVertex(): Int? {
        var currentMax = MIN_PATH_LEN
        var maxVertex: Int? = null
        for (i in 0 until numOfVertex) {
            if (!visited[i] && longestDistToVertex[i] > currentMax) {
                currentMax = longestDistToVertex[i]
                maxVertex = i
            }
        }
        return maxVertex
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

    //print(findMaxSpanTree(graph).last())
    findMaxSpanTree(graph).forEach { print("$it ") }
}

fun findMaxSpanTree(graph: Graph): MutableList<Long> {
    val start = 0
    graph.longestDistToVertex[start] = 0L

    while (true) {
        val v = graph.getMaxNotVisitedVertex()
        if (v == null || graph.longestDistToVertex[v] == MIN_PATH_LEN) {
            break
        }
        graph.visited[v] = true

        for (u in 0 until graph.numOfVertex) {
            val w = graph.matrix[v * graph.numOfVertex + u] ?: continue
            val newDist = graph.longestDistToVertex[v] + w.toLong()
            if (newDist > graph.longestDistToVertex[u]) {
                graph.longestDistToVertex[u] = newDist
            }
        }
        graph.maxSpanTreeWeights.add(graph.longestDistToVertex[v])
    }

    return graph.maxSpanTreeWeights
}
