package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.views

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.view.animation.BounceInterpolator
import androidx.core.content.ContextCompat
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import com.ekezet.base.di.ActivityScope
import com.ekezet.base.views.BaseView
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorSpec.View
import javax.inject.Inject
import kotlinx.android.synthetic.main.view_balance_indicator.view.*

/**
 * @author kiri
 */
@ActivityScope
class BalanceIndicatorView @Inject constructor(context: Context) : BaseView(context), View {
    private val graphAnimator = ValueAnimator().apply {
        addUpdateListener { animator -> graphView.progress = animator.animatedValue as Float }
    }
    private var alertAnimator: ValueAnimator? = null

    override fun setBalanceText(text: CharSequence, textColorRes: Int) {
        balanceText.apply {
            setText(text)
            setTextColor(ContextCompat.getColor(context, textColorRes))
        }
    }

    override fun setBalanceRatio(ratio: Float, isFastAnimation: Boolean) {
        if (ratio.equals(graphView.progress)) {
            return
        }
        with(graphAnimator) {
            if (isRunning) {
                pause()
            }
            if (isFastAnimation) {
                duration = BALANCE_ANIM_FAST_MILLIS
                interpolator = FastOutLinearInInterpolator()
            } else {
                duration = BALANCE_ANIM_DEFAULT_MILLIS
                interpolator = BounceInterpolator()
            }
            setFloatValues(graphView.progress, ratio)
            if (isPaused) {
                resume()
            } else {
                start()
            }
        }
    }

    override fun toggleAlert(show: Boolean) {
        alertAnimator?.pause()
        alertAnimator = if (show) {
            ObjectAnimator.ofFloat(alertIconImage.alpha, 1F)
        } else {
            ObjectAnimator.ofFloat(alertIconImage.alpha, 0F)
        }.apply {
            duration = ALPHA_ANIM_MILLIS
            addUpdateListener { animator -> alertIconImage.alpha = animator.animatedValue as Float }
        }
        alertAnimator?.start()
    }

    companion object {
        const val BALANCE_ANIM_DEFAULT_MILLIS = 900L
        const val BALANCE_ANIM_FAST_MILLIS = 300L
        const val ALPHA_ANIM_MILLIS = 300L
    }
}
