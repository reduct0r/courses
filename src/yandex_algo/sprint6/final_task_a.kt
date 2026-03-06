// https://contest.yandex.ru/contest/25070/run-report/154168730/

/**
-- ПРИНЦИП РАБОТЫ --
    Я реализовал адаптированный алгоритм Дейкстры для поиска максимального остовного дерева (maximum spanning tree).
    Граф представлен матрицей смежности, где хранится максимальный вес ребра между вершинами (если несколько - берётся max).
    Начинаем с вершины 0, инициализируя расстояния (longestDistToVertex) как MIN_PATH_LEN, кроме стартовой - 0.
    Пока есть непосещённые вершины с ключом > MIN:
        Выбираем вершину v с максимальным ключом, добавляем в дерево (visited=true).
        Для каждого непосещённого соседа u обновляем ключ, если вес ребра > текущего ключа u.
        Сумма расстояний - вес максимального остовного дерева.
        Если не все вершины посещены - граф несвязный, выводим "Oops! I did it again".

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Алгоритм следует жадному свойству для максимального остовного дерева:
    на каждом шаге добавляется максимальное ребро, соединяющее дерево с внешней вершиной, без циклов.
    Обновление ключей только для непосещённых обеспечивает отсутствие циклов.
    Выбор max ребра среди возможных гарантирует оптимальность для жадных алгоритмов.
    Обработка нескольких рёбер между одинаковыми парами вершин берёт max.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Построение графа: O(m), где m - рёбра.
    Алгоритм MaxST: O(n^2) - для n вершин, каждый раз просматриваем n для поиска max вершины (getMaxNotVisitedVertex),
    и n для обновления соседей.
    Общая сложность O(n^2) в худшем случае.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Матрица смежности: O(n^2).
    Массивы visited и longestDistToVertex: O(n).
    Список весов: O(n).
    Итого O(n^2).
 */

const val MIN_PATH_LEN = Int.MIN_VALUE / 2

class Graph(
    val numOfVertex: Int
) {
    val matrix: Array<Array<Int?>> = Array(numOfVertex) { arrayOfNulls(numOfVertex) }
    val visited: BooleanArray = BooleanArray(numOfVertex)
    val longestDistToVertex: IntArray = IntArray(numOfVertex) { MIN_PATH_LEN }

    fun addEdge(v: Int, u: Int, weight: Int) {
        when {
            matrix[v][u] == null || matrix[v][u]!! < weight -> {
                matrix[v][u] = weight
                matrix[u][v] = weight
            }
        }
    }

    fun getMaxNotVisitedVertex(): Int? {
        var currentMax = MIN_PATH_LEN
        var maxVertex: Int? = null
        for (i in 0 until numOfVertex) {
            if (!visited[i] && longestDistToVertex[i] > currentMax) {
                currentMax = longestDistToVertex[i]
                maxVertex = i
            }
        }
        return maxVertex
    }
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Graph(n)
    repeat(m) {
        val (v, u, w) = reader.readLine().split(" ").map { it.toInt() }
        graph.addEdge(v - 1, u - 1, w)
    }
    val (maxSpanTreeWeight, visitedCount) = findMaxSpanTree(graph)
    if (visitedCount < n) {
        print("Oops! I did it again")
    } else {
        print(maxSpanTreeWeight)
    }
}

fun findMaxSpanTree(graph: Graph): Pair<Long, Int> {
    val start = 0
    graph.longestDistToVertex[start] = 0
    var sum = 0L
    var visitedCount = 0
    while (true) {
        val v = graph.getMaxNotVisitedVertex() ?: break
        graph.visited[v] = true
        visitedCount++
        sum += graph.longestDistToVertex[v].toLong()
        for (u in 0 until graph.numOfVertex) {
            if (graph.visited[u]) continue
            val w = graph.matrix[v][u] ?: continue
            if (w > graph.longestDistToVertex[u]) {
                graph.longestDistToVertex[u] = w
            }
        }
    }
    return sum to visitedCount
}