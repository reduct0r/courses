package sprint8

// https://contest.yandex.ru/contest/26133/run-report/157362435/

/**
-- ПРИНЦИП РАБОТЫ --
Я реализовал итератор PackedIterator, который выдает символы распакованной строки по одному.

ptr — текущая позиция в исходной запакованной строке.
Стек хранит пары (количество повторов символа, его позиция в запакованной строке).

hasNext() проверяет, существует ли следующий неслужебный символ.
    Пока не дошли до конца или стек не пуст:
    если текущий символ - буква -> true).
    если цифра n (1..9) -> парсим n, пропускаем '[', пушим в стек (n, позиция после '[').
    если ']' → pop пару, если счётчик > 1 - уменьшаем его и возвращаемся на начало подстроки (ptr = oldPtr),
        иначе -> ptr++ (выходим из текущего уровня вложенности).
    иначе (в корректной строке только '[') - пропускаем.

next() вызывает hasNext() (чтобы гарантировать, что следующий символ готов) и возвращает packedStr[ptr++].

В findCommonPrefix:
    Пока у всех итераторов есть следующий символ:
        Берём символ ch от первого итератора.
        Для каждого следующего итератора вызываем next() и сразу сравниваем с ch.
        При первом несовпадении - сразу возвращаем текущий результат.
        Если все совпали - добавляем ch в ответ StringBuilder.


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Итератор полностью моделирует рекурсивное определение запакованной строки из условия задачи:
    буква - базовый случай,
    конкатенация AB - последовательное прохождение ptr,
    n[A] - стек имитирует n-кратное повторение подстроки (по условию |n| == 1 - одна цифра - однозначное натуральное число)

Поскольку next выдает символы строго в том порядке, в каком они появились бы после полной распаковки,
позиционное сравнение символов на каждой итерации гарантирует, что мы найдём первый символ, где строки расходятся.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
n - кол-во итераторов (запакованных строк)
k - длина наибольшего общего префикса
d - глубина вложенности

На каждой из k позиций:
    вызывается hasNext/next у n итераторов -> O(n) вызовов.
    Внутри одного hasNext/next в худшем случае делаем O(d) операций со стеком,

Итого: O(n * k * d)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Для каждого итератора: O(L) + O(d) на стек.
d - глубина вложенности
L - длина запакованной подстроки
n - кол-во итераторов
k - длина наибольшего общего префикса
StringBuilder для результата O(k)

Суммарно O(∑L_i + n*d + k)
 */

import java.util.Stack

class PackedIterator(
    val packedStr: String
) {
    private val stack: Stack<Pair<Int, Int>> = Stack()
    private var ptr = 0

    fun hasNext(): Boolean {
        while (ptr < packedStr.length || stack.isNotEmpty()) {

            val char = if (ptr < packedStr.length) packedStr[ptr] else break

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

    fun next(): Char {
        if (!hasNext()) throw NoSuchElementException()
        return packedStr[ptr++]
    }
}

fun findCommonPrefix(iterators: Array<PackedIterator>): String {
    val result = StringBuilder()

    while (iterators.all { it.hasNext() }) {
        val ch = iterators[0].next()

        for (i in 1 until iterators.size) {
            if (iterators[i].next() != ch) {
                return result.toString()
            }
        }

        result.append(ch)
    }
    return result.toString()
}

fun main(){
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val iterators = Array(n) {
        PackedIterator(reader.readLine())
    }

    println(findCommonPrefix(iterators))
}