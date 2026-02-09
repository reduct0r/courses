package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val newsFlow = MutableSharedFlow<String>()

    // Корутина-издатель: шлет новости каждые 200 мс
    launch {
        for (i in 1..5) {
            delay(200)
            val news = "News $i"
            println("--- Sending: $news ---")
            newsFlow.emit(news)
        }
    }

    // Подписчик 1: начинает слушать сразу
    launch {
        newsFlow.collect { println("Sub 1 received: $it") }
    }

    launch {
        delay(500)
        newsFlow.collect {
            println("Sub 2 received: $it")
        }
    }

    delay(1500)
    println("Finishing...")
    coroutineContext.cancelChildren()
}
