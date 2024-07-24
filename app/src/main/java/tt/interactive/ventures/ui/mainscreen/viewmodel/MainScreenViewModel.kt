package tt.interactive.ventures.ui.mainscreen.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tt.interactive.ventures.ui.usecase.GetNetworkAvailabilityUseCase
import tt.interactive.ventures.ui.usecase.LoadChartPointsUseCase
import tt.interactive.core.ui.viewmodel.BaseViewModel
import tt.interactive.ventures.R
import tt.interactive.ventures.ui.mainscreen.model.MainScreenEvent
import tt.interactive.ventures.ui.mainscreen.model.MainScreenUiState
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val loadChartPointsUseCase: LoadChartPointsUseCase,
    private val getNetworkAvailabilityUseCase: GetNetworkAvailabilityUseCase
) : BaseViewModel<MainScreenUiState, MainScreenEvent>(
    MainScreenUiState.InitState
) {

    private var inputState: Int = 0

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModelScope.launch {
            event.collect { effect ->
                when (effect) {
                    is MainScreenEvent.NextButtonClick -> {
                        processNextButtonClick()
                    }

                    is MainScreenEvent.ChangedInputState -> {
                        if (effect.number in 1..1000) {
                            emitState(MainScreenUiState.ReadyToNext)
                        } else {
                            emitState(MainScreenUiState.NotReadyToNext)
                        }
                        inputState = effect.number
                    }

                    else -> {}
                }
            }
        }
    }

    private suspend fun processNextButtonClick() {
        try {
            emitState(MainScreenUiState.ShowLoading)
            val isNetworkAvailable = getNetworkAvailabilityUseCase()
            if (isNetworkAvailable) {
                val result = loadChartPointsUseCase(inputState)
                if (result.isNotEmpty()) {
                    emitEvent(MainScreenEvent.ShowChartScreen)
                } else {
                    emitState(MainScreenUiState.ShowError(R.string.main_screen_empty_result_error_text))
                }
            } else {
                emitState(MainScreenUiState.ShowError(R.string.main_screen_network_error_text))
            }
        } catch (ex: Exception) {
            emitState(MainScreenUiState.ShowError(R.string.main_screen_error_text))
        }
    }

}