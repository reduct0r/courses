package sprint6_g

import java.util.LinkedList

var previous = mutableListOf<Int>()

fun main() {
    val reader = System.`in`.bufferedReader()
    val (nVertex, nEdges) = reader.readLine().split(" ").map { it.toInt() }
    val pairsList = mutableListOf<Pair<Int, Int>>()
    repeat(nEdges) {
        val (first, second) = reader.readLine().split(" ").map { it.toInt() }
        pairsList.add(Pair(first, second))
    }

    val aList = makeAList(pairsList, nVertex)
    val color = MutableList(nVertex) { "white" }
    val start = reader.readLine().toInt() - 1
    val distance = MutableList(nVertex) { -1 }

    distance[start] = 0
    bfs(start, color, aList, distance)
    print(distance.max())
}

fun makeAList(pairs: MutableList<Pair<Int, Int>>, nVertex: Int): List<MutableList<Int>> {
    val aList = List<MutableList<Int>>(nVertex) { mutableListOf() }
    for (pair in pairs) {
        aList[pair.first - 1].add(pair.second - 1)
        aList[pair.second - 1].add(pair.first - 1)
    }
    for (i in 0 until aList.size) {
        aList[i].sort()
    }
    return aList
}

fun bfs(v: Int, color: MutableList<String>, aList: List<MutableList<Int>>, distance: MutableList<Int>) {
    val planned = LinkedList<Int>()
    planned.add(v)
    color[v] = "gray"

    while (planned.isNotEmpty()) {
        val w = planned.poll()
        color[w] = "black"
        for (outBound in aList[w]) {
            if (color[outBound] == "white") {
                color[outBound] = "gray"
                distance[outBound] = distance[w] + 1
                planned.add(outBound)
            }
        }
    }
}