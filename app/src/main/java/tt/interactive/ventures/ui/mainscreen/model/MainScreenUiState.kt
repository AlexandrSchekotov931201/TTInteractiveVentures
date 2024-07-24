package tt.interactive.ventures.ui.mainscreen.model

import androidx.annotation.StringRes

sealed class MainScreenUiState {
    object InitState : MainScreenUiState()
    object ShowLoading : MainScreenUiState()
    class ShowError(@StringRes val text: Int) : MainScreenUiState()
    object ReadyToNext : MainScreenUiState()
    object NotReadyToNext : MainScreenUiState()
}