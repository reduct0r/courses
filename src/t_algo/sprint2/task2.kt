package t_algo.sprint2

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, k) = reader.readLine().split(" ").map { it.toInt() }
    val seq = reader.readLine().split(" ").map { it.toInt() }

    println(findWindowMin(k, seq).joinToString(" "))
}

fun findWindowMin(k: Int, seq: List<Int>): MutableList<Int> {
    val deq = ArrayDeque<Int>()
    val minList = mutableListOf<Int>()

    for (i in 0 .. seq.lastIndex) {
        when {
            deq.isEmpty() || seq[deq.last()] <= seq[i] -> {
                deq.add(i)
            }
            seq[deq.last()] > seq[i] -> {
                while (deq.isNotEmpty() && seq[deq.last()] > seq[i]) {
                    deq.removeLast()
                }
                deq.add(i)
            }
        }

        while (deq.first() <= i - k) {
            deq.removeFirst()
        }

        if (i >= k - 1) {
            minList.add(seq[deq.first()])
        }
    }

    return minList
}
