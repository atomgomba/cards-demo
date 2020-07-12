package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo

import com.ekezet.base.BaseRouter
import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.R
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceInfo.BalanceInfoSpec.Router
import com.ekezet.cardsdemo.navigation.di.NavigationChannel
import javax.inject.Inject
import kotlinx.coroutines.channels.sendBlocking

/**
 * @author kiri
 */
@ActivityScope
class BalanceInfoRouter @Inject constructor(
    private val navigationChannel: NavigationChannel
) : BaseRouter(), Router {
    override fun navigateToSelectedCardDetails() {
        navigationChannel.sendBlocking(R.id.navTransactions)
    }
}
