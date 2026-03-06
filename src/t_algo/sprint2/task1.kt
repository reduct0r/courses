package t_algo.sprint2

class MyStack<T : Comparable<T>>() {
    private val arr = mutableListOf<T>()
    private val arrayOfMins = mutableListOf<T>()

    fun push(x: T) {
        arr.add(x)
        if (arrayOfMins.isEmpty() || arrayOfMins.last() >= x) {
            arrayOfMins.add(x)
        }
    }

    fun pop() {
        if (arrayOfMins.last() == arr.removeLast()) {
            arrayOfMins.removeLast()
        }
    }

    fun getMin(): T {
        return arrayOfMins.last()
    }

}



fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val stack = MyStack<Int>()

    repeat(n) {
        val input = reader.readLine()
        when (input[0].digitToInt()) {
            1 -> {
                val value = input.split(" ").last().toInt()
                stack.push(value)
            }
            2 -> {
                stack.pop()
            }
            3 -> {
                println(stack.getMin())
            }
            else -> throw IllegalArgumentException("Unknown operation")
        }
    }
}