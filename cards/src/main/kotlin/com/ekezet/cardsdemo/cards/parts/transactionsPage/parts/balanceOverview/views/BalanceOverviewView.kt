package com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.views

import android.content.Context
import com.ekezet.base.di.ActivityScope
import com.ekezet.base.views.BaseView
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.BalanceOverviewSpec.View
import javax.inject.Inject
import kotlinx.android.synthetic.main.view_balance_overview.view.*

/**
 * @author kiri
 */
@ActivityScope
class BalanceOverviewView @Inject constructor(context: Context) : BaseView(context), View {
    override fun setCarriedOver(amount: CharSequence, currency: CharSequence) {
        carriedOverText.text = amount
        carriedOverCurrencyText.text = currency
    }
}
