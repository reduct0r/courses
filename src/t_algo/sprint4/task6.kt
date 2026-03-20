package t_algo.sprint4

import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader

data class Segment(val l: Int, val r: Int) : Comparable<Segment> {
    override fun compareTo(other: Segment): Int {
        return if (this.l != other.l) this.l.compareTo(other.l)
        else this.r.compareTo(other.r)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val nLine = reader.readLine() ?: return
    val n = nLine.trim().toInt()

    if (n == 0) {
        println(0)
        return
    }

    val segments = Array(n) {
        val st = StringTokenizer(reader.readLine())
        Segment(st.nextToken().toInt(), st.nextToken().toInt())
    }

    segments.sort()

    var totalLength = 0L
    var currentL = segments[0].l
    var currentR = segments[0].r

    for (i in 1 until n) {
        val nextL = segments[i].l
        val nextR = segments[i].r

        if (nextL < currentR) {
            if (nextR > currentR) {
                currentR = nextR
            }
        } else {
            totalLength += (currentR - currentL).toLong()
            currentL = nextL
            currentR = nextR
        }
    }

    totalLength += (currentR - currentL).toLong()

    println(totalLength)
}
