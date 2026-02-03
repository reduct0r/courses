package practice

import java.util.StringTokenizer

class Modification(val l: Long, val r: Long, val lenBefore: Long)

fun main() {
    val reader = System.`in`.bufferedReader()

    val firstLine = StringTokenizer(reader.readLine() ?: return)
    val n = firstLine.nextToken().toLong()
    val q = firstLine.nextToken().toInt()

    val s = reader.readLine() ?: ""

    val history = mutableListOf<Modification>()
    var currentLen = n

    repeat(q) {
        val line = reader.readLine() ?: return@repeat
        val st = StringTokenizer(line)
        val type = st.nextToken().toInt()

        if (type == 1) {
            val l = st.nextToken().toLong()
            val r = st.nextToken().toLong()
            val subLen = r - l + 1

            history.add(Modification(l, r, currentLen))
            currentLen += subLen
        } else {
            var i = st.nextToken().toLong()

            for (j in history.size - 1 downTo 0) {
                val mod = history[j]
                val subLen = mod.r - mod.l + 1

                if (i > mod.r + subLen) {
                    i -= subLen
                }
                else if (i >= mod.l && i <= mod.r + subLen) {
                    val posInDoubled = i - mod.l + 1
                    val originalOffset = (posInDoubled + 1) / 2
                    i = mod.l + originalOffset - 1
                }
            }
            println(s[(i - 1).toInt()])
        }
    }
}
