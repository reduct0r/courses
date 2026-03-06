package sprint5_fb

// https://contest.yandex.ru/contest/24810/run-report/150229737/

/**
-- ПРИНЦИП РАБОТЫ --
    Я реализовал удаление узла из двоичного дерева поиска (BST) с использованием самого правого узла в левом поддереве.
    Рекурсивно ищем узел D для удаления: если key < node.value, идем в левое поддерево (node.left = remove(node.left, key));
    если key > node.value, идем в правое (node.right = remove(node.right, key));
    если node == null, возвращаем null. Это следует BST-свойствам: левое поддерево < узел < правое.
    Если D найден (node.value == key):
        Если нет детей, возвращаем null (удаляем узел).
        Если один ребёнок, возвращаем этого ребёнка (заменяем D на него).
        Если два ребёнка: находим преемника P (максимум в левом поддереве) рекурсивно - спускаемся вправо от node.left, пока right != null.
        Копируем P.value в node.value (заменяем значение D на P), затем рекурсивно удаляем P из левого поддерева (node.left = remove(node.left, P.value)).
    Функция возвращает обновлённый корень поддерева (для рекурсивного присвоения в родителе).
-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Поиск сохраняет BST: спуск влево/вправо по сравнению с value гарантирует нахождение первого узла с key или null, без нарушения порядка.
    Для удаления без детей: поддерево становится null, BST сохраняется (нет элементов для нарушения).
    С одним ребёнком: замена на ребёнка сохраняет инвариант (ребёнок уже < или > родителя D по BST).
    С двумя: P - max в left, так что left.min <= все left < D < все right; копирование P.value в node сохраняет (все left <= P.value < все right);
    рекурсивное удаление P (лист или один ребёнок) не нарушает, т.к. использует те же правила. Нет циклов, т.к. только копируем value, не перемещаем узлы.
    Удаляет первый найденный узел, рекурсия обеспечивает обновление ссылок вверх.
-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Поиск D и P: O(h), где h - высота дерева (O(log n) в сбалансированном, O(n) в худшем).
    Каждая операция remove - O(h) (спуск + константы + рекурсивный вызов для P).
    Если несколько вызовов remove (m раз), общая - O(m * h).
-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Рекурсия O(h) стека (в худшем O(n) для вырожденного дерева).
    Дерево занимает O(n) узлов.
*/

// <template>
class Node(var left: Node?, var right: Node?, var value: Int)
// <template>

fun remove(root: Node?, key: Int): Node? {
    tailrec fun findMax(node: Node): Node {
        if (node.right == null) return node
        return findMax(node.right!!)
    }
    when {
        root == null -> return null
        key < root.value -> root.left = remove(root.left, key)
        key > root.value -> root.right = remove(root.right, key)
        else -> {
            if (root.left == null) return root.right
            if (root.right == null) return root.left
            val maxInLeft = findMax(root.left!!)
            root.value = maxInLeft.value
            root.left = remove(root.left, maxInLeft.value)
        }
    }
    return root
}