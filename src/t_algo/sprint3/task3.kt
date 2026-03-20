package t_algo.sprint3

import java.util.*
import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val nLine = br.readLine() ?: return
    val n = nLine.trim().toInt()

    val parents = IntArray(n)
    parents[0] = -1

    val st = StringTokenizer(br.readLine())
    for (i in 1 until n) {
        parents[i] = st.nextToken().toInt()
    }

    val mLine = br.readLine() ?: return
    val m = mLine.trim().toInt()

    val sb = StringBuilder()
    repeat(m) {
        val stReq = StringTokenizer(br.readLine())
        var u = stReq.nextToken().toInt()
        var v = stReq.nextToken().toInt()

        val pathU = mutableSetOf<Int>()
        var currU = u
        while (currU != -1) {
            pathU.add(currU)
            currU = parents[currU]
        }

        var currV = v
        while (currV != -1) {
            if (pathU.contains(currV)) {
                sb.append(currV).append("\n")
                break
            }
            currV = parents[currV]
        }
    }

    print(sb.toString())
}
