package sprint3

fun main() {
    val reader = System.`in`.bufferedReader()
    val seq = reader.readLine().trim()
    val string = reader.readLine().trim()

    println(if (isSubSeq(seq, string, 0, 0)) "True" else "False")
}

tailrec fun isSubSeq(seq: String, str: String, seqIndex: Int, strIndex: Int): Boolean {

    if (seqIndex == seq.length) {
        return true
    }

    if (strIndex == str.length) {
        return false
    }

    if (seq[seqIndex] == str[strIndex]) {
        return isSubSeq(seq, str, seqIndex + 1, strIndex + 1)
    }

    return isSubSeq(seq, str, seqIndex, strIndex + 1)
}