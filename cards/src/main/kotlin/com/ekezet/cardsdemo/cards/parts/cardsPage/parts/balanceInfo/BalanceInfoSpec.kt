package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo

import com.ekezet.base.arch.IInteractor
import com.ekezet.base.arch.IPresenter
import com.ekezet.base.arch.IRouter
import com.ekezet.base.arch.IView
import com.ekezet.base.arch.Part
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoSpec.View
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoSpec.View.Presenter

/**
 * @author kiri
 */
interface BalanceInfoSpec {
    interface View : IView {
        var dueDate: CharSequence

        fun setCurrentBalance(value: CharSequence, currency: CharSequence)
        fun setMinPayment(value: CharSequence, currency: CharSequence)

        interface Presenter : IPresenter<View> {
            fun onDetailsButtonClicked()
        }
    }

    interface Interactor : IInteractor<Interactor.Presenter> {
        interface Presenter : IPresenter<View> {
            fun onSelectedCardChanged(card: Card)
        }
    }

    interface Router : IRouter {
        fun navigateToSelectedCardDetails()
    }
}

typealias BalanceInfoPart = Part<View, Presenter, Interactor.Presenter>
