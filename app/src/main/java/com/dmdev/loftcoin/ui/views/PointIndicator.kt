package com.dmdev.loftcoin.ui.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.TypedValue
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmdev.loftcoin.R

class PointIndicator(context: Context) : RecyclerView.ItemDecoration() {
    private val inactivePaint = Paint()
    private val activePaint = Paint()
    private val indicatorRadius: Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        3f,
        context.resources.displayMetrics
    )

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val adapter = parent.adapter
        if (adapter != null) {
            val totalWidth = 4 * indicatorRadius * adapter.itemCount
            val posX = (parent.width - totalWidth) / 2f
            val posY = parent.height - 2 * indicatorRadius
            val lm = parent.layoutManager
            var currentIndicator = RecyclerView.NO_POSITION
            if (lm is LinearLayoutManager) {
                currentIndicator = lm.findFirstCompletelyVisibleItemPosition()
            }
            for (i in 0 until adapter.itemCount) {
                drawIndicator(canvas, posX + 6 * indicatorRadius * i, posY, currentIndicator == i)
            }
        }
    }

    private fun drawIndicator(canvas: Canvas, x: Float, y: Float, active: Boolean) {
        canvas.drawCircle(x, y, indicatorRadius, if (active) activePaint else inactivePaint)
    }

    init {
        inactivePaint.style = Paint.Style.FILL
        inactivePaint.color = context.resources.getColor(R.color.white30)
        inactivePaint.isAntiAlias = true
        activePaint.style = Paint.Style.FILL
        activePaint.color = context.resources.getColor(R.color.white)
        activePaint.isAntiAlias = true
    }
}