package tt.interactive.ventures.ui.chartscreen.model

sealed class ChartScreenUiState {
    object InitState : ChartScreenUiState()
    class ShowData(val data: ChartDataModel) : ChartScreenUiState()
}