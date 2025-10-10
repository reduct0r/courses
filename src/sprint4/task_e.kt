package sprint4

import kotlin.random.Random

fun main() {
    val a = 1000
    val m = 123987123L
    val hashToString = mutableMapOf<Long, String>()
    var attempts = 0

    while (true) {
        attempts++
        val length = Random.nextInt(5, 16)
        val s = (1..length).map { Random.nextInt(97, 123).toChar() }.joinToString("")
        val h = polynomialHash(a, m, s)

        if (hashToString.containsKey(h)) {
            val existing = hashToString[h]!!
            if (s != existing) {
                println("Коллизия найдена после $attempts попыток:")
                println("Строка 1: $existing (хэш: $h)")
                println("Строка 2: $s (хэш: $h)")
                break
            }
        } else {
            hashToString[h] = s
        }
    }
}

