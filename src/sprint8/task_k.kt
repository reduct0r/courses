package sprint8

fun main() {
    val reader = System.`in`.bufferedReader()
    val a = reader.readLine()
    val b = reader.readLine()

    print(compareStrings(a, b))
}

fun compareStrings(a: String, b: String): Int {
    val a = a.filter { (it.toInt() - 96) % 2 == 0 }
    val b = b.filter { (it.toInt() - 96) % 2 == 0 }

    return when {
        a > b -> 1
        a < b -> -1
        else -> 0
    }
}
