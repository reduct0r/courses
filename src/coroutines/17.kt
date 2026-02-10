package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking {
    // 1. Создаем канал (очередь заказов)
    val pizzaChannel = Channel<Int>()

    // 2. Повар №1 (запускаем в отдельной корутине)
    launch {
        for (order in pizzaChannel) { // Цикл живет, пока канал не закроют
            println("Повар 1 готовит заказ №$order")
            delay(300) // Имитация готовки
        }
        println("Повар 1 ушел домой")
    }

    // 3. Повар №2 (еще одна корутина)
    launch {
        for (order in pizzaChannel) {
            println("Повар 2 готовит заказ №$order")
            delay(500) // Этот повар чуть медленнее
        }
        println("Повар 2 ушел домой")
    }

    // 4. Официант (основной поток) отправляет 10 заказов
    for (i in 1..10) {
        println("Заказ №$i поступил на кухню")
        pizzaChannel.send(i) // Кладем заказ в "трубу"
    }

    // 5. ВАЖНО: Закрываем канал, иначе повара будут ждать вечно
    pizzaChannel.close()

    println("Кухня больше не принимает заказы")
}
