package coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun apiCall(): String {
    // Имитация долгой работы.
    delay(1000)
    //throw Exception("Network failed")
    return "Success Data"
}

suspend fun fetchDataWithTimeout(): String {
    return try {
        withTimeoutOrNull(1500) {
            apiCall()
        } ?: "Timeout Error"
    } catch (e: Exception) {
        "Caught Error"
    }
}

fun main() = runBlocking {
    println(fetchDataWithTimeout())
}
