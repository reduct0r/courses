package coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope

suspend fun main () {
    coroutineScope {
        val context = CoroutineName("name1") + Job()
        val context2 = context + CoroutineName("name2")

        println(context[CoroutineName]?.name)
        println(context2[CoroutineName]?.name)

        println(context[Job])
        println(context2[Job])
    }
}