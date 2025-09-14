package sprint2

import sprint1.emptyEl
import java.util.StringTokenizer

fun transpose(list: MutableList<Int>, n: Int, m: Int): List<Int> {
    val ans = mutableListOf<Int>()
    val size = list.size

    for (i in 0 until size) {
        val shift = i / n
        ans.add(list[(i * m) % size + shift])
    }

    return ans
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val m = reader.readLine().toInt()
    val matrixLine: MutableList<Int> = mutableListOf()
    val builder = StringBuilder()

    repeat(n) {
        val tokenizer = StringTokenizer(reader.readLine())
        repeat(m) {
            matrixLine.add(tokenizer.nextToken().toInt())
        }
    }

    val res = transpose(matrixLine, n, m)

    for (i in 1 .. m * n) {
        builder.append(res[i - 1])
        builder.append(" ")
        if (i % n == 0 && i != n * m) {
            builder.append("\n")
        }
    }

    println(builder.toString())
}
