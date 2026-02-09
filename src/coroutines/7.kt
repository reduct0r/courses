package coroutines_7

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun getNumbersFlow(): Flow<Int> = (1..10).asFlow()

suspend fun processFlow1() {
    val time = measureTimeMillis {
        val flow = getNumbersFlow()
        flow.filter {
            it % 2 == 0
        }.map {
            delay(100)
            it * it
        }.collect { value ->
            delay(100)
            print("$value ")
        }
    }
    println("\nTime1: $time ms")
}



fun main() = runBlocking {
    processFlow1()

    val time = measureTimeMillis {
        getNumbersFlow()
            .filter { it % 2 == 0 }
            .map { delay(100); it * it }
            .buffer()
            .collect {
                delay(100)
                print("$it ")
            }
    }
    println("\nTime2: $time ms")

}

