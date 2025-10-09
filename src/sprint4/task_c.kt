package sprint4

fun main() {
    val reader = System.`in`.bufferedReader()
    val str1 = reader.readLine()
    val str2 = reader.readLine()

    print(if(customCompareStrings(str1, str2)) "YES" else "NO")
}

fun customCompareStrings(str1: String, str2: String): Boolean {
    if (str1.length != str2.length) return false
    if (str1 == str2) return true

    val map1 = mutableMapOf<Char, Int>()
    val map2 = mutableMapOf<Char, Int>()

    for (i in str1.indices) {
        map1[str1[i]] = i
        map2[str2[i]] = i
    }

    return map1.values.toIntArray().contentEquals(map2.values.toIntArray())
}
