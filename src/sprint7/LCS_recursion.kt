package sprint7

fun main() {
    val reader = System.`in`.bufferedReader()
    val str1 = reader.readLine().split(" ")
    val str2 = reader.readLine().split(" ")

    print(LCS(str1, len1 = str1.size, str2, len2 = str2.size))
}

fun LCS(str1: List<String>, len1: Int, str2: List<String>, len2: Int): Int {
    if (len1 == 0 || len2 == 0) {
        return 0
    }

    if (str1[len1 - 1] == str2[len2 - 1]) {
        return 1 + LCS(str1, len1 - 1, str2, len2 - 1)
    } else {
        return maxOf(
            LCS(str1, len1 - 1, str2, len2),
            LCS(str1, len1, str2, len2 - 1)
        )
    }
}
