package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.views

import android.animation.ValueAnimator
import android.content.Context
import android.view.animation.BounceInterpolator
import androidx.core.content.ContextCompat
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import com.ekezet.base.di.ActivityScope
import com.ekezet.base.views.BaseView
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorSpec.View
import kotlinx.android.synthetic.main.view_balance_indicator.view.*
import javax.inject.Inject

/**
 * @author kiri
 */
@ActivityScope
class BalanceIndicatorView @Inject constructor(context: Context) : BaseView(context), View {
    private val graphAnimator = ValueAnimator().apply {
        addUpdateListener { animator -> graphView.progress = animator.animatedValue as Float }
    }
    private var alertAnimator = ValueAnimator().apply {
        duration = ALPHA_ANIM_MILLIS
        addUpdateListener { animator -> alertIconImage.alpha = animator.animatedValue as Float }
    }

    override fun setBalanceText(text: CharSequence, textColorRes: Int) {
        balanceText.apply {
            setText(text)
            setTextColor(ContextCompat.getColor(context, textColorRes))
        }
    }

    override fun setBalanceRatio(ratio: Float, isFastAnimation: Boolean) = with(graphAnimator) {
        if (ratio.equals(graphView.progress)) {
            return@with
        }
        if (isRunning) {
            end()
        }
        if (isFastAnimation) {
            duration = BALANCE_ANIM_FAST_MILLIS
            interpolator = FastOutLinearInInterpolator()
        } else {
            duration = BALANCE_ANIM_DEFAULT_MILLIS
            interpolator = BounceInterpolator()
        }
        setFloatValues(graphView.progress, ratio)
        start()
    }

    override fun toggleAlert(show: Boolean) = with(alertAnimator) {
        if (isRunning) {
            end()
        }
        setFloatValues(alertIconImage.alpha, if (show) 1F else 0F)
        start()
    }

    companion object {
        const val BALANCE_ANIM_DEFAULT_MILLIS = 900L
        const val BALANCE_ANIM_FAST_MILLIS = 300L
        const val ALPHA_ANIM_MILLIS = 300L
    }
}
