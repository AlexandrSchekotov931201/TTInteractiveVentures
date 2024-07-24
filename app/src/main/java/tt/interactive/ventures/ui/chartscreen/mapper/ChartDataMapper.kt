package tt.interactive.ventures.ui.chartscreen.mapper

import android.graphics.Color
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import tt.interactive.ventures.ui.model.PointModel
import tt.interactive.ventures.ui.chartscreen.model.ChartDataModel
import tt.interactive.ventures.ui.chartscreen.model.PointItem
import javax.inject.Inject

class ChartDataMapper @Inject constructor() {
    fun map(points: List<PointModel>): ChartDataModel {
        val sortedPoints = points.sortedBy { it.x }
        val pointItems = sortedPoints.map { PointItem(it.x, it.y) }
        val entries = sortedPoints.map { Entry(it.x, it.y) }
        val dataSet = LineDataSet(entries, "Points").apply {
            mode = LineDataSet.Mode.CUBIC_BEZIER
            cubicIntensity = 0.05f
            setCircleColor(Color.BLACK)
            setDrawHorizontalHighlightIndicator(false)
            disableDashedLine()
        }
        return ChartDataModel(
            pointItems,
            LineData(dataSet)
        )
    }
}