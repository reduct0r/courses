package t_algo.sprint3

import java.util.*
import java.io.*
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val line1 = br.readLine() ?: return
    val st1 = StringTokenizer(line1)
    val n = st1.nextToken().toInt()
    val root = st1.nextToken().toInt()

    val left = IntArray(n)
    val right = IntArray(n)
    for (i in 0 until n) {
        val st = StringTokenizer(br.readLine())
        left[i] = st.nextToken().toInt()
        right[i] = st.nextToken().toInt()
    }

    var lastVisited = -1
    val stackBST: Deque<Int> = ArrayDeque()
    var curr: Int? = root

    while (stackBST.isNotEmpty() || curr != -1) {
        while (curr != -1 && curr != null) {
            stackBST.push(curr)
            curr = left[curr]
        }
        curr = stackBST.pop()

        if (curr <= lastVisited) {
            println(0)
            return
        }
        lastVisited = curr
        curr = right[curr]
    }

    val height = IntArray(n)
    val stackPost: Deque<Int> = ArrayDeque()
    val outStack: Deque<Int> = ArrayDeque()

    stackPost.push(root)
    while (stackPost.isNotEmpty()) {
        val u = stackPost.pop()
        outStack.push(u)
        if (left[u] != -1) stackPost.push(left[u])
        if (right[u] != -1) stackPost.push(right[u])
    }

    while (outStack.isNotEmpty()) {
        val u = outStack.pop()
        val hL = if (left[u] == -1) 0 else height[left[u]]
        val hR = if (right[u] == -1) 0 else height[right[u]]

        if (abs(hL - hR) > 1) {
            println(0)
            return
        }
        height[u] = hL.coerceAtLeast(hR) + 1
    }

    println(1)
}
