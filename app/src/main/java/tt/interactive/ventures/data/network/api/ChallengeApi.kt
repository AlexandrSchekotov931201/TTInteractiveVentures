package tt.interactive.ventures.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import tt.interactive.ventures.data.network.responce.PointsResponse

interface ChallengeApi {
    @GET("/api/test/points")
    suspend fun fetchPoints(
        @Query("count")
        count: Int
    ): PointsResponse
}