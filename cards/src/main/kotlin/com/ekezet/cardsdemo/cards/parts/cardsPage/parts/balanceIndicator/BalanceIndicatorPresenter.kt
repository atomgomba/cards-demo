package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator

import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.BasePresenter
import com.ekezet.base.di.ActivityScope
import com.ekezet.base.utils.formatAsMoney
import com.ekezet.cardsdemo.cards.R
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorSpec.View
import javax.inject.Inject

/**
 * @author kiri
 */
@ActivityScope
class BalanceIndicatorPresenter @Inject constructor() : BasePresenter<View, Interactor, Nothing>(),
    View.Presenter, Interactor.Presenter {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        view.setup(R.layout.view_balance_indicator)
    }

    override fun onSelectedCardChanged(card: Card) {
        val available = card.availableBalance
        val isZeroBalance = available.equals(0F)
        val textColor = if (isZeroBalance) {
            R.color.error_red
        } else {
            R.color.status_blue
        }
        val ratio = if (card.totalBalance.equals(0F))
            1F
        else
            1F - available / card.totalBalance
        with(view) {
            setBalanceText(available.formatAsMoney(), textColor)
            setBalanceRatio(ratio, isFastAnimation = isZeroBalance)
            toggleAlert(isZeroBalance)
        }
    }
}
