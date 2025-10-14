package sprint4

// https://contest.yandex.ru/contest/24414/run-report/145138885/

/*
-- ПРИНЦИП РАБОТЫ --
    Я реализовал хеш-таблицу с методом цепочек для разрешения коллизий, используя массив
    бакетов фиксированного размера (142867 - простое число для распределения).
    Хеш-функция вычисляет индекс как key mod capacity для обработки отрицательных
    ключей.
    Каждый бакет - LinkedList<Entry>, где Entry хранит ключ и значение.
    Для put: вычисляется хеш, ищется ключ в цепочке циклом, если найден - обновляется
    значение, иначе добавляется новая пара.
    Для get: вычисляется хеш, если бакет пуст - None, иначе поиск циклом по цепочке,
    возврат значения или None.
    Для delete: аналогично get, но при нахождении удаляется элемент с помощью
    итератора и возвращается значение, иначе None.
    Вывод собирается в StringBuilder.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Хеш-функция всегда дает индекс в [0, capacity-1], корректно для отрицательных
    ключей.
    Метод цепочек гарантирует хранение всех ключей даже при коллизиях, поиск циклом
    находит точный ключ.
    Put обновляет существующий или добавляет новый, сохраняя уникальность.
    Get и delete возвращают None при отсутствии (пустой бакет или не найден в
    цепочке).

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Хеш-вычисление O(1).
    Каждая операция (put/get/delete): O(1) в среднем - проход по цепочке длины ~1.
    В худшем O(ключи в одном бакете) = O(10^5).
    O(n) в среднем, рехеширование не требуется.
    StringBuilder ускоряет вывод для n=10^6.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Массив table O(capacity) = O(1.4*10^5), каждый бакет - список O(длина),
    всего O(число ключей) <= O(10^5).
*/

import java.util.LinkedList

data class Entry(val key: Int, var value: Int)

class HashTable(private val capacity: Int = 142867) {
    private val table: Array<LinkedList<Entry>> = Array(capacity) { LinkedList() }

    private fun hash(key: Int): Int {
        return key.mod(capacity)
    }

    fun put(key: Int, value: Int) {
        val h = hash(key)
        val bucket = table[h]
        for (entry in bucket) {
            if (entry.key == key) {
                entry.value = value
                return
            }
        }
        bucket.add(Entry(key, value))
    }

    operator fun get(key: Int): Int? {
        val h = hash(key)
        val bucket = table[h]
        for (entry in bucket) {
            if (entry.key == key) {
                return entry.value
            }
        }
        return null
    }

    fun delete(key: Int): Int? {
        val h = hash(key)
        val bucket = table[h]
        val iter = bucket.listIterator()
        while (iter.hasNext()) {
            val entry = iter.next()
            if (entry.key == key) {
                iter.remove()
                return entry.value
            }
        }
        return null
    }

    operator fun set(key: Int, value: Int) {
        put(key, value)
    }
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().trim().toInt()
    val hashTable = HashTable()
    val sb = StringBuilder()

    repeat(n) {
        val line = reader.readLine().trim().split(" ")
        when (line[0]) {
            "get" -> {
                val key = line[1].toInt()
                val result = hashTable[key]
                sb.append(result ?: "None").append("\n")
            }
            "put" -> {
                val key = line[1].toInt()
                val value = line[2].toInt()
                hashTable[key] = value
            }
            "delete" -> {
                val key = line[1].toInt()
                val result = hashTable.delete(key)
                sb.append(result ?: "None").append("\n")
            }
        }
    }
    print(sb)
}
