package sprint4
// https://contest.yandex.ru/contest/24414/run-report/144690030/

/*
-- ПРИНЦИП РАБОТЫ --
    Я реализовал хеш-таблицу с методом цепочек для разрешения коллизий, используя массив бакетов фиксированного размера (100003 - простое число для распределения).
    Хеш-функция вычисляет индекс как (key % size + size) % size для обработки отрицательных ключей.
    Каждый бакет - MutableList<Pair<Int, Int>>, где Pair хранит ключ и значение.
    Для put: вычисляется хеш, если бакет пуст - создается список, ищется ключ в цепочке, если найден - обновляется значение, иначе добавляется новая пара.
    Для get: вычисляется хеш, если бакет пуст - None, иначе линейный поиск по цепочке, возврат значения или None.
    Для delete: аналогично get, но при нахождении удаляется элемент по индексу и возвращается значение, иначе None.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Хеш-функция всегда дает индекс в [0, size-1], корректно для отрицательных ключей.
    Метод цепочек гарантирует хранение всех ключей даже при коллизиях, линейный поиск в цепочке находит точный ключ.
    Put обновляет существующий или добавляет новый, сохраняя уникальность.
    Get и delete возвращают None при отсутствии (пустой бакет или не найден в цепочке).

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Хеш-вычисление O(1).
    Каждая операция (put/get/delete): O(1) в среднем - проход по цепочке длины ~1.
    В худшем O(ключи в одном бакете) = O(10^5).
    O(n) в среднем, рехеширование не требуется.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Массив table O(size) = O(10^5), каждый бакет - список O(длина),
    всего O(число ключей) <= O(10^5).
*/

data class Pair(val key: Int, var value: Int)

class HashTable(private val size: Int = 100003) {
    private val table: Array<MutableList<Pair>?> = arrayOfNulls(size)

    private fun hash(key: Int): Int {
        return ((key.toLong() % size + size) % size).toInt()
    }

    fun put(key: Int, value: Int) {
        val h = hash(key)
        if (table[h] == null) {
            table[h] = mutableListOf()
        }
        val bucket = table[h]!!

        for (pair in bucket) {
            if (pair.key == key) {
                pair.value = value
                return
            }
        }
        bucket.add(Pair(key, value))
    }

    fun get(key: Int): Int? {
        val h = hash(key)
        val bucket = table[h] ?: return null
        for (entry in bucket) {
            if (entry.key == key) {
                return entry.value
            }
        }
        return null
    }

    fun delete(key: Int): Int? {
        val h = hash(key)
        val bucket = table[h] ?: return null
        for (i in bucket.indices) {
            if (bucket[i].key == key) {
                val value = bucket[i].value
                bucket.removeAt(i)
                return value
            }
        }
        return null
    }
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().trim().toInt()
    val hashTable = HashTable()

    repeat(n) {
        val line = reader.readLine().trim().split(" ")
        when (line[0]) {
            "get" -> {
                val key = line[1].toInt()
                val result = hashTable.get(key)
                println(result ?: "None")
            }
            "put" -> {
                val key = line[1].toInt()
                val value = line[2].toInt()
                hashTable.put(key, value)
            }
            "delete" -> {
                val key = line[1].toInt()
                val result = hashTable.delete(key)
                println(result ?: "None")
            }
        }
    }
}
