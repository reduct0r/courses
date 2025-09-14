package academy.sem1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserTest {

    //given when then

    @Test
    fun `GIVEN client with 100 WHEN deposit 50 THEN client has 150`(){
        val user = User(phoneNumber = "", balance = 100)
        user.deposit(50)
        assertEquals(150, user.getBalance())
    }

    @Test
    fun `GIVEN client with 100 WHEN withdraw 50 THEN client has 50 exception thrown`(){
        val user = User(phoneNumber = "", balance = 100)
        val result = runCatching { user.withdraw(150) }
        assertTrue(result.isFailure)
    }

}