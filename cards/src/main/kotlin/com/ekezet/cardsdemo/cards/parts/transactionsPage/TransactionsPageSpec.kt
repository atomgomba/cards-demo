package com.ekezet.cardsdemo.cards.parts.transactionsPage

import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.arch.AnyPart
import com.ekezet.base.arch.IInteractor
import com.ekezet.base.arch.IPresenter
import com.ekezet.base.arch.IRouter
import com.ekezet.base.arch.IView
import com.ekezet.base.arch.Part
import com.ekezet.cardsdemo.cards.parts.transactionsPage.TransactionsPageSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.transactionsPage.TransactionsPageSpec.View

/**
 * @author kiri
 */
interface TransactionsPageSpec {
    interface View : IView {
        fun mountDetailedGraph(part: AnyPart, owner: LifecycleOwner)
        fun mountBalanceOverview(part: AnyPart, owner: LifecycleOwner)

        interface Presenter : IPresenter<View> {
            fun onBackButtonClicked()
        }
    }

    interface Interactor : IInteractor<Interactor.Presenter> {
        interface Presenter : IPresenter<View>
    }

    interface Router : IRouter {
        fun navigateToCards()
    }
}

typealias TransactionsPagePart = Part<View, View.Presenter, Interactor.Presenter>
