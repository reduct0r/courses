package sprint5

import java.util.Collections

class Person(
    val name: String,
    val completedTasks: Int,
    val numOfFines: Int

): Comparable<Person> {

    companion object {
        private val comparator = compareBy<Person> { -it.completedTasks }
            .thenBy { it.numOfFines }
            .thenBy { it.name }
    }

    override fun compareTo(other: Person): Int = comparator.compare(this, other)
}

fun <T : Comparable<T>> heapSort(heap: MutableList<T>): MutableList<T> {
    tailrec fun siftUp(heap: MutableList<T>, index: Int) {
        if (index == 1) {
            return
        }
        val parentIndex = index / 2

        if (heap[parentIndex] > heap[index]) {
            Collections.swap(heap, parentIndex, index)
            return siftUp(heap, parentIndex)
        }
    }

    fun siftDown(){

    }
}

fun main() {
    print(Person("d", 3,4) > Person("d", 3,1))
}