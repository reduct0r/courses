package sprint1

import java.util.Stack
import java.util.StringTokenizer

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val strArr = reader.readLine().toString()
    val k = reader.readLine().toInt()
    val tokenizer = StringTokenizer(strArr)
    val stack = Stack<Int>()

    repeat(n){
        stack.push(tokenizer.nextToken().toInt())
    }

    print(sum(stack, k))
}

fun sum(x: Stack<Int>, b: Int): String {
    var k = b
    var buf = 0
    val builder = StringBuilder()

    while (!x.isEmpty() || k > 0) {
        val last = if (!x.isEmpty()) x.pop() else 0
        val sum = last + k % 10 + buf
        builder.append("${(sum % 10)} ")
        buf = sum / 10
        k /= 10
    }
    if (buf > 0) {
        builder.append(buf.toString())
    }

    return builder.reverse().toString().trim()
}