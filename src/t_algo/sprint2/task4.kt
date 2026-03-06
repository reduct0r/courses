package t_algo.sprint2

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val seq = reader.readLine().split(" ").map { it.toInt() }

    print(countDestroyedBalls(seq))
}

fun countDestroyedBalls(seq: List<Int>): Int {
    val deque = ArrayDeque<Pair<Int, Int>>()
    var counter = 0

    for (ball in seq) {
        when {
            deque.isNotEmpty() && deque.last().first != ball -> {
                if (deque.last().second >= 3) {
                    counter += deque.removeLast().second
                }
                if (deque.isNotEmpty() && deque.last().first == ball) {
                    val count = deque.removeLast().second
                    deque.add(ball to count + 1)
                } else {
                    deque.add(ball to 1)
                }
            }

            deque.isNotEmpty() && deque.last().first == ball -> {
                val count = deque.removeLast().second
                deque.add(ball to count + 1)
            }

            else -> {
                deque.add(ball to 1)
            }
        }
    }

    if (deque.last().second >= 3) {
        counter += deque.removeLast().second
    }

    return counter
}
