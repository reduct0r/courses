package sprint5_fa

import java.util.Collections

// https://contest.yandex.ru/contest/24810/run-report/149771321/

/**
-- ПРИНЦИП РАБОТЫ --
    Я реализовал пирамидальную сортировку (heapsort) с использованием max-heap.
    Куча строится in-place путём просеивания вниз (siftDown) начиная с середины списка, что занимает O(n) времени.
    Для сортировки последовательно меняется местами корень с последним элементом, уменьшается размер кучи и вызывается siftDown.
    Сравнение осуществляется через Comparator<Person>: приоритет - descending по completedTasks, ascending по numOfFines, ascending по name.
    В main читается ввод, создаётся список, вызывается heapSort с компаратором, выводятся имена по одному в строке через joinToString.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Построение кучи с siftDown обеспечивает порядок: родитель всегда <= потомкам по компаратору.
    При сортировке min (лучший) перемещается в конец, затем следующий min из оставшихся, и так далее.
    Поскольку компаратор определяет порядок (от лучших к худшим), финальный список будет отсортирован корректно.
    Индексация с нуля обеспечивает отсутствие ошибок в структуре и не требует фиктивных элементов.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Построение кучи: O(n).
    Сортировка: n извлечений, каждое O(log n), итого O(n log n).
    Общая сложность O(n log n) в худшем и среднем случае.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Сортировка in-place, дополнительная память O(1) (не считая входного списка).
    Рекурсивный siftDown (tailrec) не добавляет стек.
 */

class Person(
    val name: String,
    val completedTasks: Int,
    val numOfFines: Int
) {
    companion object {
        val comparator = compareByDescending<Person> { it.completedTasks }
            .thenBy { it.numOfFines }
            .thenBy { it.name }
    }
}

fun <T> MutableList<T>.heapSort(comparator: Comparator<in T>) {
    operator fun T.compareTo(other: T): Int = comparator.compare(this, other)

    tailrec fun siftDown(index: Int, heapSize: Int = this.size) {
        val left = index * 2 + 1
        val right = left + 1

        if (left >= heapSize) {
            return
        }
        var maxChildIndex = left

        if (right < heapSize && this[right] > this[left]) maxChildIndex = right

        if (this[maxChildIndex] > this[index]) {
            Collections.swap(this, maxChildIndex, index)
            return siftDown(maxChildIndex, heapSize)
        }
    }

    for (i in this.size / 2 - 1 downTo 0) {
        siftDown(i)
    }

    for (i in this.lastIndex downTo 1) {
        Collections.swap(this, 0, i)
        siftDown(0, i)
    }
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val persons = mutableListOf<Person>()
    repeat(n) {
        val (name, tasks, fines) = reader.readLine().trim().split(" ")
        persons.add(Person(name, tasks.toInt(), fines.toInt()))
    }
    persons.heapSort(Person.comparator)
    print(persons.joinToString("\n") { it.name })
}