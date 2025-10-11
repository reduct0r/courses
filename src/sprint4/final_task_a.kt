package sprint4
// https://contest.yandex.ru/contest/24414/run-report/144688509/

/*
-- ПРИНЦИП РАБОТЫ --
    Я реализовал поисковую систему с использованием индекса для эффективного поиска релевантных документов.
    Сначала строится индекс: для каждого документа подсчитываются частоты слов, и для каждого слова хранится список пар (номер документа, частота).
    Индекс - mutableMapOf<String, MutableList<Pair<Int, Int>>>.
    Для каждого запроса: уникальные слова запроса (set), для каждого слова берется список документов из индекса.
    Накопление релевантности в mutableMapOf<Int, Int> как сумма частот по словам для каждого документа.
    Затем фильтруются документы с релевантностью >0, сортируются по убыванию релевантности, при равенстве - по возрастанию номера.
    Берется топ-5 (или меньше), сохраняется в result map по индексу запроса.
    Вывод буферизирован через StringBuilder для эффективности.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Из описания следует, что индекс захватывает все вхождения слов с частотами, игнорируя отсутствующие в документах.
    Релевантность вычисляется как сумма частот уникальных слов запроса, что соответствует задаче (set игнорирует дубли в запросе).
    Фильтр >0 исключает нерелевантные, сортировка compareByDescending { rel } thenBy { id } гарантирует правильный порядок.
    Take(5) дает топ, empty для 0. Для отсутствующих слов - emptyList, не влияет на релевантность.
    Алгоритм охватывает все возможные документы без полного перебора каждый раз.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Построение индекса - O(общее число слов в документах), так как для каждого слова инкремент и добавление в список O(1).
    Для m запросов: разбор O(слов в запросе), накопление O(сумма длин списков для слов запроса), сортировка O(c log c) где c <= n.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Индекс O(общее число уникальных слов-вхождений) ~ O(n).
    Для запроса: relevantMap O(кандидатов) <= O(n).
    Result map O(m * 5). Дополнительная память O(n) на худший запрос.
*/

fun main(){
    val reader = System.`in`.bufferedReader()
    val documents = mutableListOf<String>()
    val queries = mutableListOf<String>()

    repeat(reader.readLine().toInt()) {
        documents.add(reader.readLine().trim())
    }

    repeat(reader.readLine().toInt()) {
        queries.add(reader.readLine().trim())
    }

    val relevantMap = findRelevantDocuments(documents, queries)
    val sb = StringBuilder()

    for (i in 0 until queries.size) {
        val line = relevantMap[i]?.joinToString(" ") ?: ""
        sb.append(line).append("\n")
    }

    print(sb.toString())
}

fun makeDocumentsIndex(documents: MutableList<String>
): MutableMap<String, MutableList<kotlin.Pair<Int, Int>>> {
    val indexMap = mutableMapOf<String, MutableList<kotlin.Pair<Int, Int>>>()

    for (i in documents.indices) {
        val docWords = documents[i].split(" ")
        val countWordsMap = mutableMapOf<String, Int>()

        for (word in docWords){
            countWordsMap[word] = countWordsMap.getOrDefault(word, 0) + 1
        }

        for (word in countWordsMap.keys) {
            indexMap.getOrPut(word) { mutableListOf() }.add(kotlin.Pair(i + 1, countWordsMap[word]!!))
        }
    }

    return indexMap
}

fun findRelevantDocuments(
    documents: MutableList<String>,
    queries: MutableList<String>
): MutableMap<Int, List<Int>> {
    val index = makeDocumentsIndex(documents)
    val result = mutableMapOf<Int, List<Int>>()

    for (i in queries.indices) {
        val queWordsSet = queries[i].split(" ").toSet()
        val relevantMap = mutableMapOf<Int, Int>()

        for (word in queWordsSet) {
            val listOfDocuments = index[word] ?: emptyList()

            for (el in listOfDocuments) {
                relevantMap[el.first] = relevantMap.getOrDefault(el.first, 0) + el.second
            }
        }

        val candidates = relevantMap.entries
            .filter { it.value > 0 }
            .map { kotlin.Pair(it.key, it.value) }
            .sortedWith(compareByDescending<kotlin.Pair<Int, Int>> { it.second }.thenBy { it.first })

        val topFive = candidates.take(5).map { it.first }

        if (topFive.isNotEmpty()) {
            result[i] = topFive
        } else {
            result[i] = emptyList()
        }
    }

    return result
}
