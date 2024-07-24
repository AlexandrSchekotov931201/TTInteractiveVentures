package tt.interactive.ventures.domain.mapper

import tt.interactive.ventures.domain.entity.PointEntity
import tt.interactive.ventures.ui.model.PointModel
import javax.inject.Inject

class ChartPointsMapper @Inject constructor() {
    fun map(data: List<PointEntity>): List<PointModel> {
        return data.map { PointModel(it.x, it.y) }
    }
}