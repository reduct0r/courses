package sprint5_fb

// <template>
class Node(var left: Node?, var right: Node?, var value: Int)
// <template>


fun remove(root: Node?, key: Int): Node? {

    tailrec fun findMaxParent(node: Node?, parent: Node?): Node? {
        if (node == null) return null
        if (node.right == null) return parent
        return findMaxParent(node.right, node)
    }

    fun delete(node: Node?, key: Int, parent: Node?): Node? {
        if (node == null) return null

        if (node.value == key) {
            val pParent = findMaxParent(node.left, node)
            var p = pParent?.right
            if (pParent == node) p = node.left

            if (p == null) {
                if (parent == null) return node.right
                if (parent.left == node) parent.left = node.right
                else parent.right = node.right
                return node.right
            }

            pParent!!.right = p.left

            p.left = node.left
            p.right = node.right

            if (parent == null) return p
            if (parent.left == node) parent.left = p
            else parent.right = p

            return p
        }

        if (key < node.value) {
            node.left = delete(node.left, key, node)
        } else {
            node.right = delete(node.right, key, node)
        }

        return node
    }

    return delete(root, key, null)
}
