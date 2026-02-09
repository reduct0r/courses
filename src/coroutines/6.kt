package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun getNumbersFlow(): Flow<Int> = (1..10).asFlow()

suspend fun processFlow() {
    val flow = getNumbersFlow()
    flow.filter {
        it % 2 == 0
    }.map {
        delay(100)
        it * it
    }.collect { value ->
        print("$value ")
    }
}

fun main() = runBlocking {
    processFlow()
}
