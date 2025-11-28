package sprint6

fun main() {
    val reader = System.`in`.bufferedReader()
    val (numOfVertex, numOfEdges) = reader.readLine().split(" ").map { it.toInt() }
    val pairsOfVertex = mutableListOf<Pair<Int, Int>>()

    repeat(numOfEdges) {
        val (first, second) = reader.readLine().split(" ").map { it.toInt() }
        pairsOfVertex.add(Pair(first, second))
    }

    val list = makeAdjacencyMatrix(pairsOfVertex, numOfVertex, numOfEdges)

    val result = buildString {
        list.forEach { innerList ->
            append(innerList.joinToString(separator = " "))
            appendLine()
        }
    }

    print(result)
}

fun makeAdjacencyMatrix(pairs: MutableList<Pair<Int, Int>>, numOfVertex: Int, m: Int): List<MutableList<Int>> {
    val associationMatrix= List(numOfVertex) { MutableList(numOfVertex) { 0 } }

    for (pair in pairs) {
        associationMatrix[pair.first - 1][pair.second - 1] = 1
    }

    return associationMatrix
}