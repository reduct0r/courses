package t_algo.sprint4

import java.util.*
import java.io.*

class Event(val time: Int, val type: Int) : Comparable<Event> {
    override fun compareTo(other: Event): Int {
        return if (this.time != other.time) this.time.compareTo(other.time)
        else this.type.compareTo(other.type)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val n = reader.readLine()?.trim()?.toInt() ?: return

    val events = ArrayList<Event>(2 * n)
    val DAY_SECONDS = 24 * 3600

    repeat(n) {
        val st = StringTokenizer(reader.readLine())
        val h1 = st.nextToken().toInt()
        val m1 = st.nextToken().toInt()
        val s1 = st.nextToken().toInt()
        val h2 = st.nextToken().toInt()
        val m2 = st.nextToken().toInt()
        val s2 = st.nextToken().toInt()

        val start = h1 * 3600 + m1 * 60 + s1
        val end = h2 * 3600 + m2 * 60 + s2

        if (start == end && h1 == h2) {
            events.add(Event(0, 1))
            events.add(Event(DAY_SECONDS, -1))
        } else if (start < end) {
            events.add(Event(start, 1))
            events.add(Event(end, -1))
        } else {
            events.add(Event(start, 1))
            events.add(Event(DAY_SECONDS, -1))
            events.add(Event(0, 1))
            events.add(Event(end, -1))
        }
    }

    events.sort()

    var totalTime = 0
    var activeCount = 0
    var lastTime = 0

    for (event in events) {
        if (activeCount == n) {
            totalTime += (event.time - lastTime)
        }
        activeCount += event.type
        lastTime = event.time
    }

    println(totalTime)
}
