package pg.yandexPracticumInterview

import java.util.StringTokenizer

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val k = reader.readLine().toInt()
    val st = StringTokenizer(reader.readLine())
    val nums = IntArray(n) { st.nextToken().toInt() }

    print(findMaxSum(nums, k))
}

fun findMaxSum(nums: IntArray, k: Int): Int {
    val n = nums.size
    if (k >= n) return nums.sum()
    var sum = 0


    for (i in 0 until k) {
        sum += nums[i]
    }

    var maxSum = sum

    for (i in 1 .. k){
        sum = sum - nums[k - i] + nums[n - i]
        maxSum = maxOf(maxSum, sum)
    }

    return maxSum
}