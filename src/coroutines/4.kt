package coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun getTemperature(): Int {
    delay(1000)
    return 25
}

suspend fun getWindSpeed(): Int {
    delay(1000)
    return 5
}

suspend fun getWeatherInfo(): String {
    return coroutineScope {
        val temp = async {
            getTemperature()
        }

        val windSpeed = async {
            getWindSpeed()
        }

        "Temperature: ${temp.await()}°C, Wind: ${windSpeed.await()}m/s"
    }
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        println(getWeatherInfo())
    }
    println("Time: $time ms")
}
