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

fun <T> heapSort(list: MutableList<T>, comparator: Comparator<T>) {
    tailrec fun siftDown(index: Int, heapSize: Int = list.size) {
        val left = index * 2 + 1
        val right = left + 1

        if (left >= heapSize) {
            return
        }
        var maxChildIndex = left

        if (right < heapSize && comparator.compare(list[right], list[left]) > 0) maxChildIndex = right
        if (comparator.compare(list[maxChildIndex], list[index]) > 0) {
            Collections.swap(list, maxChildIndex, index)
            return siftDown(maxChildIndex, heapSize)
        }
    }

    for (i in list.size / 2 - 1 downTo 0) {
        siftDown(i)
    }

    for (i in list.lastIndex downTo 1) {
        Collections.swap(list, 0, i)
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
    heapSort(persons, Person.comparator)
    print(persons.joinToString("\n") { it.name })
}