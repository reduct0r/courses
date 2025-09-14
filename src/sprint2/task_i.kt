package sprint2

class Queue(
    private val n: Int
) {
    private val queue = Array<Int?>(n) { null }
    private var head = 0
    private var tail = 0
    private var size = 0

    fun push(x: Int) {
        if (size != n) {
            queue[tail] = x
            tail = (tail + 1) % n
            size++
        } else {
            println("error")
        }
    }

    fun pop(): Int? {
        val x = peek()
        if (x != null) {
            queue[head] = null
            head = (head + 1) % n
            size--
        }
        return x
    }

    fun peek(): Int? {
        if (size == 0) {
            return null
        }
        return queue[head]
    }

    fun size(): Int {
        return size
    }
 }


fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val size = reader.readLine().toInt()
    val queue = Queue(size)

    repeat(n) {
        when (val command = reader.readLine().toString()) {
            "peek" -> {
                println(queue.peek() ?: "None")
            }
            "pop" -> {
                println(queue.pop() ?: "None")
            }
            "size" -> {
                println(queue.size())
            }
            else -> {
                queue.push(command.split(" ")[1].toInt())
            }
        }
    }
}