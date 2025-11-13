package sprint5_fa

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

fun <T : Comparable<T>> MutableList<T>.heapSort(): MutableList<T> {
    val sortedList = mutableListOf<T>()

    while (this.isNotEmpty()) {
        val max = popMin()
        sortedList.add(max)
    }
    return sortedList
}

tailrec fun <T : Comparable<T>> MutableList<T>.siftDown(index: Int){
    val left = index * 2 + 1
    val right = left + 1

    if (left >= this.size) {
        return
    }
    val minChildIndex = if (right < this.size && this[left] > this[right]) { right } else { left }

    if (this[minChildIndex] < this[index]) {
        Collections.swap(this, minChildIndex, index)
        return siftDown(minChildIndex)
    }
}

fun <T : Comparable<T>> MutableList<T>.popMin(): T {
    val result = this[0]
    this[0] = this[this.size - 1]
    this.removeAt(this.size - 1)
    siftDown(0)
    return result
}

tailrec fun <T : Comparable<T>> MutableList<T>.siftUp(index: Int) {
    if (index == 0) {
        return
    }
    val parentIndex = (index - 1) / 2

    if (this[parentIndex] > this[index]) {
        Collections.swap(this, parentIndex, index)
        return siftUp(parentIndex)
    }
}

fun <T : Comparable<T>> MutableList<T>.heapAdd(key: T) {
    this.add(key)
    siftUp(this.size - 1)
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val heap = mutableListOf<Person>()

    repeat(n) {
        val (name, tasks, fines) = reader.readLine().trim().split(" ")
        heap.heapAdd(Person(name, tasks.toInt(), fines.toInt()))
    }

    val sortedList = heap.heapSort()
    val result = buildString {
        sortedList.forEach {
            appendLine(it.name)
        }
    }

    print(result)
}