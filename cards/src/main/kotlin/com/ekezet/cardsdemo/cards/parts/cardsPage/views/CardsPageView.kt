package com.ekezet.cardsdemo.cards.parts.cardsPage.views

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.arch.AnyPart
import com.ekezet.base.di.ActivityScope
import com.ekezet.base.views.BaseView
import com.ekezet.cardsdemo.cards.parts.cardsPage.CardsPageSpec.View
import javax.inject.Inject
import kotlinx.android.synthetic.main.page_cards.view.*

/**
 * @author kiri
 */
@ActivityScope
class CardsPageView @Inject constructor(context: Context) : BaseView(context), View {
    override fun setHeaderText(text: CharSequence) {
        cardNameText.text = text
    }

    override fun mountCardPager(part: AnyPart, owner: LifecycleOwner) {
        part.bootWithContainer(cardPagerContainer, owner.lifecycle)
    }

    override fun mountBalanceIndicator(part: AnyPart, owner: LifecycleOwner) {
        part.bootWithContainer(balanceIndicatorContainer, owner.lifecycle)
    }

    override fun mountBalanceInfo(part: AnyPart, owner: LifecycleOwner) {
        part.bootWithContainer(balanceInfoContainer, owner.lifecycle)
    }
}
