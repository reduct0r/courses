// https://contest.yandex.ru/contest/23815/run-report/144486195/

/*
-- ПРИНЦИП РАБОТЫ --
    Я реализовал in-place быструю сортировку.
    Алгоритм выбирает случайный опорный элемент (pivot) в интервале [left..right] для избежания худшего случая.
    Два указателя i и j двигаются навстречу: leftPtr вправо, пока элемент < pivot, rightPtr влево, пока элемент > pivot.
    Когда указатели на элементах (i >= pivot, j <= pivot), они свапаются, и указатели сдвигаются.
    Процесс повторяется, пока указатели не пересекутся. Затем рекурсивно сортируются левая [i..part-1] и правая [part..j] части.
    Сравнение элементов через функцию compareParticipants: больше решенных задач - выше, при равенстве: меньше штрафов - выше,
    при равенстве: по имени лексикографически раньше - выше.
    В main читается ввод, заполняется массив, сортируется, выводятся имена.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Из описания алгоритма следует, что partitioning делит массив так, что слева от возвращаемого part все элементы <= pivot по критериям сравнения, справа >= pivot.
    Рекурсия на меньших подмассивах сужает интервалы, и базовый случай (left >= right) останавливает рекурсию.
    Поскольку каждый элемент в итоге окажется в одинарном подмассиве, и partitioning сохраняет относительный порядок относительно pivot, весь массив отсортируется по критериям сравнения.
    Рандомный pivot обеспечивает баланс в среднем, уменьшая вероятность O(n^2).

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Partitioning стоит O(n) в среднем (каждый элемент посещается указателями O(1) раз).
    Рекурсия делит на два подмассива, глубина O(log n) в среднем, т.е. общая O(n log n).
    В худшем O(n^2), но рандомный pivot делает это маловероятным.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Алгоритм in-place: меняет массив на месте, без дополнительных массивов O(n).
    Дополнительная память O(log n) на стек рекурсии в среднем, O(n) в худшем.
    Нет амортизации, так как нет динамического выделения.
*/

class Participant(
    val name: String,
    val completedTasks: Int,
    val numOfFines: Int
)

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val listOfParticipants = Array(n) {
        val (name, tasks, fines) = reader.readLine().trim().split(" ")
        Participant(name, tasks.toInt(), fines.toInt())
    }
    val participantComparator = compareByDescending<Participant> { it.completedTasks }
        .thenBy { it.numOfFines }
        .thenBy { it.name }

    listOfParticipants.quickSort(participantComparator)

    println(listOfParticipants.joinToString("\n") { it.name })
}

private fun Array<Participant>.partition(left: Int, right: Int, comparator: Comparator<Participant>): Int {
    val pivot = this[(left..right).random()]
    var i = left
    var j = right
    while (i < j) {
        while (i <= j && comparator.compare(this[i], pivot) < 0) {
            i++
        }
        while (i <= j && comparator.compare(this[j], pivot) > 0) {
            j--
        }
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
    return i
}

fun Array<Participant>.quickSort(comparator: Comparator<Participant>) {
    if (size <= 1) return
    quickSort(0, lastIndex, comparator)
}

private fun Array<Participant>.quickSort(left: Int, right: Int, comparator: Comparator<Participant>) {
    if (left >= right) return
    val part = partition(left, right, comparator)
    quickSort(left, part - 1, comparator)
    quickSort(part, right, comparator)
}
