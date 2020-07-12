package com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview

import com.ekezet.base.arch.IInteractor
import com.ekezet.base.arch.IPresenter
import com.ekezet.base.arch.IView
import com.ekezet.base.arch.Part
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.BalanceOverviewSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.BalanceOverviewSpec.View
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.BalanceOverviewSpec.View.Presenter

/**
 * @author kiri
 */
interface BalanceOverviewSpec {
    interface View : IView {
        fun setCarriedOver(amount: CharSequence, currency: CharSequence)

        interface Presenter : IPresenter<View>
    }

    interface Interactor : IInteractor<Interactor.Presenter> {
        interface Presenter : IPresenter<View> {
            fun onSelectedCardChanged(card: Card)
        }
    }
}

typealias BalanceOverviewPart = Part<View, Presenter, Interactor.Presenter>
