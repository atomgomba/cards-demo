package com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph

import com.ekezet.base.arch.IInteractor
import com.ekezet.base.arch.IPresenter
import com.ekezet.base.arch.IView
import com.ekezet.base.arch.Part
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.DetailedGraphSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.DetailedGraphSpec.View
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.DetailedGraphSpec.View.Presenter

/**
 * @author kiri
 */
interface DetailedGraphSpec {
    interface View : IView {
        fun setAmounts(current: Float, available: Float, pending: Float)
        fun setCurrency(text: CharSequence)

        interface Presenter : IPresenter<View>
    }

    interface Interactor : IInteractor<Interactor.Presenter> {
        interface Presenter : IPresenter<View> {
            fun onSelectedCardChanged(card: Card)
        }
    }
}

typealias DetailedGraphPart = Part<View, Presenter, Interactor.Presenter>
