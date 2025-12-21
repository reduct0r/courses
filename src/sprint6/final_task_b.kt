package sprint6_fb

import java.util.Stack

// https://contest.yandex.ru/contest/25070/run-report/154168902/

/**
-- ПРИНЦИП РАБОТЫ --
    Я реализовал поиск связных компонент с помощью DFS.
    Сетка представлена как строка input, где клетки доступны по индексу i*m + j.
    Для каждой непосещённой '#' клетки запускается DFS: помечаем visited, считаем размер компоненты (острова).
    Соседи генерируются по 4 направлениям (вверх, вниз, влево, вправо), если они в границах и '#'.
    Считаем количество компонент (острова) и максимальный размер.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Внешний цикл проходит по всем клеткам, запуская DFS только от '#' и !visited.
    DFS обходит все достижимые '#' клетки, помечая visited, чтобы избежать повторных посещений и циклов.
    Размер накапливается, игнорируя воду ('.'), так как цикл стартует только от '#'.
    Алгоритм гарантирует полный обход графа.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Построение input: O(n*m).
    Генерация соседей: O(4) на клетку, в сумме O(n*m) для всех посещений.
    Внешний цикл + DFS: O(n*m), так как каждая клетка посещается O(1) раз.
    Общая сложность O(n*m) в худшем случае.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    input: O(n*m).
    visited: O(n*m).
    Стек DFS: O(n*m) в худшем случае.
    Итого O(n*m).
 */

class Graph(
    val n: Int,
    val m: Int,
    val grid: Array<CharArray>
) {
    val numOfVertex = n * m
    val visited = BooleanArray(numOfVertex) { false }

    private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

    fun getOutboundFrom(v: Int): List<Int> {
        val i = v / m
        val j = v % m
        val neighbors = mutableListOf<Int>()
        for ((di, dj) in directions) {
            val ni = i + di
            val nj = j + dj
            if (ni in 0 until n && nj in 0 until m && grid[ni][nj] == '#' && !visited[ni * m + nj]) {
                neighbors.add(ni * m + nj)
            }
        }
        return neighbors
    }

    operator fun get(v: Int): Boolean = !visited[v] && grid[v / m][v % m] == '#'
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val grid = Array(n) { reader.readLine().toCharArray() }
    val graph = Graph(n, m, grid)
    val (numComponents, maxWeight) = findComponents(graph)
    println("$numComponents $maxWeight")
}

fun findComponents(graph: Graph): Pair<Int, Int> {
    var numComponents = 0
    var maxWeight = 0
    for (v in 0 until graph.numOfVertex) {
        if (graph[v]) {
            numComponents++
            val componentWeight = dfs(graph, v)
            maxWeight = maxOf(maxWeight, componentWeight)
        }
    }
    return Pair(numComponents, maxWeight)
}

fun dfs(graph: Graph, start: Int): Int {
    val stack = Stack<Int>()
    stack.push(start)
    graph.visited[start] = true
    var size = 1
    while (!stack.isEmpty()) {
        val v = stack.pop()
        for (out in graph.getOutboundFrom(v)) {
            if (!graph.visited[out]) {
                graph.visited[out] = true
                stack.push(out)
                size++
            }
        }
    }
    return size
}