package sprint8

import kotlin.math.absoluteValue

fun main() {
    val reader = System.`in`.bufferedReader()
    reader.readLine()
    val seq = reader.readLine().split(" ").map{ it.toInt() }
    reader.readLine()
    val pattern = reader.readLine().split(" ").map{ it.toInt() }

    print(findAll(seq, pattern).map { it + 1 }.joinToString(" "))
}

fun findAll(seq: List<Int>, pattern: List<Int>,): MutableList<Int> {
    if (pattern.size == 1) return (0 until seq.size).toMutableList()
    if (pattern.size > seq.size) return emptyList<Int>().toMutableList()

    val occurrences = mutableListOf<Int>()

    for (i in 0 .. seq.size - pattern.size) {
        var match = true
        val c = seq[i] - pattern[0]

        for (offset in 0 until pattern.size) {
            if (seq[i + offset] - pattern[offset] != c) {
                match = false
                break
            }
        }
        if (match) {
            occurrences.add(i)
        }

    }

    return(occurrences)
}

