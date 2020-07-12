package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.views

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.ekezet.base.di.ActivityScope
import com.ekezet.base.views.BaseView
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.CardPagerSpec.View
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject
import kotlinx.android.synthetic.main.view_card_pager.view.*

/**
 * @author kiri
 */
@SuppressLint("ViewConstructor")
@ActivityScope
class CardPagerView @Inject constructor(
    context: Context,
    private val presenter: View.Presenter
) : BaseView(context), View {
    override var cardIndex: Int
        get() = cardsViewPager.currentItem
        set(value) = cardsViewPager.setCurrentItem(value, false)

    private var tabMediator: TabLayoutMediator? = null

    override fun setup(idRes: Int) {
        super.setup(idRes)
        cardsViewPager.registerOnPageChangeCallback(MyOnPageChangeCallback())
    }

    override fun setCards(items: List<Card>) {
        cardsViewPager.adapter = CardPagerAdapter(items)
        if (tabMediator != null) {
            tabMediator?.detach()
        }
        tabMediator = TabLayoutMediator(tabLayout, cardsViewPager) { tab, position -> }
        tabMediator?.attach()
    }

    override fun toggleLeftArrow(isVisible: Boolean) {
        arrowLeftImage.isVisible = isVisible
    }

    override fun toggleRightArrow(isVisible: Boolean) {
        arrowRightImage.isVisible = isVisible
    }

    private inner class MyOnPageChangeCallback : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            presenter.onUserHasSelectedCard(position)
        }
    }
}
