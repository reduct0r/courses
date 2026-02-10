package coroutines
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
fun main() = runBlocking {
    val clickFlow = MutableSharedFlow<Unit>()

    val proceed = clickFlow.debounce(1000)

    // Эмуляция кликов
    launch {
        repeat(10) {
            clickFlow.emit(Unit)
            delay(100)
        }
    }

    val job = launch {
        clickFlow.debounce(1000).collect {
            println("click: $it")
        }
    }

    delay(2000)
    println("Завершаем работу...")
    job.cancel()
}
