package coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SearchViewModel(scope: CoroutineScope) {
    val searchQuery = MutableStateFlow("")

    // Превращаем цепочку операторов в StateFlow
    val searchResult: StateFlow<String> = searchQuery
        .debounce(500)
        .distinctUntilChanged()
        .map { query -> "Результат для: $query" }
        // ВОТ ОН:
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "Введите запрос..."
        )
}
