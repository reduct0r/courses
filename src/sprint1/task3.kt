package sprint1

import java.util.StringTokenizer
import kotlin.text.appendLine

fun main() {
    val reader = System.`in`.bufferedReader()
    val arraySize = reader.readLine().toInt()
    val arrayInString = reader.readLine()
    val arrayAsTokens = StringTokenizer(arrayInString)
    val builder = StringBuilder()

//    val array = IntArray(arraySize) {
//        arrayAsTokens.nextToken().toInt()
//    }

    repeat(arraySize) {
        builder.append(arrayAsTokens.nextToken())
    }
    println(builder.toString())

    //array.forEach { print("$it ")}
}
