package t_algo.sprint1

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine()?.trim()?.toInt() ?: return
    val s = br.readLine() ?: ""

    val count = IntArray(26)
    for (char in s) {
        count[char - 'A']++
    }

    val leftPart = StringBuilder()
    var middleChar = ""

    for (i in 0 until 26) {
        val char = ('A' + i)
        val occurrences = count[i]

        repeat(occurrences / 2) {
            leftPart.append(char)
        }

        if (occurrences % 2 != 0 && middleChar == "") {
            middleChar = char.toString()
        }
    }

    val result = StringBuilder()
    result.append(leftPart)
    result.append(middleChar)
    result.append(leftPart.reverse())

    println(result.toString())
}
