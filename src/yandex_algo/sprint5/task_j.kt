package sprint5_j

// <template>
class Node(var left: Node?, var right: Node?, var value: Int)
// <template>

fun insert(root: Node, key: Int): Node {

    fun inSearch(root: Node?, key: Int): Node {
        if (root == null) return Node(null, null, key)
        if (key < root.value) {
            root.left = inSearch(root.left, key)
        } else {
            root.right = inSearch(root.right, key)
        }
        return root
    }

    return inSearch(root, key)
}

fun test() {
    val node1 = Node(null, null, 7)
    val node2 = Node(node1, null, 8)
    val node3 = Node(null, node2, 7)
    val newHead = insert(node3, 6)
    assert(newHead == node3)
    assert(newHead?.left!!.value == 6)
}

fun main() {
    test()
}