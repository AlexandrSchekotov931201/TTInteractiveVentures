package tt.interactive.ventures.domain.gateway

import tt.interactive.ventures.domain.entity.PointEntity

interface ChallengeGateway {
    suspend fun fetchPoints(count: Int): List<PointEntity>
    suspend fun getPoints(): List<PointEntity>
}