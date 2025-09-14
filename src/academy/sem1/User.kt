package academy.sem1

class User (
    val phoneNumber: String,
    private var balance: Int
) {
    fun getBalance(): Int{
        return balance
    }

    fun deposit(n: Int) {
        balance += n
    }

    fun withdraw(n: Int) {
        //if (n > balance) throw Throwable
        balance -= n
    }
}