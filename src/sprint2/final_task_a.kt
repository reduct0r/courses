package final_task_a

// https://contest.yandex.ru/contest/22781/run-report/142305517/
/*
-- ПРИНЦИП РАБОТЫ --
    Я реализовал Дек на кольцевом буфере. В качестве буфера используется массив фиксированного размера.
    Указатели head и tail указывают на начало и конец дека. Добавление в начало происходит по указателю
    head, с его последующим сдвигом по кольцевому буферу против часовой стрелки. Добавление в конец
    происходит по указателю tail, с его последующим сдвигом по кольцевому буферу по часовой стрелке.
    Извлечение элементов также происходит по соответствующим указателям со сдвигом в противоположную
    сторону.

    Если на момент добавления дек переполнен, выводится сообщение об ошибке. Если на момент извлечения
    дек пустой выводится сообщение об ошибке. Значения указателей вычисляется по модулю capacity - размера
    массива, поэтому массив закольцован и не происходит выход за его пределы.

    Для парсинга комманд используется функция processCommands.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Из описания алгоритма следует, что Дек поддерживает FIFO для операций на концах:
    элементы, добавленные в начало, извлекаются из начала раньше, чем добавленные позже.
    Аналогично для конца.

    При пустой очереди head == tail
    После pushBack элемент добавляется в конец, tail увеличивается - порядок сохраняется
    После pushFront элемент становится новым началом, head сдвигается назад
    Pop-операции обратны.

    Поскольку все сдвиги по модулю, буфер не переполняется физически, а проверки size
    гарантируют отсутствие перезаписи или чтения при нулевом размере.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Добавление в дек стоит O(1), потому что добавление статический массив по
    известному индексу стоит O(1).
    Извлечение из дека стоит O(1), т.к. доступ к элементу массива по индексу стоит O(1).
    Кольцевой буфер имитирует бесконечный массив за счёт модуля, обеспечивая O(1)
    время на все операции без сдвигов элементов.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Если нужен дек с n элементами, то будет создан статический массив длиной n.
    Отбрасывая память на остальные поля дека, то дек будет потреблять O(n) памяти.
    Т.к. не требуется дополнительное выделение памяти то амортизационная сложность не требуется.
*/

import java.io.BufferedReader

class Deque<T>(private val capacity: Int) {
    @Suppress("UNCHECKED_CAST")                                     // Я использовал примитивный массив для своей структуры данных согласно https://kotlinlang.org/docs/arrays.html#when-to-use-arrays
    private val queue = arrayOfNulls<Any?>(capacity) as Array<T?>
    private var head = 0
    private var tail = 0
    private var size = 0

    fun pushBack(x: T) {
        if (size < capacity) {
            queue[tail] = x
            tail = (tail + 1) % capacity
            size++
        } else {
            println("error")
        }
    }

    fun pushFront(x: T) {
        if (size < capacity) {
            head = (head - 1 + capacity) % capacity
            queue[head] = x
            size++
        } else {
            println("error")
        }
    }

    fun popBack(): T? {
        if (size == 0) {
            return null
        } else {
            tail = (tail - 1 + capacity) % capacity
            val res = queue[tail]
            queue[tail] = null
            size--
            return res
        }
    }

    fun popFront(): T? {
        if (size == 0) {
            return null
        } else {
            val res = queue[head]
            queue[head] = null
            head = (head + 1) % capacity
            size--
            return res
        }
    }
}

fun processCommands(reader: BufferedReader, numOfCommands: Int, deq: Deque<Int>) {
    repeat(numOfCommands) {
        val line = reader.readLine().trim().split(" ")

        when {
            line[0] == "push_front" && line.size == 2 -> {
                deq.pushFront(line[1].toInt())
            }

            line[0] == "push_back" && line.size == 2 -> {
                deq.pushBack(line[1].toInt())
            }

            line[0] == "pop_front" && line.size == 1 -> {
                println(deq.popFront() ?: "error")
            }

            line[0] == "pop_back" && line.size == 1 -> {
                println(deq.popBack() ?: "error")
            }
            else -> println("unknown")
        }
    }
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val numOfCommands = reader.readLine().toInt()
    val size = reader.readLine().toInt()
    val deq = Deque<Int>(size)

    processCommands(reader, numOfCommands, deq)
}
