package sprint2

import java.util.Stack

fun isCorrectBracketSeq(str: String): String {
    val stack = Stack<Char>()

    if (str.length % 2 != 0)
        return "False"

    if (str.isEmpty())
        return "True"

    if (str[0] == ')' || str[0] == ']' || str[0] == '}') {
        return "False"
    }

    for (el in str) {
        when (el) {
            ')' -> {
                if (stack.isEmpty()) return "False"
                if (stack.peek() == '(')
                    stack.pop()
            }
            ']' -> {
                if (stack.isEmpty()) return "False"
                if (stack.peek() == '[')
                    stack.pop()
            }
            '}' -> {
                if (stack.isEmpty()) return "False"
                if (stack.peek() == '{')
                    stack.pop()
            }
            else -> {
                stack.push(el)
            }
        }
    }
    return if (stack.empty()) {"True"} else "False"
}

fun main() {
    val reader = System.`in`.bufferedReader()
    println(isCorrectBracketSeq(reader.readLine().toString().trim()))
}