package task_d

// <template>
class Node<V>(var value: V, var next: Node<V>?) {}
// <template>

fun solution(head: Node<String>?, item: String): Int {
    var curr = head
    var firstIndex = 0
    var flag = false

    while (curr != null) {
        if (curr.value == item) {
            flag = true
            break
        }
        firstIndex++
        curr = curr.next
    }

    return if (flag) {
        firstIndex
    } else -1
}

fun test() {
    val node3 = Node("node3", null)
    val node2 = Node("node2", node3)
    val node1 = Node("node1", node2)
    val node0 = Node("node0", node1)
    val idx: Int = solution(node0, "node2")
    assert(idx == 2)
}

fun main() {
    test()
}