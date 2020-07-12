package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator

import androidx.annotation.ColorRes
import com.ekezet.base.arch.IInteractor
import com.ekezet.base.arch.IPresenter
import com.ekezet.base.arch.IView
import com.ekezet.base.arch.Part
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorSpec.View
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorSpec.View.Presenter

/**
 * @author kiri
 */
interface BalanceIndicatorSpec {
    interface View : IView {
        fun setBalanceText(text: CharSequence, @ColorRes textColorRes: Int)
        fun setBalanceRatio(ratio: Float, isFastAnimation: Boolean)
        fun toggleAlert(show: Boolean)

        interface Presenter : IPresenter<View>
    }

    interface Interactor : IInteractor<Interactor.Presenter> {
        interface Presenter : IPresenter<View> {
            fun onSelectedCardChanged(card: Card)
        }
    }
}

typealias BalanceIndicatorPart = Part<View, Presenter, Interactor.Presenter>
