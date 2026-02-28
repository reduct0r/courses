package t_algo.sprint1

import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine()?.trim()?.toInt() ?: return
    val sb = StringBuilder()

    repeat(t) {
        val st = StringTokenizer(br.readLine())
        val n = st.nextToken().toLong()
        val m = st.nextToken().toLong()

        val totalSum = (n * m) * (n * m + 1) / 2

        var lowV = 2L
        var highV = m
        var bestV = 2L
        var minDiffV = Long.MAX_VALUE

        if (m >= 2) {
            while (lowV <= highV) {
                val mid = (lowV + highV) / 2
                val sV = n * (mid - 1) * (n * m - m + mid) / 2
                val diff = abs(totalSum - 2 * sV)
                if (diff < minDiffV || (diff == minDiffV && mid < bestV)) {
                    minDiffV = diff
                    bestV = mid
                }
                if (2 * sV < totalSum) lowV = mid + 1 else highV = mid - 1
            }
        }

        var lowH = 2L
        var highH = n
        var bestH = 2L
        var minDiffH = Long.MAX_VALUE

        if (n >= 2) {
            while (lowH <= highH) {
                val mid = (lowH + highH) / 2
                val sH = (mid - 1) * m * ((mid - 1) * m + 1) / 2
                val diff = abs(totalSum - 2 * sH)
                if (diff < minDiffH || (diff == minDiffH && mid < bestH)) {
                    minDiffH = diff
                    bestH = mid
                }
                if (2 * sH < totalSum) lowH = mid + 1 else highH = mid - 1
            }
        }

        if (minDiffV <= minDiffH && m >= 2) {
            sb.append("V $bestV\n")
        } else {
            sb.append("H $bestH\n")
        }
    }
    print(sb)
}
