package sprint8

// https://contest.yandex.ru/contest/26133/run-report/157621831/

/**
-- ПРИНЦИП РАБОТЫ --
Я реализовал итератор PackedIterator, который выдает символы распакованной строки по одному.

ptr - текущая позиция в исходной запакованной строке.
ArrayDeque хранит пары. Каждая пара = (оставшееся Количество Повторов,
позиция Начала Блока), где позиция Начала Блока - это индекс сразу после '[',
оставшееся Количества Повторов - сколько раз осталось повторить символ в рамках текущего блока.

hasNext() - активно продвигает состояние до следующей буквы если она есть:
Пока ptr внутри строки или есть активные повторения в стеке:
    Если текущий символ буква - возвращаем true (ptr стоит на следующем символе распаковки).

    Если цифра n - начало конструкции n[подстрока]:
        Парсим количество повторов.
        Пропускаем '[' (ptr += 2).
        Кладём в стек (n, ptr) - запоминаем, сколько раз нужно повторить блок и куда возвращаться.

    Если символ ']' - завершаем одну итерацию текущего блока:
        Достаём пару из стека.
        Если осталось > 1 повторение - уменьшаем счётчик и возвращаемся на начало блока (ptr = startPtr).
        Иначе - выходим из уровня (ptr++).
    Иначе ('[') — пропускаем (он уже был обработан при встрече цифры).

next() сначала вызывает hasNext() (которая уже подготовила ptr на нужной букве),
затем возвращает packedStr[ptr++].

findCommonPrefix:
    Пока у всех итераторов есть следующий символ:
        Берём символ от первого итератора.
        Сравниваем его со следующим символом всех остальных.
        При первом расхождении сразу возвращаем собранный префикс.
        Иначе добавляем символ в результат.


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Итератор полностью моделирует рекурсивное определение запакованной строки из условия задачи:
Соответствие каждому случаю рекурсии:
    Буква - базовый случай. hasNext() возвращает true, next() выдаёт символ
    Конкатенация AB - последовательное продвижение ptr. Сначала A, потом B
    'n[A]' - стек имитирует цикл повторения подстроки A ровно n раз. Стек хранит (оставшиеся повторы блока, начало блока)
    при каждом ']' либо возвращаемся (повторяем подстроку), либо выходим из уровня если повторов больше не осталось.

hasNext() / next() поддерживают инвариант: "если hasNext() == true, то ptr указывает на следующий символ распакованной строки"
Поэтому посимвольное сравнение на каждой позиции гарантирует нахождение самого первого расхождения
это и есть самый длинный общий префикс.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
n - кол-во итераторов (запакованных строк)
k - длина наибольшего общего префикса
d - макс глубина вложенности

На каждой из k позиций:
    вызывается hasNext/next у n итераторов -> O(n) вызовов.
    Внутри одного hasNext/next в худшем случае делаем O(d) операций со стеком,

Итого: O(n * k * d)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Для каждого итератора:
    O(L) - хранение запакованной строки
    O(d) - стек
где:
    d - глубина вложенности
    L - макс длина запакованной подстроки
    n - кол-во итераторов
    k - длина наибольшего общего префикса
StringBuilder для результата O(k)

Суммарно O(n * L + n * d + k) = O(n * L)
 */

import java.util.ArrayDeque

class PackedIterator(val packedStr: String) : Iterator<Char> {
    private val stack = ArrayDeque<Pair<Int, Int>>()
    private var ptr = 0

    override fun hasNext(): Boolean {
        while (ptr < packedStr.length || stack.isNotEmpty()) {
            if (ptr >= packedStr.length) break

            val char = packedStr[ptr]

            when {
                char.isLetter() -> return true

                char.isDigit() -> {
                    val count = char.digitToInt()
                    ptr += 2
                    stack.push(count to ptr)
                }

                char == ']' -> {
                    val (count, startPtr) = stack.pop()
                    if (count > 1) {
                        stack.push((count - 1) to startPtr)
                        ptr = startPtr
                    } else {
                        ptr++
                    }
                }

                else -> ptr++
            }
        }
        return false
    }

    override fun next(): Char {
        if (!hasNext()) throw NoSuchElementException("No more characters")
        return packedStr[ptr++]
    }
}

fun findCommonPrefix(iterators: Array<PackedIterator>): String {
    return buildString {
        while (iterators.all { it.hasNext() }) {
            val ch = iterators[0].next()

            for (i in 1..iterators.lastIndex) {
                if (iterators[i].next() != ch) {
                    return@buildString
                }
            }
            append(ch)
        }
    }
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val iterators = Array(n) { PackedIterator(reader.readLine()) }

    println(findCommonPrefix(iterators))
}