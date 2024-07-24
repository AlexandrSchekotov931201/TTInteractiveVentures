package tt.interactive.ventures.data.mapper

import tt.interactive.ventures.data.network.responce.PointsResponse
import tt.interactive.ventures.domain.entity.PointEntity
import javax.inject.Inject

class ChallengeResponseMapper @Inject constructor() {
    fun map(response: PointsResponse): List<PointEntity> {
        return response.points.map { PointEntity(it.x, it.y) }
    }
}