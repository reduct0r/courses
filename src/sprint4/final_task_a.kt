package sprint4

// https://contest.yandex.ru/contest/24414/run-report/145081780/

/*
-- ПРИНЦИП РАБОТЫ --
    Я реализовал поисковую систему с использованием инвертированного индекса для эффективного поиска релевантных документов.
    Индекс инкапсулирован в класс SearchIndex. Для каждого добавляемого документа подсчитываются частоты слов с использованием groupingBy и eachCount.
    Для каждого слова в индексе хранится список объектов WordOccurrence (docId, count), где count - это частота слова в документе.
    Индекс - private mutableMapOf<String, MutableList<WordOccurrence>>.
    Для каждого запроса в методе getRelevantDocuments: уникальные слова запроса (toSet()), затем imperative накопление релевантности в mutableMap<Int, Int> путем суммирования count для каждого docId по всем словам.
    Затем создаются кандидаты как List<DocumentRelevance> из тех, где relevance > 0 (хотя в практике все >0).
    Из них выбирается топ-5 с помощью selectTopK, после чего топ-5 сортируется для правильного порядка.
    Вывод строится с помощью StringBuilder, обрабатывая каждый запрос последовательно.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Из описания следует, что индекс захватывает все вхождения слов с частотами, игнорируя отсутствующие в документах.
    Релевантность вычисляется как сумма частот уникальных слов запроса, что соответствует задаче (set игнорирует дубли в запросе).
    Фильтр > 0 исключает нерелевантные. Для топ-5 используется компаратор compareByDescending { relevance } thenBy { docId },
    что гарантирует правильный порядок: убывание релевантности, при равенстве - возрастание docId.
    selectTopK выбирает топ-5 по компаратору, а финальная сортировка (O(k log k) с k=5 ~ O(1)) упорядочивает их.
    Алгоритм охватывает все документы без полного перебора каждый раз.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Пусть n - число документов,
    A - общее число слов во всех документах,
    m - число запросов,
    Q - общее число слов во всех запросах,
    b - среднее число уникальных слов в одном запросе,
    v - размер словаря (unique words across all docs),
    L - средняя длина списка в индексе для слова (средняя document frequency, df, L ~ n / v в uniform модели).
    Построение индекса: O(A).
    Для m запросов:
        Разбор запроса и set: O(длина запроса), суммарно O(Q).
        Накопление релевантности: O(сумма длин списков для уникальных слов запроса) ~ O(m * b * L).
        Создание кандидатов: O(c), где c - число уникальных docId в собранных (c <= n).
        selectTopK: O(c * k) с k=5 ~ O(c).
        Сортировка топ-5: O(1).
    Итоговая сложность: O(A + Q + m * (b * L + c)).
    В худшем случае (L ~ n, c ~ n, b ~ Q/m): O(A + Q + m * ( (Q/m) * n + n )) ~ O(A + Q + Q * n + m n).
    С учетом ограничений (A, Q <= 10^6, m, n <= 10^4): в худшем O(10^6 + 10^6 * 10^4) = O(10^{10}), но на практике средние значения меньше, и imperative стиль минимизирует константы.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Индекс: O(A), так как каждый уникальный word-doc добавляется раз (U <= A).
    Для обработки запросов: relevanceMap O(c <= n), candidates O(c), topK O(k=5); парсинг запроса O(b).
    Общая: O(A + n + m + Q).
*/

data class DocumentRelevance(
    val docId: Int,
    val relevance: Int
)

data class WordOccurrence(
    val docId: Int,
    val count: Int
)

class SearchIndex {
    private val indexMap = mutableMapOf<String, MutableList<WordOccurrence>>()

    fun addDocument(docId: Int, document: String) {
        val docWords = document.split(" ")
        val countWordsMap = docWords.groupingBy { it }.eachCount()
        countWordsMap.forEach { (word, count) ->
            indexMap.getOrPut(word) { mutableListOf() }.add(WordOccurrence(docId, count))
        }
    }

    fun getRelevantDocuments(query: String): List<Int> {
        val queWordsSet = query.split(" ").toSet()
        if (queWordsSet.isEmpty()) return emptyList()

        val relevanceMap = mutableMapOf<Int, Int>()
        for (word in queWordsSet) {
            val occurrences = indexMap[word] ?: continue
            for (occ in occurrences) {
                relevanceMap[occ.docId] = relevanceMap.getOrDefault(occ.docId, 0) + occ.count
            }
        }

        if (relevanceMap.isEmpty()) return emptyList()

        val candidates = relevanceMap.map { (docId, relevance) -> DocumentRelevance(docId, relevance) }

        val comp = compareByDescending<DocumentRelevance> { it.relevance }.thenBy { it.docId }
        val topFive = selectTopK(candidates, 5, comp)

        return topFive.sortedWith(comp).map { it.docId }
    }
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val index = SearchIndex()

    val n = reader.readLine().toInt()
    repeat(n) { i ->
        index.addDocument(i + 1, reader.readLine().trim())
    }

    val sb = StringBuilder()
    repeat(reader.readLine().toInt()) {
        val query = reader.readLine().trim()
        val relevant = index.getRelevantDocuments(query)
        val line = if (relevant.isNotEmpty()) relevant.joinToString(" ") else ""
        sb.appendLine(line)
    }

    print(sb.toString())
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
            var worstIndex = 0
            for (i in 1 until topK.size) {
                if (comparator.compare(topK[i], topK[worstIndex]) > 0) {
                    worstIndex = i
                }
            }
            if (comparator.compare(candidate, topK[worstIndex]) < 0) {
                topK[worstIndex] = candidate
            }
        }
    }
    return topK
}
