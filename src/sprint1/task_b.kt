package sprint1

import kotlin.math.abs

fun main() {
    val (a, b ,c) = readln().split(" ").map { it.toInt() }

    if ((abs(a % 2) == abs(b % 2)) && (abs(b % 2) == abs(c % 2))) {
        print("WIN")
    } else {
        print("FAIL")
    }
}