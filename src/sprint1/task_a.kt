package sprint1

import java.util.StringTokenizer
import kotlin.math.pow

fun main() {
    val reader = System.`in`.bufferedReader()
    val str = reader.readLine()
    val tokenizer = StringTokenizer(str)

    val a = tokenizer.nextToken().toInt()
    val x = tokenizer.nextToken().toInt()
    val b = tokenizer.nextToken().toInt()
    val c = tokenizer.nextToken().toInt()

    println(a * x.toDouble().pow(2.0).toInt() + b * x + c)
}