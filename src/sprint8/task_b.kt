package sprint8

import kotlin.math.absoluteValue

fun main() {
    val reader = System.`in`.bufferedReader()
    val str1 = reader.readLine()
    val str2 = reader.readLine()

    print(processHuman(str1, str2))
}

fun processHuman(str1: String, str2: String): String {
    if ((str1.length - str2.length).absoluteValue > 1) return "FAIL"

    var missCount = 0
    var ptr1 = 0
    var ptr2 = 0

    while (ptr1 < str1.length - 1 && ptr2 < str2.length - 1) {
        if (str1[ptr1] == str2[ptr2]) {
            ptr1++
            ptr2++
        } else {
            missCount++
            when {
                str1.length > str2.length -> ptr1++
                str1.length < str2.length -> ptr2++
                else -> {
                    ptr1++; ptr2++
                }
            }
        }

    }

    return if (missCount < 2) {
        "OK"
    } else {
        "FAIL"
    }
}
