package sprint1

fun main() {
    val outputBuffer = StringBuilder()
    val reader = System.`in`.bufferedReader()
    val numLines = reader.readLine().toInt()
    repeat(numLines) {
        val (firstValue, secondValue) = reader.readLine().split(" ").map { it.toInt() }
        val result = firstValue + secondValue
        outputBuffer.appendLine(result)
    }
    println(outputBuffer.toString())
}