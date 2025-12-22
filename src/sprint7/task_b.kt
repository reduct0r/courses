package sprint7

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val lessons = mutableListOf<Pair<Double, Double>>()

    repeat(n) {
        val (start, end) = reader.readLine().split(" ").map { it.toDouble() }
        lessons.add(Pair(start, end))
    }

    lessons.sortWith(compareBy({it.second}, {it.first}))

    val res = findMaxLessons(lessons)
    val output = buildString {
        append(res.size)
        appendLine()
        for (el in res) {
            append("${el.first} ")
            append(el.second)
            appendLine()
        }
    }.trim()

    print(output)
}

fun findMaxLessons(list: MutableList<Pair<Double, Double>>): MutableList<Pair<Double, Double>> {
    var currentTime = Double.MIN_VALUE / 2
    val res = mutableListOf<Pair<Double, Double>>()
    for (l in list) {
        if (l.first >= currentTime) {
            currentTime = l.second
            res.add(l)
        }
    }
    return res
}
