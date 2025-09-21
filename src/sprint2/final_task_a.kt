package final_task_a

// https://contest.yandex.ru/contest/22781/run-report/142766756/
/*
-- ПРИНЦИП РАБОТЫ --
    Я реализовал Дек на кольцевом буфере. В качестве буфера используется массив фиксированного размера.
    Указатель head указывает на начало дека, а tail на индекс следующий за последним элементом. Добавление
    в начало происходит по указателю head, сдвинутому по кольцевому буферу против часовой стрелки.
    Добавление в конец происходит по указателю tail, с его последующим сдвигом по кольцевому буферу по часовой стрелке.
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

    var size = 0
        private set

    private var head = 0
        set(value) {
            field = (value % capacity + capacity) % capacity
        }

    private var tail = 0
        set(value) {
            field = (value % capacity + capacity) % capacity
        }

    fun pushBack(x: T): Boolean {
        if (size < capacity) {
            queue[tail] = x
            tail += 1
            size++
            return true
        }
        return false
    }

    fun pushFront(x: T): Boolean {
        if (size < capacity) {
            head -= 1
            queue[head] = x
            size++
            return true
        }
        return false
    }

    fun popBack(): T? {
        if (size == 0) {
            return null
        } else {
            tail -= 1
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
            head += 1
            size--
            return res
        }
    }
}

fun processCommands(reader: BufferedReader, numOfCommands: Int, deq: Deque<Int>) {
    repeat(numOfCommands) {
        val line = reader.readLine().trim().split(" ")

        when {
            line[0] == "push_front" -> {
                if (!deq.pushFront(line[1].toInt())) { println("error") }
            }

            line[0] == "push_back" -> {
                if (!deq.pushBack(line[1].toInt())) { println("error") }
            }

            line[0] == "pop_front" -> {
                println(deq.popFront() ?: "error")
            }

            line[0] == "pop_back" -> {
                println(deq.popBack() ?: "error")
            }
            else -> error("unknown command")
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
