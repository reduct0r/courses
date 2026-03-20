package t_algo.sprint3

import java.util.*
import java.io.*

class MaxHeap(capacity: Int) {
    private val heap = IntArray(capacity)
    private var size = 0

    fun insert(x: Int) {
        heap[size] = x
        siftUp(size)
        size++
    }

    fun extract(): Int {
        val max = heap[0]
        size--
        if (size > 0) {
            heap[0] = heap[size]
            siftDown(0)
        }
        return max
    }

    private fun siftUp(index: Int) {
        var i = index
        while (i > 0) {
            val parent = (i - 1) / 2
            if (heap[i] <= heap[parent]) break
            swap(i, parent)
            i = parent
        }
    }

    private fun siftDown(index: Int) {
        var i = index
        while (true) {
            val left = 2 * i + 1
            val right = 2 * i + 2
            var largest = i

            if (left < size && heap[left] > heap[largest]) {
                largest = left
            }
            if (right < size && heap[right] > heap[largest]) {
                largest = right
            }

            if (largest == i) break
            swap(i, largest)
            i = largest
        }
    }

    private fun swap(i: Int, j: Int) {
        val temp = heap[i]
        heap[i] = heap[j]
        heap[j] = temp
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine()?.trim()?.toInt() ?: return

    val maxHeap = MaxHeap(n)
    val sb = StringBuilder()

    repeat(n) {
        val st = StringTokenizer(br.readLine())
        val command = st.nextToken()
        if (command == "0") {
            val x = st.nextToken().toInt()
            maxHeap.insert(x)
        } else {
            sb.append(maxHeap.extract()).append("\n")
        }
    }
    print(sb)
}
