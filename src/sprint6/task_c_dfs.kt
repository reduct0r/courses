package sprint6_c

fun main() {
    val reader = System.`in`.bufferedReader()
    val (numOfVertex, numOfEdges) = reader.readLine().split(" ").map { it.toInt() }
    val pairsOfVertex = mutableListOf<Pair<Int, Int>>()

    repeat(numOfEdges) {
        val (first, second) = reader.readLine().split(" ").map { it.toInt() }
        pairsOfVertex.add(Pair(first, second))
    }

    val startVertex = reader.readLine().toInt() - 1
    val color = MutableList(numOfVertex) { "white" }
    val list = makeAdjacencyList(pairsOfVertex, numOfVertex)

    val resPath = mutableListOf<Int>()

    DFSFromVertex(startVertex, list, color, resPath)

    val res = buildString {
        resPath.forEach {
            append(it + 1)
            append(" ")
        }
    }.trim()

    print(res)
}


fun makeAdjacencyList(pairs: MutableList<Pair<Int, Int>>, numOfVertex: Int): List<MutableList<Int>> {
    val associationList = List<MutableList<Int>>(numOfVertex) { mutableListOf() }
    for (pair in pairs) {
        associationList[pair.first - 1].add(pair.second - 1)
        associationList[pair.second - 1].add(pair.first - 1)
    }
    for (i in 0 until associationList.size) {
        associationList[i].sort()
    }
    return associationList
}

fun DFSFromVertex(v: Int, list: List<MutableList<Int>>, c: MutableList<String>, res: MutableList<Int>) {
    c[v] = "gray"
    res.add(v)
    for (w in list[v]) {
        if (c[w] == "white") {
            DFSFromVertex(w, list, c, res)
        }
    }
}
