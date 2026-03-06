package sprint6_j

import java.util.Stack

val order = Stack<Int>()

fun main() {
    val reader = System.`in`.bufferedReader()
    val (numOfVertex, numOfEdges) = reader.readLine().split(" ").map { it.toInt() }
    val pairsOfVertex = mutableListOf<Pair<Int, Int>>()

    repeat(numOfEdges) {
        val (first, second) = reader.readLine().split(" ").map { it.toInt() }
        pairsOfVertex.add(Pair(first, second))
    }

    val list = makeAdjacencyList(pairsOfVertex, numOfVertex)
    val color = MutableList(numOfVertex) { "white" }
    topSort(numOfVertex, color, list)

    val res = buildString {
        repeat(numOfVertex) {
            append(order.pop() + 1)
            append(" ")
        }
    }.trim()

    print(res)
}

fun topSort(numOfVertex: Int, c: MutableList<String>, list: List<MutableList<Int>>) {
    for (i in 0 until numOfVertex) {
        if (c[i] == "white") {
            dfsFromVertex(i, list, c)
        }
    }
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

fun dfsFromVertex(v: Int, list: List<MutableList<Int>>, c: MutableList<String>) {
    c[v] = "gray"
    for (w in list[v]) {
        if (c[w] == "white") {
            dfsFromVertex(w, list, c)
        }
    }
    c[v] = "black"
    order.push(v)
}