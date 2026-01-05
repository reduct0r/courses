package sprint7
// https://contest.yandex.ru/contest/25597/problems/B/#2989/2020_04_29/zDij5o2YCE

/**
-- ПРИНЦИП РАБОТЫ --
    Задача сводится к задаче о рюкзаке.
    Если общая сумма нечётная - разбиение невозможно.
    Если чётная, то target = totalSum / 2.
    Используем одномерный массив динамического программирования:
    dp[j] хранит максимальную достижимую сумму очков, не превосходящую j.
    Поскольку стоимость предмета равна его весу, если в конце dp[target] == target, значит,
    существует подмножество с точной суммой target.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    На каждом шаге dp[j] содержит максимальную достижимую сумму <= j
    с учётом уже обработанных элементов.
    Обратный порядок гарантирует, что каждый элемент берётся не более одного раза.
    Если общая сумма чётная и dp[target] == target, то одно подмножество имеет сумму target,
    второе - остаток, тоже равный target.
    Пустой массив (n=0) даёт сумму 0, которую можно разбить на две пустые части.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    O(n * target), где n ≤ 300, target ≤ 300 * 300 / 2 = 45000.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Одномерный массив dp размера target + 1
    O(target) ≤ O(45000).
    Дополнительная память O(1).
 */

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val line = reader.readLine()
    if (n == 0) {
        println("True")
        return
    }
    val points = line.split(" ").map { it.toShort() }.toShortArray()

    println(if (canPartition(points)) "True" else "False")
}

fun canPartition(points: ShortArray): Boolean {
    if (points.isEmpty()) return true

    val totalSum = points.sum()
    if (totalSum % 2 != 0) return false

    val target = totalSum / 2

    // dp[j] максимальная достижимая сумма <= j
    val dp = IntArray(target + 1) { 0 }

    for (point in points) {
        val value = point.toInt()
        for (j in target downTo value) {
            dp[j] = maxOf(dp[j], value + dp[j - value])
        }
    }

    return dp[target] == target
}