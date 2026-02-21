package sprint8_

class TrieNode {
    val children = arrayOfNulls<TrieNode>(26)
    var isEnd: Boolean = false
}

class Trie {
    val root = TrieNode()

    fun add(str: String) {
        var curr = root
        for (c in str) {
            val index = c - 'a'
            if (curr.children[index] == null) {
                curr.children[index] = TrieNode()
            }
            curr = curr.children[index]!!
        }
        curr.isEnd = true
    }
}

fun canSplitByWords(text: String, trie: Trie): Boolean {
    val n = text.length
    val dp = BooleanArray(n + 1)
    dp[0] = true

    for (i in 0 until n) {
        if (!dp[i]) continue

        var curr = trie.root
        for (j in i until n) {
            val index = text[j] - 'a'
            val nextNode = curr.children[index] ?: break

            curr = nextNode
            if (curr.isEnd) {
                dp[j + 1] = true
            }
        }
        if (dp[n]) return true
    }
    return dp[n]
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val text = reader.readLine()
    val n = reader.readLine().toInt()
    val trie = Trie()

    repeat(n) {
        trie.add(reader.readLine())
    }

    println(if (canSplitByWords(text, trie)) "YES" else "NO")
}
