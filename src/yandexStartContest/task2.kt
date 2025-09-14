package yandexStartContest

fun main(){
    val len: Int = readln().toInt()
    val arr1 = readln().split(" ").map { it.toInt() }
    val arr2 = readln().split(" ").map { it.toInt() }

    for (i in 0 until len) {
        print(arr1[i].toString() + " ")
        if (i < len -1) {
            print(arr2[i].toString() + " ")
        } else {
            print(arr2[i].toString())
        }
    }

}