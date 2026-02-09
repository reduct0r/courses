package coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

suspend fun main() {
    coroutineScope {
        val text1: Deferred<String> = async {
            delay(1000)
            "text1"
        }

        val text2: Deferred<String> = async {
            delay(2000)
            "text2"
        }

        val res = text1.await() + text2.await()
    }
}