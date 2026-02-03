package practice

fun main() {
    val reader = System.`in`.bufferedReader()
    val s = reader.readLine().map { it }

    var zerosCount = s.count { it == '0' }
    val nonZeros = s.filter { it != '0' }.sorted()

    val output = buildString {
        for (i in nonZeros) {
            append(i)
            repeat(zerosCount) { append('0') }
            zerosCount = 0
        }
    }

    print(output)
}