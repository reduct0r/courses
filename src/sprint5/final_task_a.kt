package sprint5

class Person(
    val name: String,
    val completedTasks: Int,
    val numOfFines: Int

): Comparable<Person> {

    companion object {
        private val comparator = compareBy<Person> { -it.completedTasks }
            .thenBy { it.numOfFines }
            .thenBy { it.name }
    }

    override fun compareTo(other: Person): Int = comparator.compare(this, other)
}

fun main() {
    print(Person("d", 3,4) > Person("d", 3,1))
}