package sprint1

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().trim().toInt()

    print(factor(n))
}

fun factor(n: Int): String {
    val primes = mutableListOf<Int>()
    var num = n

    while (num % 2 == 0) {
        primes.add(2)
        num /= 2
    }

    var i = 3
    while (i * i <= num) {
        while (num % i == 0) {
            primes.add(i)
            num /= i
        }
        i += 2
    }

    if (num > 1) {
        primes.add(num)
    }


    return primes.joinToString(" ")
}