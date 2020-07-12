package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager

import com.ekezet.base.arch.IInteractor
import com.ekezet.base.arch.IPresenter
import com.ekezet.base.arch.IView
import com.ekezet.base.arch.Part
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.CardPagerSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.CardPagerSpec.View
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.CardPagerSpec.View.Presenter
import kotlinx.coroutines.Job

/**
 * @author kiri
 */
interface CardPagerSpec {
    interface View : IView {
        var cardIndex: Int

        fun setCards(items: List<Card>)
        fun toggleLeftArrow(isVisible: Boolean)
        fun toggleRightArrow(isVisible: Boolean)

        interface Presenter : IPresenter<View> {
            fun onUserHasSelectedCard(current: Int)
        }
    }

    interface Interactor : IInteractor<Interactor.Presenter> {
        fun selectCardByIndex(index: Int): Job

        interface Presenter : IPresenter<View> {
            fun onCardsLoaded(items: List<Card>)
            fun onSelectedCardChanged(selected: Card, index: Int, total: Int)
        }
    }
}

typealias CardPagerPart = Part<View, Presenter, Interactor.Presenter>
