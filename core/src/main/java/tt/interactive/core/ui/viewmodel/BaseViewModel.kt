package tt.interactive.core.ui.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState, Event>(
    initState: UiState
) : ViewModel(), DefaultLifecycleObserver {

    private val _uiState = MutableStateFlow(initState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    protected fun emitState(state: UiState) {
        _uiState.value = state
    }

    fun emitEvent(effect: Event) {
        viewModelScope.launch {
            _event.emit(effect)
        }
    }

}