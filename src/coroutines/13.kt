package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.EmptyCoroutineContext

@OptIn(FlowPreview::class)
fun main() = runBlocking {
    val searchQuery = MutableStateFlow("")
    val onlyInStock = MutableStateFlow(false)
    val scope: CoroutineScope = CoroutineScope(EmptyCoroutineContext)

    // 1. Делаем "замедленный" поток текста
    val debouncedQuery = searchQuery
        .debounce(500)
        .distinctUntilChanged()

    // 2. Объединяем его с мгновенным чекбоксом
    val resultFlow: StateFlow<String> = debouncedQuery
        .combine(onlyInStock) { text, checked ->
            "Searching for: [$text], In Stock: $checked"
        }
        .stateIn(
            scope = this, // Используем scope текущего runBlocking
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "Waiting..."
        )
    
    val job = launch {
        resultFlow.collect { println("UI Display: $it") }
    }

    searchQuery.value = "Iphon"
    delay(100)
    searchQuery.value = "Iphone"

    delay(600)


    delay(1000)
    job.cancel()
}
