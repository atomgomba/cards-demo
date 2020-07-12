package com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.BasePresenter
import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.R
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.DetailedGraphSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.DetailedGraphSpec.View
import javax.inject.Inject

/**
 * @author kiri
 */
@ActivityScope
class DetailedGraphPresenter @Inject constructor() : BasePresenter<View, Interactor, Nothing>(),
    View.Presenter, Interactor.Presenter {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        view.setup(R.layout.view_detailed_graph)
    }

    @SuppressLint("DefaultLocale")
    override fun onSelectedCardChanged(card: Card) {
        view.setAmounts(card.currentBalance, card.availableBalance, card.pendingBalance)
        view.setCurrency(card.currency.toUpperCase())
    }
}
