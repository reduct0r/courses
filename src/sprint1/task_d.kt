package sprint1

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }

    var counter = 0

    for (i in arr.indices) {
        if (n == 1) {
            counter++
            break
        }
        if (i == 0 && arr[i] > arr[i + 1]) {
            counter++
            continue
        } else if (i == 0) {
           continue
        }
        else if (i == n - 1 && arr[i] > arr[i - 1]) {
            counter++
            continue
        }
        else if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
            counter++
            continue
        }
    }

    print(counter)
}