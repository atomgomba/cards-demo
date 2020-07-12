package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.BasePresenter
import com.ekezet.base.di.ActivityScope
import com.ekezet.base.utils.formatAsDate
import com.ekezet.base.utils.formatAsMoney
import com.ekezet.cardsdemo.cards.R
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoSpec.Router
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoSpec.View
import javax.inject.Inject

/**
 * @author kiri
 */
@ActivityScope
class BalanceInfoPresenter @Inject constructor() : BasePresenter<View, Interactor, Router>(), View.Presenter, Interactor.Presenter {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        view.setup(R.layout.view_balance_info)
    }

    @SuppressLint("DefaultLocale")
    override fun onSelectedCardChanged(card: Card) = with(card) {
        val currency = currency.toUpperCase()
        view.setCurrentBalance(currentBalance.formatAsMoney(), currency)
        view.setMinPayment(minPayment.formatAsMoney(), currency)
        view.dueDate = dueDate.formatAsDate()
    }

    override fun onDetailsButtonClicked() {
        router!!.navigateToSelectedCardDetails()
    }
}
