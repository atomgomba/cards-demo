package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.views

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.view.isVisible
import com.ekezet.base.di.ActivityScope
import com.ekezet.base.views.BaseView
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoSpec.View
import javax.inject.Inject
import kotlinx.android.synthetic.main.view_balance_info.view.*

/**
 * @author kiri
 */
@SuppressLint("ViewConstructor")
@ActivityScope
class BalanceInfoView @Inject constructor(
    context: Context,
    private val presenter: View.Presenter
) : BaseView(context), View {
    override var dueDate: CharSequence
        get() = throw NotImplementedError()
        set(value) {
            dueDateContainer.isVisible = value.isNotBlank()
            dueDateText.text = value
        }

    override fun setup(idRes: Int) {
        super.setup(idRes)
        detailsButton.setOnClickListener { presenter.onDetailsButtonClicked() }
    }

    override fun setCurrentBalance(value: CharSequence, currency: CharSequence) {
        currentBalanceContainer.isVisible = value.isNotBlank() && currency.isNotBlank()
        currentBalanceText.text = value
        currentBalanceCurrencyText.text = currency
    }

    override fun setMinPayment(value: CharSequence, currency: CharSequence) {
        minPaymentContainer.isVisible = value.isNotBlank() && currency.isNotBlank()
        minPaymentText.text = value
        minPaymentCurrencyText.text = currency
    }
}
