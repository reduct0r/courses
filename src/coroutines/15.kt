package coroutines

import kotlinx.coroutines.*


suspend fun fetchData(name: String, time: Long, fail: Boolean): String {
    delay(time)
    if (fail) throw Exception("$name failed")
    return "$name Data"
}

suspend fun main() {
    val scope = CoroutineScope(SupervisorJob() + CoroutineName("Test"))

    val data1 = scope.async { runCatching { fetchData("Service A", 200, false) }.getOrNull() }
    val data2 = scope.async { runCatching { fetchData("Service B", 566, true) }.getOrNull() }
    val data3 = scope.async { runCatching { fetchData("Service C", 234, false) }.getOrNull() }

    val finalResults = listOfNotNull(data1.await(), data2.await(), data3.await())

    println(finalResults)
    println(scope.isActive)
}