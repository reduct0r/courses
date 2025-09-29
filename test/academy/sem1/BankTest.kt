package academy.sem1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BankTest {
    @Test
    fun `test`(){
        val toTransfer = 100
        val phone = "123"

        val sender = User("", toTransfer)
        val recipient = User(phone, toTransfer)
        val bank = Bank(listOf(User("", 100), User("", 150)))
    }
}