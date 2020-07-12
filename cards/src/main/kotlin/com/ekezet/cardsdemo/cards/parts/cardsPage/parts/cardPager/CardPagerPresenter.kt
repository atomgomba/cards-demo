package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager

import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.BasePresenter
import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.R
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.CardPagerSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.CardPagerSpec.View
import javax.inject.Inject

/**
 * @author kiri
 */
@ActivityScope
class CardPagerPresenter @Inject constructor() : BasePresenter<View, Interactor, Nothing>(),
    View.Presenter, Interactor.Presenter {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        view.setup(R.layout.view_card_pager)
    }

    override fun onCardsLoaded(items: List<Card>) {
        view.setCards(items)
    }

    override fun onSelectedCardChanged(selected: Card, index: Int, total: Int) {
        view.toggleLeftArrow(0 < index)
        view.toggleRightArrow(index < total - 1)
    }

    override fun onUserHasSelectedCard(current: Int) {
        interactor!!.selectCardByIndex(current)
    }
}
