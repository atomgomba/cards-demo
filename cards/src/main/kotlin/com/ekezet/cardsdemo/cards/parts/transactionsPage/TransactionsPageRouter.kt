package com.ekezet.cardsdemo.cards.parts.transactionsPage

import com.ekezet.base.BaseRouter
import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.R
import com.ekezet.cardsdemo.cards.parts.transactionsPage.TransactionsPageSpec.Router
import com.ekezet.cardsdemo.navigation.di.NavigationChannel
import javax.inject.Inject
import kotlinx.coroutines.channels.sendBlocking

/**
 * @author kiri
 */
@ActivityScope
class TransactionsPageRouter @Inject constructor(
    private val navigationChannel: NavigationChannel
) : BaseRouter(), Router {
    override fun navigateToCards() {
        navigationChannel.sendBlocking(R.id.navCards)
    }
}
