package sprint5_fb

// https://contest.yandex.ru/contest/24810/run-report/147766350/

/*
-- ПРИНЦИП РАБОТЫ --
    Я реализовал удаление узла из двоичного дерева поиска (BST) с использованием самого правого узла в левом поддереве.
    Рекурсивно находим узел D для удаления, передавая его parent.
    Если D найден:
        Находим преемника P и его родителя pParent в левом поддереве.
        Если P = null (нет left), заменяем D на D.right.
        Иначе удаляем P из старого места: pParent.right = P.left.
        Перемещаем P на место D: P.left = D.left, P.right = D.right.
        Обновляем parent.child = P (проверяем left/right).
        Для корня возвращаем P или D.right.
    Возвращает обновлённый подкорень.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Поиск следует BST-свойствам: влево если key < node, вправо иначе - находит искомый D или null.
    Для 0/1 ребёнка замена сохраняет BST (left < parent < right).
    Для 2 детей P - max в left, замена P на D сохраняет инвариант (все left < P < right).
    Удаление P (как листа или с 1 ребёнком) не нарушает, т.к. P.left заменяет P.
    Нет циклов, т.к. P.left = D.left только после удаления P.
    Удаляет первый найденный узел.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Поиск D и P: O(h), h - высота дерева (O(log n) в сбалансированном, O(n) в худшем).
    Каждая операция - O(h) (спуск + константы).
    Для m операций - O(m * h).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Рекурсия O(h) стека (в худшем O(n)).
    Дерево O(n) узлов.
*/

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

            val isLeftChild = pParent!!.left == p
            if (isLeftChild) {
                pParent.left = p.left
            } else {
                pParent.right = p.left
            }

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