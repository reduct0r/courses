package sprint5_m

fun siftUp(heap: IntArray, idx: Int): Int {
    // Вышли на вершину
    if (idx == 1) {
        return 1
    }

    val parentIdx = idx / 2

    // Нашли родителя больше себя
    if (heap[parentIdx] >= heap[idx]) {
        return idx
    } else { // Родитель меньше - перестановка
        val temp = heap[idx]
        heap[idx] = heap[parentIdx]
        heap[parentIdx] = temp
        return siftUp(heap, parentIdx)
    }
}

fun test() {
    val sample = intArrayOf(-1, 12, 6, 8, 3, 15, 7)
    assert(siftUp(sample, 5) == 1)
}

fun main() {
    test()
}