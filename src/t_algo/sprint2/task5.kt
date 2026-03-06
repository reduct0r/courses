package t_algo.sprint2

import java.io.BufferedReader
import java.util.StringTokenizer

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    sortWagons(n, reader)
}

fun sortWagons(n: Int, reader: BufferedReader) {
    val deque = ArrayDeque<Int>()
    val sc = StringTokenizer(reader.readLine())
    val ans = mutableListOf<Pair<Int, Int>>()
    var needWagonNum = 1

    while (sc.hasMoreElements()) {
        val num = sc.nextToken().toInt()
        deque.add(num)
        ans.add(1 to 1)

        while (deque.isNotEmpty() && deque.last() == needWagonNum) {
            deque.removeLast()
            needWagonNum++
            ans.add(2 to 1)
        }
    }

    if (deque.isNotEmpty()) {
        println(0)
    } else {
        printCompressed(ans)
    }
}

fun printCompressed(actions: List<Pair<Int, Int>>) {
    val compressed = mutableListOf<Pair<Int, Int>>()
    for (action in actions) {
        if (compressed.isNotEmpty() && compressed.last().first == action.first) {
            val last = compressed.removeAt(compressed.size - 1)
            compressed.add(last.first to last.second + action.second)
        } else {
            compressed.add(action)
        }
    }

    println(compressed.size)
    compressed.forEach { println("${it.first} ${it.second}") }
}
