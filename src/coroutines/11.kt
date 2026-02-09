package coroutines

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel(
    private val _defaultUserName: MutableStateFlow<String> = MutableStateFlow("Anonymous"),
    val userName: StateFlow<String> = _defaultUserName.asStateFlow()
) {
    fun updateUser(newName: String) {
        _defaultUserName.value = newName
    }
}