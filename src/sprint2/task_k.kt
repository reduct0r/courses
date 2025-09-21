package sprint2

fun findFib(n: Int): Int {
    if (n == 0 || n == 1) {
        return 1
    }
    return  findFib(n - 1) +  findFib(n - 2)
}

fun main() {
    val reader = System.`in`.bufferedReader()
    println(findFib(reader.readLine().toInt()))
}