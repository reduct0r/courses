package coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.delay

fun main() = runBlocking {
    val scope = CoroutineScope(SupervisorJob())

    val deferred = scope.async {
        delay(100)
        throw Exception("Network Error")
    }

    try {
        deferred.await()
    } catch (e: Exception) {
        println("Caught: ${e.message}")
    }

    delay(500)
    println("Scope active: ${scope.isActive}")
}
