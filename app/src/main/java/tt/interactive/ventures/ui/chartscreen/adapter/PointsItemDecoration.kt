package tt.interactive.ventures.ui.chartscreen.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import tt.interactive.utils.dpToPx
import tt.interactive.ventures.R

class PointsItemDecoration(
    private val context: Context
) : RecyclerView.ItemDecoration() {

    private companion object {
        const val END_LINE_WIDTH_DP = 2
        const val TOP_LINE_WIDTH_DP = 2
        const val BOTTOM_LINE_WIDTH_DP = 2
    }

    private val endLinePaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.black)
        style = Paint.Style.STROKE
        strokeWidth = context.dpToPx(END_LINE_WIDTH_DP)
    }

    private val topLinePaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.black)
        style = Paint.Style.FILL
    }

    private val bottomLinePaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.black)
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        val childCount = parent.childCount
        if (childCount == 0) {
            return
        }

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.top - params.topMargin
            val bottomTop = top + context.dpToPx(TOP_LINE_WIDTH_DP)
            canvas.drawRect(
                child.left.toFloat(),
                top.toFloat(),
                child.right.toFloat(),
                bottomTop,
                topLinePaint
            )

            val bottom = child.bottom + params.bottomMargin
            val topBottom = bottom - context.dpToPx(BOTTOM_LINE_WIDTH_DP)
            canvas.drawRect(
                child.left.toFloat(),
                topBottom,
                child.right.toFloat(),
                bottom.toFloat(),
                bottomLinePaint
            )
        }

        val lastChild = parent.getChildAt(childCount - 1)
        val params = lastChild.layoutParams as RecyclerView.LayoutParams

        val left = lastChild.right + params.rightMargin
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom

        canvas.drawLine(
            left.toFloat(),
            top.toFloat(),
            left.toFloat(),
            bottom.toFloat(),
            endLinePaint
        )
    }
}