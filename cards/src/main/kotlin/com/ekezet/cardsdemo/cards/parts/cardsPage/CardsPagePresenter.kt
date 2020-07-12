package com.ekezet.cardsdemo.cards.parts.cardsPage

import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.BasePresenter
import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.R
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.cardsPage.CardsPageSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.cardsPage.CardsPageSpec.View
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorPart
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoPart
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.CardPagerPart
import javax.inject.Inject

/**
 * @author kiri
 */
@ActivityScope
class CardsPagePresenter @Inject constructor(
    private val cardPagerPart: CardPagerPart,
    private val balanceIndicatorPart: BalanceIndicatorPart,
    private val balanceInfoPart: BalanceInfoPart
) : BasePresenter<View, Interactor, Nothing>(), View.Presenter, Interactor.Presenter {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        with(view) {
            setup(R.layout.page_cards)
            mountCardPager(cardPagerPart, owner)
            mountBalanceIndicator(balanceIndicatorPart, owner)
            mountBalanceInfo(balanceInfoPart, owner)
        }
    }

    override fun onSelectedCardChanged(selected: Card) {
        view.setHeaderText(selected.name)
    }
}
