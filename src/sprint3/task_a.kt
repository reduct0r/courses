package sprint3

fun main() {
    val n = System.`in`.bufferedReader().readLine().trim().toInt()
    generateBrackets(n * 2 - 1, "(", 1, 0)
}

fun generateBrackets(n: Int, prefix: String, open: Int, close: Int) {
    if (open + close == n + 1) {
        println(prefix)
    }  else {
        if (open <= n / 2) {
            generateBrackets(n, "$prefix(", open + 1, close)
        }
        if (close < open) {
            generateBrackets(n, "$prefix)", open, close + 1)
        }
    }
}