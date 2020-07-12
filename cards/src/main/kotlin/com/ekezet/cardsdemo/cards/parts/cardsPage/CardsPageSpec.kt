package com.ekezet.cardsdemo.cards.parts.cardsPage

import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.arch.AnyPart
import com.ekezet.base.arch.IInteractor
import com.ekezet.base.arch.IPresenter
import com.ekezet.base.arch.IView
import com.ekezet.base.arch.Part
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.cardsPage.CardsPageSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.cardsPage.CardsPageSpec.View

/**
 * @author kiri
 */
interface CardsPageSpec {
    interface View : IView {
        fun setHeaderText(text: CharSequence)
        fun mountCardPager(part: AnyPart, owner: LifecycleOwner)
        fun mountBalanceIndicator(part: AnyPart, owner: LifecycleOwner)
        fun mountBalanceInfo(part: AnyPart, owner: LifecycleOwner)

        interface Presenter : IPresenter<View>
    }

    interface Interactor : IInteractor<Interactor.Presenter> {
        interface Presenter : IPresenter<View> {
            fun onSelectedCardChanged(selected: Card)
        }
    }
}

typealias CardsPagePart = Part<View, View.Presenter, Interactor.Presenter>
