package sprint3

const val MAX_ELEMENT = 3

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val stringArr = reader.readLine().trim()
    if (stringArr == "") {
        print("")
    } else {
        val arr = stringArr.split(" ").map { it.toInt() }
        println(sortDresses(arr).joinToString(" "))
    }
}

fun sortDresses(arr: List<Int>): MutableList<Int> {
    val countArr = MutableList(MAX_ELEMENT + 1) { 0 }
    val resArr = mutableListOf<Int>()
    var ptr = 0

    for (el in arr) {
        countArr[el]++
    }

    while (ptr < countArr.size - 1) {
        if (countArr[ptr] != 0) {
            resArr.add(ptr)
            countArr[ptr]--
        } else {
            ptr++
        }
    }

    return resArr
}
