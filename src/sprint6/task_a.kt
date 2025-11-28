package sprint6

fun main() {
    val reader = System.`in`.bufferedReader()
    val (numOfVertex, numOfEdges) = reader.readLine().split(" ").map { it.toInt() }
    val pairsOfVertex = mutableListOf<Pair<Int, Int>>()

    repeat(numOfEdges) {
        val (first, second) = reader.readLine().split(" ").map { it.toInt() }
        pairsOfVertex.add(Pair(first, second))
    }

    val list = makeAdjacencyList(pairsOfVertex, numOfVertex, numOfEdges)

    val result = buildString {
        list.drop(1).forEach { innerList ->
            append(innerList.size)
            append(" ")
            append(innerList.joinToString(separator = " "))
            appendLine()
        }
    }

    print(result)
}

fun makeAdjacencyList(pairs: MutableList<Pair<Int, Int>>, numOfVertex: Int, m: Int): List<MutableList<Int>> {
    val associationList = List<MutableList<Int>>(numOfVertex + 1) { mutableListOf() }

    for (pair in pairs) {
        associationList[pair.first].add(pair.second)
    }

    return associationList
}