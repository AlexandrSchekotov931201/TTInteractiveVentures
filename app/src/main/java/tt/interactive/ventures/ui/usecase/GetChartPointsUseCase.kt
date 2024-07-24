package tt.interactive.ventures.ui.usecase

import tt.interactive.ventures.ui.model.PointModel

interface GetChartPointsUseCase {
    suspend operator fun invoke(count: Int? = null): List<PointModel>
}