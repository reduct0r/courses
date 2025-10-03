package sprint3

// https://contest.yandex.ru/contest/23815/run-report/143775664/
/*
-- ПРИНЦИП РАБОТЫ --
    Я реализовал in-place быструю сортировку.
    Алгоритм выбирает случайный опорный элемент (pivot) в интервале [left..right] для избежания худшего случая.
    Два указателя leftPtr и rightPtr двигаются навстречу: leftPtr вправо, пока элемент < pivot, rightPtr влево, пока элемент > pivot.
    Когда указатели на элементах (leftPtr >= pivot, rightPtr <= pivot), они свапаюстя, и указатели сдвигаются.
    Процесс повторяется, пока указатели не пересекутся. Затем рекурсивно сортируются левая [left..part-1] и правая [part..right] части.
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
    val name: String = "",
    val completedTasks: Int = 0,
    val numOfFines: Int = 0
)

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val listOfParticipants = Array(n) { Participant() }

    for (i in 0 until n) {
        val parts = reader.readLine().trim().split(" ")
        listOfParticipants[i] = Participant(parts[0], parts[1].toInt(), parts[2].toInt())
    }

    inPlaceQSort(listOfParticipants, 0, n - 1)

    for (participant in listOfParticipants) {
        println(participant.name)
    }
}

fun compareParticipants(a: Participant, b: Participant): Int {
    return when {
        a.completedTasks != b.completedTasks -> b.completedTasks.compareTo(a.completedTasks)
        a.numOfFines != b.numOfFines -> a.numOfFines.compareTo(b.numOfFines)
        else -> a.name.compareTo(b.name)
    }
}

fun partition(arr: Array<Participant>, left: Int, right: Int): Int {
    val pivotValue = arr[(left..right).random()]
    var leftPtr = left
    var rightPtr = right

    while (leftPtr <= rightPtr) {
        while (leftPtr <= rightPtr && compareParticipants(arr[leftPtr], pivotValue) < 0) {
            leftPtr++
        }

        while (leftPtr <= rightPtr && compareParticipants(arr[rightPtr], pivotValue) > 0) {
            rightPtr--
        }

        if (leftPtr <= rightPtr) {
            val temp = arr[leftPtr]
            arr[leftPtr] = arr[rightPtr]
            arr[rightPtr] = temp
            leftPtr++
            rightPtr--
        }
    }

    return leftPtr
}

fun inPlaceQSort(arr: Array<Participant>, left: Int, right: Int): Array<Participant> {
    if (left < right) {
        val part = partition(arr, left, right)
        inPlaceQSort(arr, left, part - 1)
        inPlaceQSort(arr, part, right)
    }
    return arr
}
