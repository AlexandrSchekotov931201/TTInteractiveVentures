package tt.interactive.ventures.data.network.responce

class PointsResponse(
    val points: List<Point>
) {
    class Point(
        val x: Float,
        val y: Float
    )
}