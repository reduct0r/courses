package sprint8

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
                    ptr += 2 // Пропускаем 'n' и '['
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
    var allSame = true

    while (iterators.all { it.hasNext() }) {
        val firstChar = iterators[0].next()

        for (i in 1 until iterators.size) {
            val nextChar = iterators[i].next()
            if (nextChar != firstChar) {
                allSame = false
                break
            }
        }

        if (allSame) {
            result.append(firstChar)
        } else {
            break
        }
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