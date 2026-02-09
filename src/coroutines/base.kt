package coroutines

import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    launch {
        delay(1000)
        print("first")
    }
    launch {
        delay(1000)
        print("second")
    }

    val job: Job = launch {
        print("3")
    }

}