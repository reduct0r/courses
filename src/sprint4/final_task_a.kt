package sprint4

// https://contest.yandex.ru/contest/24414/run-report/145575246/

/*
-- ПРИНЦИП РАБОТЫ --
    Я реализовал поисковую систему с использованием инвертированного индекса для эффективного поиска релевантных документов.
    Индекс инкапсулирован в класс SearchIndex. Для каждого добавляемого документа подсчитываются частоты слов с использованием groupingBy и eachCount.
    Для каждого слова в индексе хранится список объектов DocumentRelevance (docId, relevance), где relevance - это частота слова в документе.
    Индекс - private mutableMapOf<String, MutableList<DocumentRelevance>>.
    Нумерация документов инкапсулирована внутри класса: каждый вызов addDocument присваивает следующий уникальный идентификатор начиная с 1,
    что предотвращает добавление документов с одним id.
    Затем создаются кандидаты как List<DocumentRelevance> из тех, где relevance > 0 (хотя в практике все >0).
    Из них выбирается топ-5 с помощью selectTopK.
    selectTopK работает следующим образом: проходимся по всем кандидатам, поддерживаем список до k лучших элементов.
    Если размер списка меньше k, добавляем кандидата. Иначе находим индекс худшего элемента в списке (findWorstIndex), и если кандидат лучше худшего, заменяем его.
    В конце сортируем полученный топ-k по компаратору (O(k log k) с k=5 ~ O(1)) для правильного порядка.
    findWorstIndex: линейный поиск - элемент, который хуже всех по компаратору
    Вывод строится с помощью buildString, обрабатывая каждый запрос последовательно.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Из описания следует, что индекс захватывает все вхождения слов с частотами, игнорируя отсутствующие в документах.
    Релевантность вычисляется как сумма частот уникальных слов запроса, что соответствует задаче (set игнорирует дубли в запросе).
    Фильтр > 0 исключает нерелевантные. Для топ-5 используется компаратор compareByDescending { relevance } thenBy { docId },
    что гарантирует правильный порядок: убывание релевантности, при равенстве - возрастание docId.
    selectTopK выбирает топ-5 по компаратору и упорядочивает их.
    Алгоритм охватывает все документы без полного перебора каждый раз.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Пусть N - количество документов,
    D - количество слов в одном документе,
    M - количество запросов,
    Q - количество слов в одном запросе (в худшем случае они все уникальные).
    Построение индекса: O(N * D).
    Для M запросов:
        Разбор запроса и set: O(Q) на запрос, суммарно O(M * Q).
        Накопление релевантности: в худшем случае O(Q * N) на запрос (если каждое слово встречается во всех документах), суммарно O(M * Q * N).
        Создание кандидатов: O(N) в худшем на запрос.
        selectTopK: O(N * k) с k=5 ~ O(N) на запрос.
    Итоговая сложность: O(N * D + M * Q + M * Q * N + M * N).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Индекс: O(N * D), так как каждый уникальный word-doc добавляется раз (<= N * D).
    Для обработки запросов: relevanceMap O(N), candidates O(N), topK O(k=5); парсинг запроса O(Q).
    Общая: O(N * D + N + M + M * Q).
*/

data class DocumentRelevance(
    val docId: Int,
    val relevance: Int
)

class SearchIndex {
    private val indexMap = mutableMapOf<String, MutableList<DocumentRelevance>>()
    private var nextId = 1
    fun addDocument(document: String) {
        val docId = nextId++
        val docWords = document.split(" ")
        val countWordsMap = docWords.groupingBy { it }.eachCount()
        countWordsMap.forEach { (word, count) ->
            indexMap.getOrPut(word) { mutableListOf() }.add(DocumentRelevance(docId, count))
        }
    }
    fun getRelevantDocuments(query: String): List<Int> {
        val queWordsSet = query.split(" ").toSet()
        if (queWordsSet.isEmpty()) return emptyList()
        val relevanceMap = mutableMapOf<Int, Int>()
        for (word in queWordsSet) {
            val occurrences = indexMap[word] ?: continue
            for (occ in occurrences) {
                relevanceMap[occ.docId] = relevanceMap.getOrDefault(occ.docId, 0) + occ.relevance
            }
        }
        if (relevanceMap.isEmpty()) return emptyList()
        val candidates = relevanceMap.map { (docId, relevance) -> DocumentRelevance(docId, relevance) }
        val comp = compareByDescending<DocumentRelevance> { it.relevance }.thenBy { it.docId }
        val topFive = selectTopK(candidates, 5, comp)
        return topFive.map { it.docId }
    }
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val index = SearchIndex()
    val n = reader.readLine().toInt()
    repeat(n) {
        index.addDocument(reader.readLine().trim())
    }
    val m = reader.readLine().toInt()
    val result = buildString {
        repeat(m) {
            val query = reader.readLine().trim()
            val relevant = index.getRelevantDocuments(query)
            appendLine(relevant.joinToString(" "))
        }
    }
    print(result)
}

fun selectTopK(
    candidates: List<DocumentRelevance>,
    k: Int = 5,
    comparator: Comparator<DocumentRelevance>
): List<DocumentRelevance> {
    val topK = mutableListOf<DocumentRelevance>()
    for (candidate in candidates) {
        if (topK.size < k) {
            topK.add(candidate)
        } else {
            val worstIndex = findWorstIndex(topK, comparator)
            if (comparator.compare(candidate, topK[worstIndex]) < 0) {
                topK[worstIndex] = candidate
            }
        }
    }
    return topK.sortedWith(comparator)
}

private fun findWorstIndex(
    topK: List<DocumentRelevance>,
    comparator: Comparator<DocumentRelevance>
): Int {
    var worstIndex = 0
    for (i in 1 until topK.size) {
        if (comparator.compare(topK[i], topK[worstIndex]) > 0) {
            worstIndex = i
        }
    }
    return worstIndex
}
