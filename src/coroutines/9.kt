package coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        // Корутина 1: Обычный запуск
        launch {
            while (true) {
                println("Local scope is working...")
                delay(200)
            }
        }

        // Корутина 2: GlobalScope
        GlobalScope.launch {
            while (true) {
                println("GlobalScope is working...")
                delay(200)
            }
        }
    }

    delay(500)
    println("--- Cancelling parent job ---")
    job.cancelAndJoin()

    delay(1000) // Даем время посмотреть, не выживет ли кто-то
    println("Main finished")
}

/**
GlobalScope создает корутины, которые привязаны к жизненному циклу всего приложения,
а не к той функции или блоку, где они вызваны. Они не участвуют в Structured Concurrency.
 */
