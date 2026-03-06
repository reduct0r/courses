package sprint6_h

var count = -1

fun main() {
    val reader = System.`in`.bufferedReader()
    val (numOfVertex, numOfEdges) = reader.readLine().split(" ").map { it.toInt() }
    val pairsOfVertex = mutableListOf<Pair<Int, Int>>()

    repeat(numOfEdges) {
        val (first, second) = reader.readLine().split(" ").map { it.toInt() }
        pairsOfVertex.add(Pair(first, second))
    }

    val entry = MutableList(numOfVertex) { -1 }
    val leave = MutableList(numOfVertex) { -1 }

    val list = makeAdjacencyList(pairsOfVertex, numOfVertex)
    val color = MutableList(numOfVertex) { "white" }

    DFSFromVertexTimed(0, entry, leave, list, color)

    val res = buildString {
        for (i in 0 until numOfVertex) {
            append(entry[i])
            append(" ")
            append(leave[i])
            appendLine()
        }
    }

    print(res)
}

fun makeAdjacencyList(pairs: MutableList<Pair<Int, Int>>, numOfVertex: Int): List<MutableList<Int>> {
    val associationList = List<MutableList<Int>>(numOfVertex) { mutableListOf() }
    for (pair in pairs) {
        associationList[pair.first - 1].add(pair.second - 1)
    }
    for (i in 0 until associationList.size) {
        associationList[i].sort()
    }
    return associationList
}

fun DFSFromVertexTimed(v: Int, e: MutableList<Int>, l: MutableList<Int>,
                       list: List<MutableList<Int>>, c: MutableList<String>) {
    count++
    c[v] = "grey"
    e[v] = count

    for (w in list[v]) {
        if (c[w] == "white") {
            DFSFromVertexTimed(w, e, l, list, c)
        }
    }
    c[v] = "black"
    count++
    l[v] = count
}