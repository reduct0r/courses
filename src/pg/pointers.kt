package pg

class ListNode (
    val `val`: Int,
    var next: ListNode? = null
)


fun solve(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null && l2 == null) return null
    if (l1 == null) return l2
    if (l2 == null) return l1

    var p1 = l1
    var p2 = l2

    val head = ListNode(-1)
    var ptr = head
    var shift = 0

    while (p1 != null || p2 != null || shift != 0) {
        val v1 = p1?.`val` ?: 0
        val v2 = p2?.`val` ?: 0

        val sum = v1 + v2 + shift

        ptr.next = ListNode(sum % 10)
        shift = sum / 10

        ptr = ptr.next!!

        p1 = p1?.next
        p2 = p2?.next
    }

    return head.next
}


fun main() {
    // Создаем два списка: [5, 8] и [5, 2]
    val list1 = createList(intArrayOf(5, 8))
    val list2 = createList(intArrayOf(5, 2))

    println("Список 1: ${listToString(list1)}")
    println("Список 2: ${listToString(list2)}")

    val result = solve(list1, list2)

    println("---")
    println("Результат: ${listToString(result)}")
}

// Вспомогательная функция для создания списка из массива
fun createList(nums: IntArray): ListNode? {
    val dummy = ListNode(0)
    var current = dummy
    for (num in nums) {
        current.next = ListNode(num)
        current = current.next!!
    }
    return dummy.next
}

// Вспомогательная функция для визуализации списка
fun listToString(node: ListNode?): String {
    val sb = StringBuilder()
    var current = node
    while (current != null) {
        sb.append(current.`val`)
        if (current.next != null) sb.append(" -> ")
        current = current.next
    }
    return if (sb.isEmpty()) "null" else sb.toString()
}


