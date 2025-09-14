package sprint2

import java.lang.Integer.max

class StackMaxEf(
    var items: MutableList<Int> = mutableListOf(),
    var maxItems: MutableList<Int> = mutableListOf(),
) {
    fun push(n: Int) {
        items.add(n)

        if (maxItems.isEmpty()) {
            maxItems.add(n)
        } else {
            maxItems.add(max(maxItems.last(), n))
        }
    }

    fun pop(): Int? {
        if (items.isEmpty()) {
            return null
        }
        val res = items.last()
        items.removeLast()
        maxItems.removeLast()

        return res
    }

    fun getMax(): Int? {
        if (items.isEmpty()) {
            return null
        }
        return maxItems.last()
    }

    fun top(): Int? {
        return if (items.isNotEmpty()) {
            items.last()
        } else null
    }
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val stack = StackMaxEf()

    repeat(n) {
        when (val command = reader.readLine().toString()) {
            "get_max" -> {
                println(stack.getMax() ?: "None")
            }
            "pop" -> {
               stack.pop() ?: println("error")
            }
            "top" -> {
                println(stack.top() ?: "error")
            }
            else -> {
                stack.push(command.split(" ")[1].toInt())
            }
        }
    }
}
