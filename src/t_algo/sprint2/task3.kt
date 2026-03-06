package t_algo.sprint2

import java.util.ArrayDeque

fun main() {
    val reader = System.`in`.bufferedReader()

    print(calculatePostfix(reader.readLine()))
}

fun calculatePostfix(expr: String): Int {
    val stack = ArrayDeque<Int>()

    expr.split(" ").filter { it.isNotEmpty() }.forEach { token ->
        when (token) {
            "+" -> {
                val b = stack.removeLast()
                val a = stack.removeLast()
                stack.addLast(a + b)
            }
            "-" -> {
                val b = stack.removeLast()
                val a = stack.removeLast()
                stack.addLast(a - b)
            }
            "*" -> {
                val b = stack.removeLast()
                val a = stack.removeLast()
                stack.addLast(a * b)
            }
            else -> {
                stack.addLast(token.toInt())
            }
        }
    }
    return stack.last()
}

