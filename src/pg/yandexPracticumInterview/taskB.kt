package pg.yandexPracticumInterview

import java.util.StringTokenizer

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val st = StringTokenizer(reader.readLine())
    val nums = IntArray(n) { st.nextToken().toInt() }

    print(findMod(nums))
}

fun findMod(nums: IntArray): Int {
    val n = nums.size
    val table = IntArray(n * n) { 0 }

    for (i in 0 until n) {
        for (j in 0 until n) {
            table[i * j + i] = nums[i]
        }
    }

    return 0

}
