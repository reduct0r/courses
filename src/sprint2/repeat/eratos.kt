package sprint2.repeat

fun main() {
    val n = readln().toInt()

    println(primeNumbers(n).joinToString(" "))

}

fun primeNumbers(n: Int): List<Int> {
    val primes = mutableListOf<Int>()
    val minDivisors = IntArray(n + 1) { 0 }

    for (i in 2.. n) {
        if (minDivisors[i] == 0) {
            primes.add(i)
            minDivisors[i] = i
        }

        for (primeEl in primes) {
            val comp = primeEl * i
            if (primeEl > minDivisors[i] || comp > n) {
                break
            }
            minDivisors[comp] = i
        }
    }

    return primes
}