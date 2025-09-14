package sprint2

class StackMax(
    var items: MutableList<Int> = mutableListOf(),
    var pointer: Int = -1
) {
    fun push(n: Int){
        items.add(n)
        pointer++
    }

    fun pop(): Int? {
        if (items.isEmpty()) {
            return null
        }
        val res = items[pointer]
        items.removeAt(pointer)
        pointer--
        return res
    }

    fun getMax(): Int? {
        if (items.isEmpty()) {
            return null
        }
        return items.max()
    }
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val stack = StackMax()

    repeat(n) {
        when (val command = reader.readLine().toString()) {
            "get_max" -> {
                println(stack.getMax() ?: "None")
            }
            "pop" -> {
                stack.pop() ?: println("error")
            }
            else -> {
                stack.push(command.split(" ")[1].toInt())
            }
        }
    }
}
