package sprint1

fun main() {
    val reader = System.`in`.bufferedReader()
    val str = reader.readLine().trim()

    print(isPalindrome(str))
}

// 97-122, 65-90, 48-57
fun isPalindrome(str: String): String {
    var left = 0
    var right = str.length - 1
    while (left < right) {
        while (left < right && !isLetterOrNum(str[left])) left++
        while (left < right && !isLetterOrNum(str[right])) right--

        if (left < right && str[left].lowercase() != str[right].lowercase()) {
            return "False"
        }
        left++
        right--
    }
    return "True"
}

fun isLetterOrNum(c: Char): Boolean {
    return c.code in 97..122 || c.code in 65..90 || c.code in 48..57
}
