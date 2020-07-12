package com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.BasePresenter
import com.ekezet.base.di.ActivityScope
import com.ekezet.base.utils.formatAsMoney
import com.ekezet.cardsdemo.cards.R
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.BalanceOverviewSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.BalanceOverviewSpec.View
import javax.inject.Inject

/**
 * @author kiri
 */
@ActivityScope
class BalanceOverviewPresenter @Inject constructor() : BasePresenter<View, Interactor, Nothing>(),
    View.Presenter, Interactor.Presenter {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        view.setup(R.layout.view_balance_overview)
    }

    @SuppressLint("DefaultLocale")
    override fun onSelectedCardChanged(card: Card) {
        val currency = card.currency.toUpperCase()
        view.setCarriedOver(card.balanceCarriedOver.formatAsMoney(), currency)
    }
}
