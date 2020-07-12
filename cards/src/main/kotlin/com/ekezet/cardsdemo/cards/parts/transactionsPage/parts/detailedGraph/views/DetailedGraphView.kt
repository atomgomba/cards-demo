package com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Style
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.ekezet.base.di.ActivityScope
import com.ekezet.base.utils.formatAsMoney
import com.ekezet.base.views.BaseView
import com.ekezet.cardsdemo.cards.R
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.DetailedGraphSpec.View
import javax.inject.Inject
import kotlinx.android.synthetic.main.view_detailed_graph.view.*

/**
 * @author kiri
 */
@ActivityScope
class DetailedGraphView @Inject constructor(context: Context) : BaseView(context), View {
    @ColorInt private val orange20: Int = ContextCompat.getColor(context, R.color.orange_20)
    @ColorInt private val orange60: Int = ContextCompat.getColor(context, R.color.orange_60)
    @ColorInt private val blue: Int = ContextCompat.getColor(context, R.color.primary_blue)

    override fun setAmounts(current: Float, available: Float, pending: Float) {
        currentBalanceText.text = current.formatAsMoney()
        availableBalanceText.text = available.formatAsMoney()
        graphImage.setImageBitmap(createDetailedGraph(current, available, pending))
        pendingText.text = pending.formatAsMoney()
    }

    override fun setCurrency(text: CharSequence) {
        pendingCurrencyText.text = text
    }

    private fun createDetailedGraph(current: Float, available: Float, pending: Float): Bitmap {
        val maxWidth = graphImage.width.toFloat()
        val maxHeight = graphImage.height.toFloat()
        val total = current + available + pending
        val currentRatio = current / total
        val pendingRatio = pending / total

        val bmp = Bitmap.createBitmap(maxWidth.toInt(), maxHeight.toInt(), ARGB_8888)
        val canvas = Canvas(bmp)
        val paint = Paint().apply {
            style = Style.FILL
        }
        paint.color = orange60
        canvas.drawRect(0F, 0F, maxWidth * currentRatio, maxHeight, paint)
        paint.color = orange20
        canvas.drawRect(maxWidth * currentRatio, 0F, maxWidth * currentRatio + maxWidth * pendingRatio, maxHeight, paint)
        paint.color = blue
        canvas.drawRect(maxWidth * currentRatio + maxWidth * pendingRatio, 0F, maxWidth, maxHeight, paint)
        return bmp
    }
}
