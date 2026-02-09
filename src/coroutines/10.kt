package coroutines

import kotlinx.coroutines.*
import sun.rmi.server.Dispatcher

suspend fun performWork() = coroutineScope {
    // 1. Загрузка (Network/Disk) - какой диспетчер?
    val data = withContext(Dispatchers.IO) {
    println("Downloading on ${Thread.currentThread().name}")
    delay(500)
    "Large Data"
}

    // 2. Расчет (CPU intensive) - какой диспетчер?
    val result = withContext(Dispatchers.Default) {
    println("Calculating on ${Thread.currentThread().name}")
    // Имитация вычислений
        (1..1_000_000).sumOf { it * it }
    }

    // 3. Обновление UI - какой диспетчер?
    withContext(Dispatchers.Main) {
    println("Updating UI on ${Thread.currentThread().name}: $result")
}
}

fun main() = runBlocking {
    performWork()
}
