package sprint1

import java.util.StringTokenizer

fun main() {
    val reader = System.`in`.bufferedReader()   // считыватель с буфером
    val builder = StringBuilder()               // построитель строки без создания новых при изменении
    val size = reader.readLine().toInt()        // считали 1ю строку с размером массива
    val str = reader.readLine()                 // считали строку со строкой в виде массива с числами через пробел
    val tokenizer = StringTokenizer(str)        // токенайзер читает текст пока не встретит разделитель

    repeat(size) {
        builder.append(tokenizer.nextToken()).append(" ")
    }

    println(builder.toString())
}