package sprint2

class NodeQ<T>(
    var value: T?,
    var next: NodeQ<T>? = null,
)
class LinkedListQueue<V>() {
    private var tail = NodeQ<V>(null)
    private var head: NodeQ<V>? = tail
    private var size = 0
    private var prev = tail

    fun put(x: V) {
        if (size == 0) {
            tail.value = x
            head = tail
            size++
        } else {
            prev = tail
            tail = NodeQ(x, prev)
            size++
        }
    }

    fun get(): V? {
        if (size == 0) {
            return null
        }

        val x: V? = head?.value
        var preHead: NodeQ<V>? = tail

        repeat(size - 2) {
            preHead = preHead?.next
        }
        head = preHead
        size--

        return x
    }

    fun size(): Int {
        return size
    }

    fun printQ(): String {
        var curr: NodeQ<V>? = tail
        val ans = StringBuilder()
        ans.append("null <- ")
        while (curr != null) {
            ans.append(curr.value.toString())
            ans.append(" <- ")
            curr = curr.next
        }
        return ans.toString()
    }
}

fun main() {
    val queue = LinkedListQueue<Int>()
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()

    repeat(n) {
        when (val command = reader.readLine().toString()) {
            "get" -> {
                println(queue.get() ?: "error")
            }
            "size" -> {
                println(queue.size())
            }
            else -> {
                queue.put(command.split(" ")[1].toInt())
            }
        }
    }

}