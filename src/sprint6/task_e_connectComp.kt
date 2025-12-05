package sprint6_e

var colorCounter = 1

fun main() {
    val reader = System.`in`.bufferedReader()
    val (nVertex, nEdges) = reader.readLine().split(" ").map { it.toInt() }
    val pairsList = mutableListOf<Pair<Int, Int>>()

    repeat(nEdges) {
        val (first, second) = reader.readLine().split(" ").map { it.toInt() }
        pairsList.add(Pair(first, second))
    }

    val color = MutableList(nVertex) { -1 }
    val aList = makeAdjacencyList(pairsList, nVertex)

    findConnComps(color, aList)

    printComponents(color)
}

fun printComponents(color: MutableList<Int>) {
    if (color.isEmpty()) {
        print("0")
        return
    }

    val numComponents = color.toSet().size

    val components = mutableMapOf<Int, MutableList<Int>>()

    for (i in 0 until color.size) {
        val c = color[i]
        components.getOrPut(c) { mutableListOf() }.add(i + 1)
    }

    val builder = StringBuilder()
    builder.append("$numComponents\n")
    for (comp in components) {
        builder.append(comp.component2().joinToString(" "))
        builder.append("\n")
    }

    print(builder.toString())
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

fun findConnComps(color: MutableList<Int>, aList: List<MutableList<Int>>) {
    for (i in 0 until color.size) {
        if (color[i] == -1) {
            findViaDfs(i, color, aList)
            colorCounter++
        }
    }
}

fun findViaDfs(v: Int, color: MutableList<Int>, aList: List<MutableList<Int>>) {
    color[v] = colorCounter
    for (w in aList[v]) {
        if (color[w] == -1) {
            findViaDfs(w, color, aList)
        }
    }
}