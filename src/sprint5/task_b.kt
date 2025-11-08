package sprint5_b

import kotlin.math.absoluteValue
import kotlin.math.max

// <template>
class Node(var value: Int) {
    var left: Node? = null
    var right: Node? = null
}
// <template>

fun treeSolution(head: Node?): Boolean {
    fun isBalanced(head: Node?): Pair<Boolean, Int> {
        if (head == null) {
            return Pair(true, 0)
        }

        val leftIsBalanced = isBalanced(head.left)
        val leftH = leftIsBalanced.second

        val rightIsBalanced = isBalanced(head.right)
        val rightH = rightIsBalanced.second


        val balancedHere = (leftH - rightH).absoluteValue <= 1
        val heightHere = max(leftH, rightH) + 1

        val isBalancedSubtrees = leftIsBalanced.first && rightIsBalanced.first && balancedHere

        return Pair(isBalancedSubtrees, heightHere)
    }
    return isBalanced(head).first
}

fun test() {
    val node1 = Node(1)
    val node2 = Node(-5)
    val node3 = Node(3)
    node3?.left = node1
    node3?.right = node2
    val node4 = Node(10)
    val node5 = Node(2)
    node5?.left = node3
    node5?.right = node4
    assert(treeSolution(node5))
}

fun main() {
    test()
}