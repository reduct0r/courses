package task_e

// <template>
class Node<V>(var value: V) {
    var next: Node<V>? = null
    var prev: Node<V>? = null

    fun hasNext(): Boolean {
        return next != null
    }
}
// <template>


fun solution(head: Node<String>?): Node<String>? {
    if (head == null)
        return null

    var curr = head
    var newHead: Node<String>? = null

    while (curr != null) {
        val next = curr.next
        curr.next = curr.prev
        curr.prev = next
        newHead = curr
        curr = next
    }

    return newHead
}


fun test() {
    val node3 = Node("node3")
    val node2 = Node("node2")
    val node1 = Node("node1")
    val node0 = Node("node0")

    node0.next = node1
    node1.next = node2
    node2.next = node3

    node1.prev = node0
    node2.prev = node1
    node3.prev = node2

    val newNode = solution(node0)
    assert(newNode === node3)
    assert(node3?.next === node2)
    assert(node2?.next === node1)
    assert(node2?.prev === node3)
    assert(node1?.next === node0)
    assert(node1?.prev === node2)
    assert(node0?.prev === node1)
}

fun main() {
    test()
}