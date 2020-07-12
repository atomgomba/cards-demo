package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import com.ekezet.cardsdemo.cards.R

/**
 * @author kiri
 */
class BalanceGraphView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    /**
     * 0...1
     */
    var progress: Float = 0F
        set(value) {
            field = value
            invalidate()
        }

    @ColorInt private val orange: Int = ContextCompat.getColor(context, R.color.orange)
    @ColorInt private val blue: Int = ContextCompat.getColor(context, R.color.primary_blue)

    @Px private val baselineWidthPx: Float
    @Px private val balanceWidthPx: Float
    @Px private val halfBaselineWidthPx: Float
    @Px private val halfBalanceWidthPx: Float

    private val paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    init {
        setWillNotDraw(false)

        baselineWidthPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, baselineStrokeWidthDp, resources.displayMetrics)
        halfBaselineWidthPx = baselineWidthPx / 2F
        balanceWidthPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, balanceStrokeWidthDp, resources.displayMetrics)
        halfBalanceWidthPx = balanceWidthPx / 2F
    }

    override fun onDraw(canvas: Canvas) {
        val halfHeight = height / 2F
        val maxWidth = width.toFloat()
        paint.apply {
            color = orange
            strokeWidth = baselineWidthPx
        }
        canvas.drawLine(halfBaselineWidthPx, halfHeight, maxWidth - halfBaselineWidthPx, halfHeight, paint)
        if (progress.equals(0F)) {
            return
        }
        paint.apply {
            color = blue
            strokeWidth = balanceWidthPx
        }
        canvas.drawLine(halfBalanceWidthPx + progress * maxWidth, halfHeight, maxWidth - halfBalanceWidthPx, halfHeight, paint)
    }

    companion object {
        const val baselineStrokeWidthDp = 4F
        const val balanceStrokeWidthDp = 12F
    }
}
