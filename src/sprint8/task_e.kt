package sprint8

fun main() {
    val reader = System.`in`.bufferedReader()
    val baseStr = reader.readLine()
    val strList = mutableListOf<Pair<String, Int>>()
    repeat(reader.readLine().toInt()) {
        val (str, pos) = reader.readLine().split(" ")
        strList.add(Pair(str, pos.toInt()))
    }

    print(mergeStrings(baseStr, strList))
}

fun mergeStrings(baseStr: String, strList: MutableList<Pair<String, Int>>): String {
    val sortedList = strList.sortedBy { it.second }
    val resultBuilder = StringBuilder()
    var currentPos = 0
    for (el in sortedList) {
        resultBuilder.append(baseStr.substring(currentPos, el.second))
        resultBuilder.append(el.first)
        currentPos = el.second
    }
    resultBuilder.append(baseStr.substring(currentPos))
    return resultBuilder.toString()
}
