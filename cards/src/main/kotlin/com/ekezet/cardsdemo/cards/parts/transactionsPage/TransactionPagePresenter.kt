package com.ekezet.cardsdemo.cards.parts.transactionsPage

import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.BasePresenter
import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.R
import com.ekezet.cardsdemo.cards.parts.transactionsPage.TransactionsPageSpec.Router
import com.ekezet.cardsdemo.cards.parts.transactionsPage.TransactionsPageSpec.View
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.balanceOverview.BalanceOverviewPart
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.DetailedGraphPart
import javax.inject.Inject

/**
 * @author kiri
 */
@ActivityScope
class TransactionPagePresenter @Inject constructor(
    private val detailedGraphPart: DetailedGraphPart,
    private val balanceOverviewPart: BalanceOverviewPart
) : BasePresenter<View, Nothing, Router>(), View.Presenter {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        with(view) {
            setup(R.layout.page_transactions)
            mountDetailedGraph(detailedGraphPart, owner)
            mountBalanceOverview(balanceOverviewPart, owner)
        }
    }

    override fun onBackButtonClicked() {
        router!!.navigateToCards()
    }
}
