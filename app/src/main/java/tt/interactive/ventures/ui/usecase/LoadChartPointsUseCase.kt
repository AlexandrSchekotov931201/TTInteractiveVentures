package tt.interactive.ventures.ui.usecase

import tt.interactive.ventures.ui.model.PointModel

interface LoadChartPointsUseCase {
    suspend operator fun invoke(count: Int): List<PointModel>
}