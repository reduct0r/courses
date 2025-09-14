package sdf
// <template>
class Node<V>(
    var value: V,
    var next: Node<V>?
) {}
// <template>

fun solution(head: Node<String>?, pos: Int): Node<String>? {
    var curr = head
    var prev = curr

    if (pos == 0) {
        return head?.next
    }

    for (i in 0 until pos) {
        if (curr != null) {
            if (i == pos - 1) {
                prev = curr
            }
            curr = curr.next
        }
    }

    prev?.next = curr?.next
    return head

}

fun test() {
    val node3 = Node("node3", null)
    val node2 = Node("node2", node3)
    val node1 = Node("node1", node2)
    val node0 = Node("node0", node1)

    val newHead = solution(node0, 1)

    assert(newHead === node0)
    assert(newHead?.next === node2)
    assert(newHead?.next?.next === node3)
    assert(newHead?.next?.next?.next == null)
    // result is : node0 -> node2 -> node3

    println(printList(newHead))
}

fun printList(head: Node<String>?): String {
    var curr: Node<String>? = head
    val ans = StringBuilder()
    ans.append("null <- ")
    while (curr != null) {
        ans.append(curr.value.toString())
        ans.append(" <- ")
        curr = curr.next
    }
    return ans.toString()
}

fun main() {
    test()
}