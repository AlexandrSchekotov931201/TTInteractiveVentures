package tt.interactive.ventures.data.gateway

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import tt.interactive.ventures.data.mapper.ChallengeResponseMapper
import tt.interactive.ventures.data.network.api.ChallengeApi
import tt.interactive.ventures.domain.entity.PointEntity
import tt.interactive.ventures.domain.gateway.ChallengeGateway
import javax.inject.Inject

class ChallengeGatewayImpl @Inject constructor(
    private val challengeApi: ChallengeApi,
    private val challengeDataMapper: ChallengeResponseMapper
) : ChallengeGateway {

    private val mutex = Mutex()
    private val cachedPoints = MutableStateFlow<List<PointEntity>>(emptyList())

    override suspend fun fetchPoints(count: Int): List<PointEntity> {
        return mutex.withLock {
            val response = challengeApi.fetchPoints(count)
            val mappedPoints = challengeDataMapper.map(response)
            cachedPoints.value = mappedPoints
            mappedPoints
        }
    }

    override suspend fun getPoints(): List<PointEntity> {
        return mutex.withLock {
            cachedPoints.value
        }
    }
}