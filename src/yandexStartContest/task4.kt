package yandexStartContest

fun main() {
    val num = readln().toInt()
    val arr = readln().split(" ").map{it.toInt()}
    val goal = readln().toInt()

    val seen = mutableSetOf<Int>()

    for (x in arr) {
        if (goal - x in seen) {
            println("${goal - x} $x")
            return
        }
        seen.add(x)
    }
    print("None")
}