package com.ekezet.cardsdemo.cards.parts.transactionsPage.views

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.arch.AnyPart
import com.ekezet.base.di.ActivityScope
import com.ekezet.base.views.BaseView
import com.ekezet.cardsdemo.cards.parts.transactionsPage.TransactionsPageSpec.View
import javax.inject.Inject
import kotlinx.android.synthetic.main.page_transactions.view.*

/**
 * @author kiri
 */
@SuppressLint("ViewConstructor")
@ActivityScope
class TransactionsPageView @Inject constructor(
    context: Context,
    private val presenter: View.Presenter
) : BaseView(context), View {
    override fun setup(idRes: Int) {
        super.setup(idRes)
        backButton.setOnClickListener { presenter.onBackButtonClicked() }
    }

    override fun mountDetailedGraph(part: AnyPart, owner: LifecycleOwner) {
        part.bootWithContainer(detailedGraphContainer, owner.lifecycle)
    }

    override fun mountBalanceOverview(part: AnyPart, owner: LifecycleOwner) {
        part.bootWithContainer(balanceOverviewContainer, owner.lifecycle)
    }
}
