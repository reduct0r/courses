package sprint3

fun main() {
    val n = System.`in`.bufferedReader().readLine().trim()
    generateLetters(n, "", 0)
}

val map = mapOf<Int, String>(
    2 to "abc",
    3 to "def",
    4 to "ghi",
    5 to "jkl",
    6 to "mno",
    7 to "pqrs",
    8 to "tuv",
    9 to "wxyz"
)

fun generateLetters(digits: String, current: String, index: Int) {
    if (index == digits.length) {
        print("$current ")
        return
    }

    val letters = map[digits[index].digitToInt()] ?: ""
    for (char in letters) {
        generateLetters(digits, current + char, index + 1)
    }

}
