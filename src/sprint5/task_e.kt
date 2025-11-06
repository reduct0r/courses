package sprint5_e

// <template>
class Node {
    var value: Int
    var left: Node?
    var right: Node?

    constructor(value: Int) {
        this.value = value
        right = null
        left = null
    }

    constructor(value: Int, left: Node?, right: Node?) {
        this.value = value
        this.left = left
        this.right = right
    }
}
// <template>

fun treeSolution(head: Node?): Boolean {
    fun isValid(node: Node?, min: Int, max: Int): Boolean {
        if (node == null) return true
        if (node.value <= min || node.value >= max) return false
        return isValid(node.left, min, node.value) && isValid(node.right, node.value, max)
    }
    return isValid(head, Int.MIN_VALUE, Int.MAX_VALUE)
}

fun test() {
    val node1 = Node(1, null, null)
    val node2 = Node(4, null, null)
    val node3 = Node(3, node1, node2)
    val node4 = Node(8, null, null)
    val node5 = Node(5, node3, node4)
    assert(treeSolution(node5))
    node2.value = 5
    assert(!treeSolution(node5))
}

fun main() {
    test()
}