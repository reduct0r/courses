package sprint1

fun main() {
    val n = 12
    val res = eratosthenes(n)
    res.forEach { print("$it ") }

    print("\n")

    for (i in 0..n) {
        if (res[i]) {
            print("$i ")
        }
    }

    print("\n")

    val res2 = eratosthenesEff(n)
    for (i in 0..n) {
        if (res2[i]) {
            print("$i ")
        }
    }

    print("\n")


    eratosthenesLinear(n).first.forEach { print("$it ") }
    eratosthenesLinear(n).second.forEach { print("$it ") }

}

fun eratosthenes(n: Int): MutableList<Boolean>{
    val list: MutableList<Boolean> = MutableList(n + 1) { true }
    list[0] = false
    list[1] = false

    for (num in 2..n) {
        if (list[num]) {
            for (j in 2 * num..n step num) {
                list[j] = false
            }
        }
    }
    return list
}

fun eratosthenesEff(n: Int): MutableList<Boolean>{
    val list: MutableList<Boolean> = MutableList(n + 1) { true }
    list[0] = false
    list[1] = false

    for (num in 2..n) {
        if (list[num]) {
            for (j in num * num..n step num) {
                list[j] = false
            }
        }
    }
    return list
}

fun eratosthenesLinear(n: Int): Pair<IntArray, IntArray> {
    val lp = IntArray(n + 1) { 0 }
    val primes = mutableListOf<Int>()

    for (i in 2..n) {
        if (lp[i] == 0) {       // если простое
            lp[i] = i
            primes.add(i)
        }

        for (p in primes) {
            val potential = p * i
            if (p > lp[i] || potential > n) {
                break
            }
            lp[potential] = p
        }
    }

    return primes.toIntArray() to lp
}