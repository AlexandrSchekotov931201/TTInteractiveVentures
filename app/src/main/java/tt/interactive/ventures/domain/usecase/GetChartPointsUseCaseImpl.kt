package tt.interactive.ventures.domain.usecase

import tt.interactive.ventures.domain.gateway.ChallengeGateway
import tt.interactive.ventures.domain.mapper.ChartPointsMapper
import tt.interactive.ventures.ui.model.PointModel
import tt.interactive.ventures.ui.usecase.GetChartPointsUseCase
import javax.inject.Inject

class GetChartPointsUseCaseImpl @Inject constructor(
    private val challengeGateway: ChallengeGateway,
    private val chartPointsMapper: ChartPointsMapper
) : GetChartPointsUseCase {
    override suspend fun invoke(count: Int?): List<PointModel> {
        val pointEntities = challengeGateway.getPoints()
        return chartPointsMapper.map(pointEntities)
    }
}