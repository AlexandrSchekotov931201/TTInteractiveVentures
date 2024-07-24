package tt.interactive.ventures.domain.usecase

import tt.interactive.ventures.domain.gateway.ChallengeGateway
import tt.interactive.ventures.domain.mapper.ChartPointsMapper
import tt.interactive.ventures.ui.model.PointModel
import tt.interactive.ventures.ui.usecase.LoadChartPointsUseCase
import javax.inject.Inject

class LoadChartPointsUseCaseImpl @Inject constructor(
    private val challengeGateway: ChallengeGateway,
    private val chartPointsMapper: ChartPointsMapper
) : LoadChartPointsUseCase {
    override suspend fun invoke(count: Int): List<PointModel> {
        val pointEntities = challengeGateway.fetchPoints(count)
        return chartPointsMapper.map(pointEntities)
    }
}