package t_algo.sprint1

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val n = reader.readLine()?.toInt() ?: return

    var low = 1
    var high = n
    var ans = 1

    while (low <= high) {
        val mid = (low + high + 1) / 2

        println(mid)

        val response = reader.readLine()

        if (response == ">=") {
            ans = mid
            low = mid + 1
        } else {
            high = mid - 1
        }
    }

    println("! $ans")
}
