package sprint8

class TrieNode(
    val value: Char = '#',
    val children: MutableMap<Char, TrieNode> = emptyMap<Char, TrieNode>().toMutableMap(),
    var isEnd: Boolean = false
)

class Trie(
    val root: TrieNode = TrieNode()
) {
    fun add(str: String) {
        var curr = root
        for (c in str) {
            curr = curr.children.getOrPut(c) { TrieNode(c) }
        }
        curr.isEnd = true
    }

    fun getAllWords(
        curr: TrieNode = root,
        currentWord: String = "",
        result: MutableList<String> = mutableListOf()
    ): List<String> {

        if (curr.isEnd) {
            result.add(currentWord)
        }

        for ((char, nextNode) in curr.children) {
            getAllWords(nextNode, currentWord + char, result)
        }

        return result
    }

}

fun main() {
    val reader = System.`in`.bufferedReader()
    val input = reader.readLine()

    val trie = Trie()
    trie.add(input)

    trie.add("cat")
    trie.add("car")
    trie.add("cart")
    trie.add("dog")

    val words = trie.getAllWords()
    println(words)
}