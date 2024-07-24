package tt.interactive.ventures.ui.chartscreen.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tt.interactive.ventures.ui.usecase.GetChartPointsUseCase
import tt.interactive.core.ui.viewmodel.BaseViewModel
import tt.interactive.ventures.ui.chartscreen.mapper.ChartDataMapper
import tt.interactive.ventures.ui.chartscreen.model.ChartScreenEvent
import tt.interactive.ventures.ui.chartscreen.model.ChartScreenUiState
import javax.inject.Inject

class ChartScreenViewModel @Inject constructor(
    private val getChartPointsUseCase: GetChartPointsUseCase,
    private val chartDataMapper: ChartDataMapper
) : BaseViewModel<ChartScreenUiState, ChartScreenEvent>(ChartScreenUiState.InitState) {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModelScope.launch {
            val points = getChartPointsUseCase()
            val dataSet = chartDataMapper.map(points)
            emitState(ChartScreenUiState.ShowData(dataSet))
        }
    }

}