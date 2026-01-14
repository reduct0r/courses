package sprint8

fun main() {
    val reader= System.`in`.bufferedReader()
    val str = reader.readLine()
    val reversed = buildString { append(str.split(" ").reversed().joinToString(" ")) }
    print(reversed)
}