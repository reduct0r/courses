package sprint4

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val map = mutableMapOf<String, Int>()

    repeat(n) {
        val str = reader.readLine()
        if (map[str] != null) {
            map[str] = map[str]!! + 1
        } else {
            map[str] = 1
        }
    }

    print(map.filter{ it.value > 0 }.keys.joinToString("\n"))
}